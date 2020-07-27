package model.mine;

import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;

import java.awt.*;
import java.util.ArrayList;

public class Mine extends GameFigure implements Subject {

    public static int UNIT_MOVE = 12;

    int size = 20;
    int width, height;
    Color color;
    private MineState mineState;

    ArrayList<Observer> listeners = new ArrayList<>();

    public Mine(int x, int y) {
        super(x, y); // pass to GameFigure
        width = size;
        height = size/2;
        color = Color.darkGray;
        mineState = new MineStateTimer(); // this is the initial state of new Mine
    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        g2.fillRect((int)location.x - width/2, (int)location.y - height/2, width, height);
    }

    public void goNextState() {
            mineState.goNext(this);
        }

    // Mine has the state transition information
    public void setState(MineState mineState) {
        this.mineState = mineState;
    }

    @Override
    public void update() {
            goNextState();
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
