package grail.atomic;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@PropertyNames({"X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y", "Height", "Width"})
@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
public class AScalableRectangle extends ABoundedShape implements ScalableRectangle {

	public AScalableRectangle(int theX, int theY, int theWidth, int theHeight) {
		x = theX;
		y = theY;
		width = theWidth;
		height = theHeight;
	}



	@Override
	public void scale(int percentage){
		width = (width*percentage)/100;
		height = (height*percentage)/100;
	}
}
