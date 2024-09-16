package grail.composite;

import grail.atomic.BoundedShape;
//import util.annotations.EditablePropertyNames;
//import util.annotations.PropertyNames;
//import util.annotations.Tags;
//import tags301.Comp301Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
public interface Text extends BoundedShape {

	public String getText();
	public void setText(String newVal);
}
