package model.egg;

import controller.Main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;

import java.awt.*;
import java.util.ArrayList;

public class Egg extends GameFigure implements Subject {

    public static int UNIT_MOVE = 5;
    public static int UNIT_MOVE_FALLING = 100;
    public static final int STATE_FLYING = 0;
    public static final int STATE_FALLING = 1;
    public static final int STATE_DONE = 2;
    public static final int STATE_EXPLODING = 3;

    public static final int INIT_EGG_SIZE = 25;
    public static final int MAX_EGG_SIZE = 75;

    int size1 = INIT_EGG_SIZE;
    int size2 = INIT_EGG_SIZE/2;
    int width, height;
    boolean movingRight = true; // flag
    int state;
    Color color;
    EggAnimStrategy animStrategy;

    ArrayList<Observer> listeners = new ArrayList<>();

    public Egg(int x, int y) {
        super(x, y); // pass to GameFigure
        width = size1/2;
        height = size1;
        state = STATE_FLYING;
        color = Color.white;
        animStrategy = new EggAnimFlying(this);
    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        g2.fillOval((int)location.x - width/2, (int)location.y - height/2, size2, size1);
    }

    @Override
    public void update() {
        updateState();
        animStrategy.animate(); // Strategy design pattern
    }

    private void updateState() {
        if (state == STATE_FLYING) {
            if (hitCount > 0) {
                size2 = size1;
                state = STATE_EXPLODING;
                animStrategy = new EggAnimExploding(this);

            }
            else if (location.y >= Main.win.canvas.height) {
                state = STATE_DONE;

            }
            else if (location.x >= Main.win.canvas.width) {
                state = STATE_DONE;

            }
        }
        else if (state == STATE_EXPLODING) {
                if (size1 >= MAX_EGG_SIZE) {
                    size1 = INIT_EGG_SIZE;
                    size2 = INIT_EGG_SIZE/2;
                    state = STATE_FALLING;
                    animStrategy = new EggAnimFalling(this);
                }
        }
        else if (state == STATE_FALLING) {
            if (location.x >= Main.win.canvas.height) {
                state = STATE_DONE;
            }
        }
        else if (state == STATE_DONE) {
            super.done = true;
            notifyEvent(); // Bird died at this moment
        }
    }
    @Override
    public int getCollisionRadius() {
        return (int) (size1/2 * 0.75);
    }

    @Override
    public void attachListener(Observer o) {
        listeners.add(o);
    }

    @Override
    public void detachListener(Observer o) {
        listeners.remove(o);
    }

    @Override
    public void notifyEvent() {
        for (var o: listeners) { // for all the observers attached
            o.eventReceived();
        }
    }
}

