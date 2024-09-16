package grail.composite;

import grail.atomic.Locatable;
import grail.atomic.RotatingLine;
import tags301.Comp301Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
//import grail.components.ARotatingLine;

@Tags({Comp301Tags.LEGS, Comp301Tags.ANGLE})

@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public interface Angle extends Locatable{

	RotatingLine getRightLine();
	RotatingLine getLeftLine();

	@Override
	int getX();
	@Override
	void setX(int newX);
	@Override
	int getY();
	@Override
	void setY(int newY);
	void move(int offsetX, int offsetY);

}
