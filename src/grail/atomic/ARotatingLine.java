package grail.atomic;

import java.beans.PropertyChangeEvent;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.composite.AnImpossibleAngle;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({Comp301Tags.ROTATING_LINE})
@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({"X", "Y", "Height", "Width", "Radius", "Angle"})
@EditablePropertyNames({"X", "Y", "Height", "Width", "Radius", "Angle"})
//
public class ARotatingLine extends ABoundedShape implements RotatingLine{

	static final int INITIAL_RADIUS = 25;
	static final double INITIAL_ANGLE = Math.PI / 2;
//	int x, y;
	Point point;

	public ARotatingLine() {
		point = new APolarPoint(INITIAL_RADIUS, INITIAL_ANGLE);
	}

	public ARotatingLine(double theRadius, double theAngle) {
		point = new APolarPoint(theRadius, theAngle);
	}

	@Override
	public int getWidth() {
		return point.getX();
	}
	@Override
	public int getHeight() {
		return point.getY();
	}

	@Override
	public double getRadius() {
		return point.getRadius();
	}

	@Override
	public void setRadius(double theRadius) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "point", point.getRadius(), theRadius);
		point = new APolarPoint(theRadius, point.getAngle());
		notifyAllObservers(propertyChangeEvent);
	}

	@Override
	public double getAngle() {
		return point.getAngle();
	}

	@Override
	public void setAngle(double theAngle) throws AnImpossibleAngle {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "point", point.getAngle(), theAngle);
		point = new APolarPoint(point.getRadius(), theAngle);
		notifyAllObservers(propertyChangeEvent);
	}



	@Override
	public void rotate(double units) {
		try {
			double rotateAmount = units * (Math.PI / 32);
			setAngle(rotateAmount+getAngle());
		} catch(AnImpossibleAngle e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void move(int moveX, int moveY) {
		setX(moveX);
		setY(moveY);
	}

	public static void main(String[] args) {
		final long timeBetween = 1000;
		RotatingLine line = new ARotatingLine();
    	OEFrame anOEFrame = ObjectEditor.edit(line);
    	ThreadSupport.sleep(timeBetween);
    	anOEFrame.refresh();
	}
}