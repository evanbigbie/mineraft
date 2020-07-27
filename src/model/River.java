package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class River extends GameFigure {

    public Rectangle2D.Float water;
    public final int WATER_HEIGHT = 30;
    public final int WATER_LEN = 1300;

    public River(int x, int y) {
        water = new Rectangle2D.Float(0, 1500, WATER_LEN, WATER_HEIGHT); // square base perimeter
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.cyan);
        g2.fill(water);
        g2.draw(water);
    }

    @Override
    public void update() {
        water.x = 0;
        water.y = 400; // actual height placement of water
    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
