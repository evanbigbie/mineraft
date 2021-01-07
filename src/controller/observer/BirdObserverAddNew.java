package controller.observer;

import controller.InputEvent;
import controller.Main;

public class BirdObserverAddNew implements Observer {
    @Override
    public void eventReceived() {
        InputEvent event = new InputEvent();
        event.event = new BirdCreateEvent("Bird", -50, 100);
        event.type = InputEvent.Bird_CREATE;
        Main.playerInputEventQueue.queue.add(event);
    }
}
