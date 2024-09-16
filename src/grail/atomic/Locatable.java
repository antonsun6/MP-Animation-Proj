package grail.atomic;
import tags301.Comp301Tags;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({Comp301Tags.LOCATABLE})
public interface Locatable extends PropertyListenerRegisterer {
	int getX();
 	void setX(int newX);
 	int getY();
 	void setY(int newY);



}
