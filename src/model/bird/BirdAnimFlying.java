package model.bird;

import controller.Main;

public class BirdAnimFlying implements BirdAnimStrategy {

    Bird context;

    public BirdAnimFlying(Bird context) {
        this.context = context;
    }

    @Override
    public void animate() { // copy paste from Bird
        if (context.location.x > Main.win.canvas.width) {
            context.location.x = 0; // *********************************** FP: bird regenerates on left
        } else if (context.location.x <= Main.win.canvas.width) {
            context.movingRight = true;
        }
        if (context.movingRight) {
            context.location.x += context.UNIT_MOVE * 3/4;
            context.location.y -= 1/10;
        }
    }
}
