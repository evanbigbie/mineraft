package model.mine;

import java.awt.*;

public class MineStateTimer implements MineState {

    int z = 0;
    int n = 1;

    public MineStateTimer() {
    }
    @Override
    public void goNext(Mine context) {
        if (z < 100) {
            if (z % 10 == 0) {
                if (n == 1) {
                    context.color = Color.red;
                    n -= 1;
                } else {
                    context.color = Color.DARK_GRAY;
                    n += 1;
                }
            }
            z++;
        }
        else
            context.setState(new MineStateFire());
    }
}


