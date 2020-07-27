package controller;

import controller.observer.EggObserverAddNew;
import controller.observer.BirdObserverAddNew;
import model.*;
import model.egg.Egg;
import model.bird.Bird;
import view.MyWindow;

import javax.swing.*;
import java.awt.*;

public class Main {

    // doing public members for faster/direct access for @ 20fps
    public static MyWindow win;
    public static GameData gameData;
    public static PlayerInputEventQueue playerInputEventQueue;
    public static boolean running; // flag: for when to quit game

    public static int INDEX_MOUSE_POINTER = 0; // in gameData.fixedObject array list
    public static int INDEX_SHOOTER = 1;

    public static int FPS = 20; // frames per second == 1000 ms/20 = 50 ms

    public static void main(String[] args) {

        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();

        startScreen();

        initGame();

        gameLoop();
    }

    static void startScreen() {
        // show some initial message on canvas
        Font font = new Font("Helvetica Neue", Font.PLAIN, 40);
        gameData.friendObject.add(new Text("PRESS  Raft  TO RAFT", 130, 200, Color.WHITE, font));
        while (!running) {
            Main.win.canvas.render(); // anything from MyCanvas - 'render all game data here'
            try {
                Thread.sleep(500); // display for 5 seconds?
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // init = initial point (of the game)
    static void initGame() {
        gameData.clear();

        gameData.fixedObject.add(new MousePointer(0,0)); // loc changes based on where pointer is
        // canvas has not rendered yet so need get size:
        int x = Main.win.getWidth() / 2; // x: center in window
        int y = Main.win.getHeight() - 100; // y: approximation.. widget height issues
        int z = Main.win.getHeight() - 1200;
        gameData.fixedObject.add(new Shooter(x, y)); // define as x and y here
        gameData.fixedObject.add(new River(x, z));
        gameData.fixedObject.add(new Cloud1(x - 10, y));
        gameData.fixedObject.add(new Cloud2(x, y));

        addBirdwithListener(0,50);
        addEggwithListener(0, 50);
    }

    public static void addBirdwithListener(int x, int y) {
        var bird = new Bird(x, y);
        bird.attachListener(new BirdObserverAddNew());
        gameData.enemyObject.add(bird);
    }

    public static void addEggwithListener(int x, int y) {
        var egg = new Egg(x, y);
        egg.attachListener(new EggObserverAddNew());
        gameData.enemyObject.add(egg);
    }

    static void gameLoop() {

        running = true;

        // G A M E  L O O P: will update at frame rate
        while (running) {
            long startTime = System.currentTimeMillis(); // # of ms since 01/01/1970

            playerInputEventQueue.processInputEvents();
            processCollisions();
            gameData.update(); // bird in GameData will update each time
            win.canvas.render(); // render on the canvas

            long endTime = System.currentTimeMillis();

            long timeSpent = endTime - startTime;
            // time need to sleep before next iteration:
            long sleepTime = (long) (1000.0 / FPS - timeSpent); // 1/FPS is how much time for ea iteration

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // end game loop

    }

    static void processCollisions() { // idea: 2 objects w radius. true if distance between <= r1 + r2
        var shooter = (Shooter) Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
        for (var enemy: Main.gameData.enemyObject) {
            if (shooter.collideWith(enemy)) { // collision has been made / hit the target
                ++shooter.hitCount;
                ++enemy.hitCount;
            }
        }

        // here if hitCount is not zero then have been hit
        for (var friend: Main.gameData.friendObject) {
            for (var enemy: Main.gameData.enemyObject) {
                if (friend.collideWith(enemy)) {
                    ++friend.hitCount;
                    ++enemy.hitCount;
                }
            }
        }
    }
}
