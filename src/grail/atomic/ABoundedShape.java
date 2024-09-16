package grail.atomic;
import java.beans.PropertyChangeEvent;

import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@PropertyNames({"X", "Y", "Width", "Height"})
@EditablePropertyNames({"X", "Y", "Width", "Height"})
@Tags({Comp301Tags.BOUNDED_SHAPE})
public abstract class ABoundedShape extends ALocatable implements BoundedShape{
	int width;
	int height;
	@Override
	public int getWidth() {
		return width;
	}
	@Override
	public void setWidth(int newWidth) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "width", width, newWidth);
		width = newWidth;
		notifyAllObservers(propertyChangeEvent);
	}
	@Override
	public int getHeight() {
		return height;
	}
	@Override
	public void setHeight(int newHeight) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "height", height, newHeight);
		height = newHeight;
		notifyAllObservers(propertyChangeEvent);
	}
}
