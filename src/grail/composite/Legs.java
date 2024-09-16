package grail.composite;

import java.beans.PropertyChangeEvent;
import java.util.Arrays;

//import grail.atomic.ALocatable;
import grail.atomic.RestrictedLine;
import grail.atomic.RotatingLine;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({Comp301Tags.LEGS})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "leftLeg", "rightLeg"})
@EditablePropertyNames({"X", "Y"})
public class Legs extends AngleShape implements Angle{

	RestrictedLine leftLeg;
	RestrictedLine rightLeg;

	static final double LEFT_MINIMUM_ANGLE = (Math.PI)/2;
	static final double LEFT_MAXIMUM_ANGLE = (Math.PI);
	static final double RIGHT_MINIMUM_ANGLE = 0;
	static final double RIGHT_MAXIMUM_ANGLE = (Math.PI)/2;
	static final int LEFT_RADIUS = 20;
	static final double LEFT_ANGLE = 3 * (Math.PI)/4;
	static final int RIGHT_RADIUS = 20;
	static final double RIGHT_ANGLE = (Math.PI)/4;
	static final int LEG_X = 30;
	static final int LEG_Y = 30;



	public Legs() {
		leftLeg = new RestrictedLine(LEFT_MINIMUM_ANGLE, LEFT_MAXIMUM_ANGLE, LEFT_RADIUS, LEFT_ANGLE);
		rightLeg = new RestrictedLine(RIGHT_MINIMUM_ANGLE, RIGHT_MAXIMUM_ANGLE, RIGHT_RADIUS, RIGHT_ANGLE);
		leftLeg.setX(LEG_X);
		leftLeg.setY(LEG_Y);
		rightLeg.setX(LEG_X);
		rightLeg.setY(LEG_Y);
		try {
			leftLeg.setAngle(INITIAL_LEFT_ANGLE);
			rightLeg.setAngle(INITIAL_RIGHT_ANGLE);
		} catch(AnImpossibleAngle e) {
			System.out.println(e.getMessage());
		}
		leftLeg.setRadius(INITIAL_RADIUS);
		rightLeg.setRadius(INITIAL_RADIUS);
	}
	@Override
	public void setX(int newX) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "point_x", leftLeg.getX(), newX);
		this.x = newX;
		leftLeg.setX(newX);
		rightLeg.setX(newX);
		notifyAllObservers(propertyChangeEvent);
	}
	@Override
	public void setY(int newY) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "point_y", leftLeg.getY(), newY);
		this.y = newY;
		leftLeg.setY(newY);
		rightLeg.setY(newY);
		notifyAllObservers(propertyChangeEvent);
	}
	@Override
	public void move(int offsetX, int offsetY) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "angle_point", Arrays.asList(getX(), getY()), Arrays.asList(offsetX, offsetY));
		setX(offsetX);
		setY(offsetY);
		notifyAllObservers(propertyChangeEvent);
	}

	public RestrictedLine getLeftLeg() {
		return leftLeg;
	}

	public RestrictedLine getRightLeg() {
		return rightLeg;
	}
	@Override
	public RotatingLine getRightLine() {
		// TODO Auto-generated method stub
		return leftLeg;
	}
	@Override
	public RotatingLine getLeftLine() {
		// TODO Auto-generated method stub
		return rightLeg;
	}

}
