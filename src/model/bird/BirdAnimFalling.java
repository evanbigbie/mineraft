package model.bird;

import java.awt.*;

public class BirdAnimFalling implements BirdAnimStrategy {

    Bird context;
    int n = 1;

    public BirdAnimFalling(Bird context) {
        this.context = context;
    }

    @Override
    public void animate() {
        if (n == 1) {
            n -= 1;
            context.color = Color.RED;
        }
        else {
            n += 1;
            context.color = Color.PINK;
        }
        context.location.y += context.UNIT_MOVE_FALLING;
    }
}
