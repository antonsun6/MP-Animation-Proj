package grail.composite;

import grail.atomic.BoundedShape;
import grail.atomic.RotatingLine;
import grail.atomic.ScalableRectangle;
//import util.annotations.EditablePropertyNames;
//import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface BuildGorge extends BoundedShape{

	ScalableRectangle getRectangle();
	RotatingLine getSide1();
	RotatingLine getSide2();

}
