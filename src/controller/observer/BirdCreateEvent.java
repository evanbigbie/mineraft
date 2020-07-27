package controller.observer;

import java.util.EventObject;
import java.util.Random;

public class BirdCreateEvent extends EventObject {
    // stored into PlayerInputEventQueue which is a LinkedList<>() of InputEvent type

    Random rand = new Random();

    private int x;
    private int y;
    private int n = rand.nextInt(150) + 1;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BirdCreateEvent(Object source, int x, int y) {
        super(source);
        this.x = x;
        this.y = y + n;
    }

    public int getX() {return x;}
    public int getY() {return y;}
}
