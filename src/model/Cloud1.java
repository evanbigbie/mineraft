package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Cloud1 extends GameFigure {

    public Rectangle2D.Float cloud1;
    public final int CLOUD1_HEIGHT = 35;
    public final int CLOUD1_LEN = 80;

    int move = 0;
    int count = 0;


    public Cloud1(int x, int y) {
        cloud1 = new Rectangle2D.Float(100, 130, CLOUD1_LEN, CLOUD1_HEIGHT); // square base perimeter
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fill(cloud1);
        g2.draw(cloud1);
    }

    @Override
    public void update() {
        if (count < 20) {
            if (move % 2 == 0) {
                cloud1.x = 145;
                cloud1.y = 90; // actual height placement of cloud
            }
            else {
                cloud1.x = 142;
                cloud1.y = 91;
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
