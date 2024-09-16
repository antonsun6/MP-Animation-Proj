package grail.atomic;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import tags301.Comp301Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@PropertyNames({"X", "Y"})
@EditablePropertyNames({"X", "Y"})
@Tags({Comp301Tags.LOCATABLE})
public abstract class ALocatable implements Locatable{

	protected int x;
	protected int y;

	List<PropertyChangeListener> propertyChangeListeners = new ArrayList<>();

	@Override
	public int getX() {
		return x;
	}
	@Override
	public void setX(int newX) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "x", x, newX);
//		System.out.println(propertyChangeEvent);
		x = newX;
		notifyAllObservers(propertyChangeEvent);
	}
	@Override
	public int getY() {
		 return y;
	}
	@Override
	public void setY(int newY) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "y", y, newY);
//		System.out.println(propertyChangeEvent);
		y = newY;
		notifyAllObservers(propertyChangeEvent);
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyChangeListeners.add(arg0);
	}

	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return propertyChangeListeners;

	}

	// Can the notification code be put in another class?
	protected void notifyAllObservers(PropertyChangeEvent propertychangeEvent) {

		for (PropertyChangeListener e: propertyChangeListeners) {
			// Should/does the model know how each observable will react to the new state
			e.propertyChange(propertychangeEvent);
		}
	}
}
