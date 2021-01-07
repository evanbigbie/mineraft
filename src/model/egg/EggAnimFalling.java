package model.egg;

import java.awt.*;

public class EggAnimFalling implements EggAnimStrategy {

    Egg context;
    int n = 1;

    public EggAnimFalling(Egg context) {
        this.context = context;
    }

    @Override
    public void animate() {
        if (n == 1) {
            n -= 1;
            context.color = Color.black;
        }
        else {
            n += 1;
            context.color = Color.black;
        }
        context.location.x += context.UNIT_MOVE_FALLING;
    }
}
