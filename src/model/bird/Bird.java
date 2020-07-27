package model.bird;

import controller.Main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;

import java.awt.*;
import java.util.ArrayList;

public class Bird extends GameFigure implements Subject {

    public static int UNIT_MOVE = 15;
    public static int UNIT_MOVE_FALLING = 5;
    public static final int STATE_FLYING = 0;
    public static final int STATE_FALLING = 1;
    public static final int STATE_DONE = 2;

    int size = 30;
    int width, height;
    boolean movingRight = true; // flag
    int state;
    Color color;
    BirdAnimStrategy animStrategy;

    ArrayList<Observer> listeners = new ArrayList<>();

    public Bird(int x, int y) {
        super(x, y); // pass to GameFigure
        width = size/2;
        height = size - 10;
        state = STATE_FLYING;
        color = Color.magenta;
        animStrategy = new BirdAnimFlying(this);
    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        g2.fillRect((int)location.x - width/2, (int)location.y - height/2, width, height);
    }

    @Override
    public void update() {
        updateState();
        animStrategy.animate(); // Strategy design pattern
    }

    private void updateState() {
        if (state == STATE_FLYING) {
            if (hitCount > 0) {
                state = STATE_FALLING;
                animStrategy = new BirdAnimFalling(this);
            }
            else if (location.x >= Main.win.canvas.width - 20) {
                    state = STATE_DONE;
                    super.done = true;
                    notifyEvent();
            }
        } else if (state == STATE_FALLING) {
            if (location.y >= Main.win.canvas.height) {
                state = STATE_DONE;
            }
        } else if (state == STATE_DONE) {
            super.done = true;
            notifyEvent(); // Bird died at this moment
        }
    }
    @Override
    public int getCollisionRadius() {
        return (int) (size/2 * 0.75);
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
