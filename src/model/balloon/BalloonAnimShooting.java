package model.balloon;

public class BalloonAnimShooting implements BalloonAnimStrategy {

    int n = 0;
    int i = 0;

    Balloon context; // must then set all locations below (animate) to 'context.'

    public BalloonAnimShooting(Balloon context) {
        this.context = context;
    }
    @Override
    public void animate() {
        double rad = Math.atan2(context.target.y - context.location.y, context.target.x - context.location.x);
        if (i == 0) {
            if (n < 20) {
                context.location.x += context.UNIT_MOVE * Math.cos(rad); // read 'rad' as 'theta' to remember
                context.location.y += context.UNIT_MOVE * Math.sin(rad);
                n++;
            } else {
                n = 0;
                i = 1;
            }
        }
        else {
            if (n < 10) {
                context.location.x += context.UNIT_MOVE * Math.cos(rad); // read 'rad' as 'theta' to remember
                context.location.y += context.UNIT_MOVE * Math.sin(rad);
                n++;
            }
            n = 0;
            i = 0;
        }
    }
}
