package grail.atomic;

import grail.composite.AnImpossibleAngle;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.ROTATING_LINE, Comp301Tags.RESTRICTED_LINE})
public interface RotatingLine extends BoundedShape, Point{
//	double getAngle();
//	double getRadius();
	void setRadius(double theRadius);
	void setAngle(double theAngle) throws AnImpossibleAngle;
    void rotate(double units);
    void move(int moveX, int moveY);
}
