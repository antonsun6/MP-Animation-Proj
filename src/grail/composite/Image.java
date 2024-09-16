package grail.composite;

import grail.atomic.BoundedShape;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import util.annotations.EditablePropertyNames;
//import util.annotations.PropertyNames;
//import util.annotations.Tags;
//import tags301.Comp301Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public interface Image extends BoundedShape {

	public String getImageFileName();
	public void setImageFileName(String newVal);
}
