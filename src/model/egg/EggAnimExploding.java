package model.egg;

import java.awt.*;

public class EggAnimExploding implements EggAnimStrategy {

    Egg context; // must then set all locations below (animate) to 'context.'

    public EggAnimExploding(Egg context) {
        this.context = context;
    }
    @Override
    public void animate() {
        context.color = Color.yellow;
        ++context.size1;
    }
}