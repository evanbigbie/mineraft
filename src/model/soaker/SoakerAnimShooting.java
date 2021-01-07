package model.soaker;

public class SoakerAnimShooting implements SoakerAnimStrategy {

    Soaker context; // must then set all locations below (animate) to 'context.'

    public SoakerAnimShooting(Soaker context) {
        this.context = context;
    }
    @Override
    public void animate() {
        context.location.y -= context.UNIT_MOVE;
        //double rad = Math.atan2(context.target.y - context.location.y, context.target.x - context.location.x);
        //context.location.x +=context.UNIT_MOVE * Math.cos(rad); // read 'rad' as 'theta' to remember
        //context.location.y += context.UNIT_MOVE * Math.sin(rad);
    }
}
