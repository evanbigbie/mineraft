package controller.observer;

import java.util.EventObject;
import java.util.Random;

public class EggCreateEvent extends EventObject {
    // stored into PlayerInputEventQueue which is a LinkedList<>() of InputEvent type

    Random rand = new Random();

    private int x;
    private int y;
    private int r = rand.nextInt(250) + 1;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public EggCreateEvent(Object source, int x, int y) {
        super(source);
        this.x = x;
        this.y = y - 100;
    }

    int q = 0;
    public int getX() {return x;}
    public int getY() {
        if (q == 0) {
            q = 1;
            return y + r;
        } else {
            q = 0;
            return y - r;
        }
    }
}
