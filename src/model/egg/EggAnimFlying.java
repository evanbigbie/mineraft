package model.egg;

import controller.Main;

public class EggAnimFlying implements EggAnimStrategy {

    Egg context;
    int n = 0;

    public EggAnimFlying(Egg context) {
        this.context = context;
    }

    @Override
    public void animate() { // copy paste from Bird
        n++;
        if (context.location.x > Main.win.canvas.width) {
            context.location.x = 0; // *********************************** FP: bird regenerates on left
        } else if (context.location.x <= Main.win.canvas.width) {
            context.movingRight = true;
        }
        if (context.movingRight) {
            context.location.x += context.UNIT_MOVE + 9;
            context.location.y += context.UNIT_MOVE + n/4;
        }
    }
}