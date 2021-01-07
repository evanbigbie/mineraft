package controller.observer;

import controller.InputEvent;
import controller.Main;

public class EggObserverAddNew implements Observer {
    @Override
    public void eventReceived() {
        //System.out.println("Bird died");
        InputEvent event = new InputEvent();
        event.event = new EggCreateEvent("Egg", 0, 100);
        event.type = InputEvent.EGG_CREATE;
        Main.playerInputEventQueue.queue.add(event);
    }
}
