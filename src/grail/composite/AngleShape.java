package grail.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.atomic.ALocatable;
import grail.atomic.ARotatingLine;
import grail.atomic.RotatingLine;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({Comp301Tags.ANGLE})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "leftLine", "rightLine"})
@EditablePropertyNames({"X", "Y"})
public class AngleShape extends ALocatable implements Angle{

	RotatingLine leftLine;
	RotatingLine rightLine;

	static final int INITIAL_X = 30;
	static final int INITIAL_Y = 30;
	static final double INITIAL_LEFT_ANGLE = 3 * (Math.PI)/4;
	static final double INITIAL_RIGHT_ANGLE = 1 * (Math.PI)/4;
	static final int INITIAL_RADIUS = 20;
//
//	double radius;
//	double angle;

	public AngleShape() {
		try {
			leftLine = new ARotatingLine();
			rightLine = new ARotatingLine();
			leftLine.setX(INITIAL_X);
			leftLine.setY(INITIAL_Y);
			rightLine.setX(INITIAL_X);
			rightLine.setY(INITIAL_Y);
			leftLine.setAngle(INITIAL_LEFT_ANGLE);
			rightLine.setAngle(INITIAL_RIGHT_ANGLE);
			leftLine.setRadius(INITIAL_RADIUS);
			rightLine.setRadius(INITIAL_RADIUS);
		} catch(AnImpossibleAngle e) {
			System.out.println(e.getMessage());
		}

	}


//	public AngleShape(double leftAngle, double rightAngle) {
//		double theRadius = radius;
//		leftLine = new ARotatingLine(theRadius, leftAngle);
//		rightLine = new ARotatingLine(theRadius, rightAngle);
//	}

	@Override
	public RotatingLine getLeftLine() {
		return leftLine;
	}

	@Override
	public RotatingLine getRightLine() {
		return rightLine;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int newX) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "point_x", leftLine.getX(), newX);
		this.x = newX;
		leftLine.setX(newX);
		rightLine.setX(newX);
		notifyAllObservers(propertyChangeEvent);
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int newY) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "point_y", leftLine.getY(), newY);
		this.y = newY;
		leftLine.setY(newY);
		rightLine.setY(newY);
		notifyAllObservers(propertyChangeEvent);
	}

	@Override
	public void move(int offsetX, int offsetY) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "angle_point", Arrays.asList(getX(), getY()), Arrays.asList(offsetX, offsetY));
		setX(offsetX);
		setY(offsetY);
		notifyAllObservers(propertyChangeEvent);
	}

	public static void addPropertyChangeListener(Angle anAngle, PropertyChangeListener aListener) {
		anAngle.addPropertyChangeListener(aListener);
//		anAngle.getLeftLine().addPropertyChangeListener(aListener);
//		anAngle.getRightLine().addPropertyChangeListener(aListener);

	}


	public static void main(String[] args) {
		final long timeBetween = 1000;
		Angle angle = new AngleShape();
    	OEFrame anOEFrame = ObjectEditor.edit(angle);
    	ThreadSupport.sleep(timeBetween);
    	anOEFrame.refresh();
	}
}
