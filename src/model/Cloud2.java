package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Cloud2 extends GameFigure {

    public Rectangle2D.Float cloud2;
    public final int CLOUD2_HEIGHT = 40;
    public final int CLOUD2_LEN = 100;

    int move = 0;
    int count = 0;

    public Cloud2(int x, int y) {
        cloud2 = new Rectangle2D.Float(110, 165, CLOUD2_LEN, CLOUD2_HEIGHT); // square base perimeter
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fill(cloud2);
        g2.draw(cloud2);
    }

    @Override
    public void update() {
        if (count < 20) {
            if (move % 2 == 0) {
                cloud2.x = 445;
                cloud2.y = 120; // actual height placement of cloud
            }
            else {
                cloud2.x = 442;
                cloud2.y = 121;
            }
            count++;
        }
        else {
            count = 0;
            move++;
        }
    }

    @Override
    public int getCollisionRadius() {
        return 0;
    }
}
