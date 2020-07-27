package model;

import java.awt.*;

public class MousePointer extends GameFigure {

    public final int SIZE = 10;

    public MousePointer(int x, int y) {
        super(x, y); // pass info parent class; changing location provided in GameFigure
    }

    @Override
    public void render(Graphics2D g2) {

        //draw a cross/plus shape for cursor: +
        g2.setColor(Color.CYAN);
        // draw horizontal line:
        g2.drawLine((int)location.x - SIZE, (int)location.y,
                (int)location.x + SIZE, (int)location.y);
        // draw vertical line:
        g2.drawLine((int)location.x, (int)location.y - SIZE,
                (int)location.x, (int)location.y + SIZE);
    }

    @Override
    public void update() {
        // N/A for this case
    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
