package grail.composite;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.atomic.ABoundedShape;
import grail.atomic.ARotatingLine;
import grail.atomic.AScalableRectangle;
import grail.atomic.RotatingLine;
import grail.atomic.ScalableRectangle;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.misc.ThreadSupport;

@PropertyNames({"rectangle", "side1", "side2", "X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y", "Height", "Width"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class BuildGorgeShape extends ABoundedShape implements BuildGorge {

	static final double SIDE_ANGLE = (Math.PI / 2);
	static final int RECTANGLE_X = 700;
	static final int RECTANGLE_Y = 570;
	static final int RECTANGLE_WIDTH = 225;
	static final int RECTANGLE_HEIGHT = 20;
	static final int SCALE_FACTOR = 10;

	ScalableRectangle rectangle = new AScalableRectangle(RECTANGLE_X, RECTANGLE_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
	RotatingLine side1 = new ARotatingLine();
	RotatingLine side2 = new ARotatingLine();

	public BuildGorgeShape() {
//		side.getLeftLine().setRadius(bridgeRadius);
//		side.getLeftLine().setAngle(bridgeAngle);
//		side.getRightLine().setRadius(bridgeRadius2);
//		side.getRightLine().setAngle(bridgeAngle2);
		rectangle.getX();
		rectangle.getY();
		build();
	}

	@Override
	public ScalableRectangle getRectangle() {
		return rectangle;
	}

	@Override
	public RotatingLine getSide1() {
		return side1;
	}

	@Override
	public RotatingLine getSide2() {
		return side2;
	}

	public void build() {
		try {
			side1.setX(rectangle.getX()); //good
			side1.setY(rectangle.getY() - SCALE_FACTOR * (rectangle.getHeight()));
			side1.setRadius(rectangle.getHeight() + 2 * SCALE_FACTOR * rectangle.getHeight());
			side1.setAngle(SIDE_ANGLE); //good
			side2.setX(rectangle.getX() + rectangle.getWidth()); //good
			side2.setY(rectangle.getY() - SCALE_FACTOR * (rectangle.getHeight()));
			side2.setRadius(rectangle.getHeight() + 2 * SCALE_FACTOR * rectangle.getHeight());
			side2.setAngle(SIDE_ANGLE); //good
//		side2.setX(side.getRightLine().getWidth()+ side.getRightLine().getX());
//		side2.setY(side.getRightLine().getY());
//		side2.setRadius(bridgeSideRadius);
//		side2.setAngle(bridgeSideAngle);
			} catch (AnImpossibleAngle e) {
				System.out.println(e.getMessage());
			}
	}

	public static void main(String[] args) {
		final long timeBetween = 1000;
		BuildGorge gorge = new BuildGorgeShape();
    	OEFrame anOEFrame = ObjectEditor.edit(gorge);
    	ThreadSupport.sleep(timeBetween);
    	anOEFrame.refresh();
	}
}
