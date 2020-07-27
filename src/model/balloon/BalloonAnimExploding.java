package model.balloon;

import java.awt.*;

public class BalloonAnimExploding implements BalloonAnimStrategy {

    Balloon context; // must then set all locations below (animate) to 'context.'

    public BalloonAnimExploding(Balloon context) {
        this.context = context;
    }
    @Override
    public void animate() {
        context.color = Color.cyan;
        ++context.size;
        ++context.size;
        ++context.size;
    }
}
