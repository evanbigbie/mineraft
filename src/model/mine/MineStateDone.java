package model.mine;

public class MineStateDone implements MineState {

    int r = 0;

    public MineStateDone(){
    }
    @Override
    public void goNext(Mine context) {
        context.setState(new MineStateTimer());
    }
}

