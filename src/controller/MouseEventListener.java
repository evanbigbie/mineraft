package controller;

// Look up Java 10 API MouseListener/MouseMotionListener
// unrelated: spelling errors are corrected program-wide via 'refactoring'

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEventListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) { // pressed + released (quickly) = clicked
        // wrap by object and store in queue
        // System.out.println("mouse pressed at " + e.getX() + " " + e.getY());

        // create object and put it in the queue:
        InputEvent inputEvent = new InputEvent();
        inputEvent.event = e; // something superclass sub to super class allowed (see InputEvent)
        if (e.getButton() == 1) {
            inputEvent.type = InputEvent.MOUSE_PRESSED;
            //System.out.println("left click");
        }
        else if (e.getButton() == 3) {
            inputEvent.type = InputEvent.MOUSE_RIGHT;
            //System.out.println("right click");
        }
        Main.playerInputEventQueue.queue.add(inputEvent);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println("mouse moved at " + e.getX() + " " + e.getY());
        InputEvent inputEvent = new InputEvent();
        inputEvent.event = e;
        inputEvent.type = InputEvent.MOUSE_MOVED;
        Main.playerInputEventQueue.queue.add(inputEvent);
    }
}
