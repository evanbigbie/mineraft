package model.soaker;

import java.awt.*;

public class SoakerAnimExploding implements SoakerAnimStrategy {

    Soaker context; // must then set all locations below (animate) to 'context.'

    public SoakerAnimExploding(Soaker context) {
        this.context = context;
    }
    @Override
    public void animate() {
        context.color = Color.cyan;
        ++context.size;
        ++context.size;
    }
}
