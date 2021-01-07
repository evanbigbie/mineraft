package model.balloon;

import controller.Main;
import model.GameFigure;
import model.Shooter;

import java.awt.*;
import java.awt.geom.Point2D;

public class Balloon extends GameFigure {

    // define some constants:
    public static final int UNIT_MOVE = 5; // in each frame move 5 px along the firing line
    public static final int INIT_BALLOON_SIZE = 15;
    public static final int MAX_BALLOON_SIZE = 40;

    public static final int STATE_SHOOTING = 0;
    public static final int STATE_EXPLODING = 1;
    public static final int STATE_DONE = 2;

    int size = INIT_BALLOON_SIZE;
    Point2D.Float target; // where mouse was pressed
    Color color;
    int state;
    BalloonAnimStrategy animStrategy;

    public Balloon(int tx, int ty) { // (tx, ty) is mouse pressed location - - this where balloon created
        Shooter shooter = (Shooter) Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
        super.location.x = shooter.barrel.x2; // x1/y1 is start, x2/y2 is end
        super.location.y = shooter.barrel.y2; // y2 is the end of the barrel
        target = new Point2D.Float(tx, ty);
        color = Color.RED;
        state = STATE_SHOOTING;
        animStrategy = new BalloonAnimShooting(this); // this: passing *context* to BalloonAnimShooting
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1));
        // (- size/2 to deal with circle within bounding box):
        g2.fillOval((int)super.location.x - size/2, (int)super.location.y - size/2, size, size);
    }

    @Override
    public void update() { // update == increment (most complicated)
        updateState();
        animStrategy.animate(); // Strategy design pattern

    }

    private void updateState() {
        if (state == STATE_SHOOTING) {
            if (hitCount > 0 || target.distance(location) <= 3.0) {
                state = STATE_EXPLODING;
                animStrategy = new BalloonAnimExploding(this);
            }
        } else if (state == STATE_EXPLODING) {
            if (size >= MAX_BALLOON_SIZE) {
                state = STATE_DONE;
            }
        } else if (state == STATE_DONE) {
            super.done = true;
        }
    }

    @Override
    public int getCollisionRadius() {
        return size/2;
    }
}
