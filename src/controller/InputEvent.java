package controller;

import java.util.EventObject;

public class InputEvent { // store each event from physical input device

    public static final int MOUSE_PRESSED = 0; // these are def of type, so public access
    public static final int MOUSE_MOVED = 1;
    public static final int KEY_PRESSED = 2;
    public static final int Bird_CREATE = 3;
    public static final int EGG_CREATE = 5;
    public static final int MOUSE_RIGHT = 6;

    public EventObject event;
    public int type;
}
