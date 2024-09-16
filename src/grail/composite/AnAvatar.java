package grail.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.atomic.ABoundedShape;
import grail.atomic.ARotatingLine;
import grail.atomic.RotatingLine;
import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({Comp301Tags.AVATAR})
@PropertyNames({"X", "Y", "head", "torso", "arms", "legs", "stringShape", "Width", "Height"})
@EditablePropertyNames({"X", "Y", "Height", "Width", "Radius", "Angle"})

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AnAvatar extends ABoundedShape implements Avatar{

	Image head;
	RotatingLine torso = new ARotatingLine();
	Angle arms;
	Legs legs ;
	Text stringShape = new StringShape();

	static final int OFFSET_SPEECH = 15;
	static final double OFFSET_ARMS = 0.25;

//	static int sharedX = head.getX() + head.getWidth() / 2;
//	static int sharedY = head.getY() + head.getHeight();
	static final double INITIAL_LEFT_ANGLE = 3 * (Math.PI)/4;
	static final double INITIAL_RIGHT_ANGLE = 1 * (Math.PI)/4;
	static final int INITIAL_RADIUS = 20;

	// constructor
	public AnAvatar(Image thisHead) {
		head = thisHead;
		build();
	}

//	public AnAvatar(int xPosition, int yPosition, String fileName) {
//		head = new ImageShape();
//		head.setX(x_position);
//		head.setY(y_position);
//		build();
//	}

	@Override
	public Image getHead() {
		return head;
	}

	@Override
	public RotatingLine getTorso() {
		return torso;
	}

	@Override
	public Angle getArms() {
		return arms;
	}

	@Override
	public Legs getLegs() {
		return legs;
	}

	@Override
	public Text getStringShape() {
		return stringShape;
	}

	public void build() {

		int sharedX = head.getX() + head.getWidth() / 2;
		int sharedY = head.getY() + head.getHeight();

		torso.setX(sharedX);
		torso.setY(sharedY);
		arms = FactoryClass.legsFactoryMethod(sharedX, sharedY + (int)(torso.getHeight() * OFFSET_ARMS), INITIAL_LEFT_ANGLE, INITIAL_RADIUS, INITIAL_RIGHT_ANGLE, INITIAL_RADIUS);
		legs = FactoryClass.restrictedLegsFactoryMethod(sharedX, sharedY + torso.getHeight(), INITIAL_LEFT_ANGLE, INITIAL_RADIUS, INITIAL_RIGHT_ANGLE, INITIAL_RADIUS);
//		System.out.println(arms.getX()+" ---  "+ arms.getY());

		stringShape.setX(head.getX() + head.getWidth() + OFFSET_SPEECH);
		stringShape.setY(head.getY() - OFFSET_SPEECH);

	}
	@Override
	public void speech(String text) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "stringShape", getStringShape().getText(), text  );
		getStringShape().setText(text);
		notifyAllObservers(propertyChangeEvent);
	}
	@Override
	public void move(int newX, int newY) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "avatar_point", Arrays.asList(head.getX(), head.getY()), Arrays.asList(newX, newY));
		head.setX(newX);
		head.setY(newY);
		build();
		notifyAllObservers(propertyChangeEvent);
		// TODO add rotating method
	}

	@Override
	public void scale(double scaleRatio) {

//		OFFSET_ARMS *= scaleRatio;
////		OFFSET_SPEECH *= scaleRatio;

		head.setWidth((int) (head.getWidth() * scaleRatio));
		head.setHeight((int) (head.getHeight() * scaleRatio));
		torso.setHeight((int) (torso.getHeight() * scaleRatio));
		arms.getLeftLine().setRadius((arms.getLeftLine().getRadius()) * scaleRatio);
		arms.getRightLine().setRadius((arms.getRightLine().getRadius()) * scaleRatio);
		legs.getRightLine().setRadius((legs.getRightLine().getRadius()) * scaleRatio);
		legs.getLeftLine().setRadius((legs.getLeftLine().getRadius()) * scaleRatio);
		build();
	}

	public static void addPropertyChangeListener(Avatar anAvatar, PropertyChangeListener aListener) {
		anAvatar.getHead().addPropertyChangeListener(aListener);
		anAvatar.getTorso().addPropertyChangeListener(aListener);
		anAvatar.getArms().addPropertyChangeListener(aListener);
		anAvatar.getLegs().addPropertyChangeListener(aListener);
		anAvatar.getStringShape().addPropertyChangeListener(aListener);
	}


	public static void main(String[] args) {
		final long timeBetween = 1000;
		Image head = new ImageShape();
		head.setImageFileName("images/arthur.jpg");
		Avatar avatar = new AnAvatar(head);
    	OEFrame anOEFrame = ObjectEditor.edit(avatar);
    	ThreadSupport.sleep(timeBetween);
    	anOEFrame.refresh();
	}
}
