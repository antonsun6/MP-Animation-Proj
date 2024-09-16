package grail.atomic;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.BOUNDED_SHAPE})
public interface BoundedShape extends Locatable{
	int getWidth();
	void setWidth(int newVal);
	int getHeight();
	void setHeight(int newVal);

}
