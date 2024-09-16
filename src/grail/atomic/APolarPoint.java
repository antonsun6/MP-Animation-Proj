package grail.atomic;
//import grail.composite.ALocatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({"X", "Y", "Angle", "Radius"})
@EditablePropertyNames({})
public class APolarPoint implements Point {

	double radius, angle;

	public APolarPoint(double theRadius, double theAngle) {
		radius = theRadius;
		angle = theAngle;
	}

	public APolarPoint(int theX, int theY) {
		radius = Math.sqrt(theX*theX + theY*theY);
		angle = Math.atan((double) theY/theX);
	}
	// get X, Y of the polar point
	@Override
	public int getX() { return (int) (radius*Math.cos(angle)); }
	@Override
	public int getY() { return (int) (radius*Math.sin(angle)); }
	@Override
	public double getAngle() { return angle; }
	@Override
	public double getRadius() { return radius;}
}

