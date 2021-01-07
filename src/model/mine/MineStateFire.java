package model.mine;

import java.awt.*;

public class MineStateFire implements MineState {

    int t = 0;

    public MineStateFire() {

    }
    @Override
    public void goNext(Mine context) {
        if (t == 0) {
            context.color = Color.yellow;
            context.width *= 5;
            context.height *= 5;
            t++;
        }
        else if (t == 1) {
            context.color = Color.orange;
            t++;
        }
        else if (t == 2) {
            context.color = Color.yellow;
            t++;
        }
        else if (t == 3) {
            context.width /= 5;
            context.height /= 5;
            context.color = Color.darkGray;
            t++;
        }
        else {
            if (context.location.y > -20)
                context.location.y -= context.UNIT_MOVE;
            else
                context.setState(new MineStateDone());
        }
    }
}

