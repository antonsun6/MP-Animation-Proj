package grail.composite;


import grail.atomic.BoundedShape;
import grail.atomic.RotatingLine;
import tags301.Comp301Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({Comp301Tags.AVATAR})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface Avatar extends BoundedShape {

	Image getHead();
	RotatingLine getTorso();
	Angle getArms();
	Legs getLegs();
	Text getStringShape();
    void move(int newX, int newY);  
	void scale(double scaleRatio);
	void speech(String text);

}
