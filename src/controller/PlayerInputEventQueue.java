package controller;

import controller.observer.EggCreateEvent;
import controller.observer.BirdCreateEvent;
import model.balloon.Balloon;
import model.MousePointer;
import model.Shooter;
import model.mine.Mine;
import model.soaker.Soaker;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class PlayerInputEventQueue { // store events from mouse, keyboard, etc

    public LinkedList<InputEvent> queue = new LinkedList<>();

    public void processInputEvents() { // any event that is queued is processed here

        while (!queue.isEmpty()) {
            InputEvent inputEvent = queue.removeFirst();

            switch (inputEvent.type) {
                case InputEvent.MOUSE_PRESSED:
                    MouseEvent e = (MouseEvent) inputEvent.event;
                    Balloon b = new Balloon(e.getX(), e.getY()); // created a 'balloon' object at the mouse press location
                    Main.gameData.friendObject.add(b); // here the balloon will be animated
                    break;
                case InputEvent.MOUSE_RIGHT:
                    int i = 0;
                    var shooter1 = Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
                    MouseEvent f = (MouseEvent) inputEvent.event;
                    Mine m = new Mine((int) shooter1.location.x, (int) shooter1.location.y - 5);
                    //if (Main.gameData.friendObject.size() < 5) {
                    Main.gameData.friendObject.add(m);

                    break;
                case InputEvent.MOUSE_MOVED: // type cast to MousePointer/MouseEvent
                    MousePointer mp = (MousePointer) Main.gameData.fixedObject.get(0);
                    MouseEvent me = (MouseEvent) inputEvent.event;
                    mp.location.x = me.getX();
                    mp.location.y = me.getY();
                    break;
                case InputEvent.KEY_PRESSED: // when key pressed, change location of shooter
                    var shooter = Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);

                    KeyEvent ke = (KeyEvent) inputEvent.event; // (error said: required .. x.KeyEvent)
                    // will know which key pressed by this
                    switch (ke.getKeyCode()) {
                        //case KeyEvent.VK_UP:
                            //KeyEvent kk = (KeyEvent) inputEvent.event;
                            //var shootx = (int)shooter.location.x;
                            //Soaker s = new Soaker(shootx, 400);
                           // break;
                            //shooter.location.y -= Shooter.UNIT_MOVE;
                            //break;
                        //case KeyEvent.VK_DOWN:
                            //shooter.location.y += Shooter.UNIT_MOVE;
                            //break;
                        case KeyEvent.VK_LEFT:
                            shooter.location.x -= Shooter.UNIT_MOVE;
                            break;
                        case KeyEvent.VK_RIGHT:
                            shooter.location.x += Shooter.UNIT_MOVE;
                            break;
                    }
                    break;
                case InputEvent.Bird_CREATE:
                    BirdCreateEvent ue = (BirdCreateEvent) inputEvent.event;
                    Main.addBirdwithListener(ue.getX(), ue.getY()); // create new Bird in this moment
                    break;
                case InputEvent.EGG_CREATE:
                    EggCreateEvent ee = (EggCreateEvent) inputEvent.event;
                    Main.addEggwithListener(ee.getX(), ee.getY()); // create new Mine in this moment
                    break;
            }
        }
    }
}
