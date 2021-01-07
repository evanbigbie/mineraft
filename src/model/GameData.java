package model;

import java.util.ArrayList;

public class GameData { // things to be displayed on the screen (what's put into double buffer)

    public ArrayList<GameFigure> fixedObject = new ArrayList<>(); // ancestor of any object to be displayed
    public ArrayList<GameFigure> friendObject = new ArrayList<>();
    public ArrayList<GameFigure> enemyObject = new ArrayList<>();

    public void update() { // here means update everything, uses loop

        ArrayList<GameFigure> remove = new ArrayList<>(); // put into new array list to avoid exception (see below)
        for (var fig : fixedObject) {
            if(fig.done) remove.add(fig);
            else fig.update();
        }
        fixedObject.removeAll(remove);

        remove.clear();
        for (var fig : friendObject) {
            if (fig.done) remove.add(fig);
            else fig.update();
        }
        friendObject.removeAll(remove);

        remove.clear();
        for (var fig : enemyObject) { // this iterating through enemyObject array list
            // which is why we got the ConcurrentModificationException: adding objects into list
            if (fig.done) remove.add(fig);
            else fig.update();
        }
        enemyObject.removeAll(remove);
    }

        public void clear() {
            fixedObject.clear();
            friendObject.clear();
            enemyObject.clear();
        }
}
