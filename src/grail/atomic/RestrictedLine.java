package grail.atomic;


import java.beans.PropertyChangeEvent;

import grail.composite.AnImpossibleAngle;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.RESTRICTED_LINE})
public class RestrictedLine extends ARotatingLine implements RotatingLine{

	double minAngle;
	double maxAngle;

	public RestrictedLine(double minimumAngle, double maximumAngle ) {
		super();
		minAngle = minimumAngle;
		maxAngle = maximumAngle;
	}
	public RestrictedLine(double minimumAngle, double maximumAngle, double theRadius, double theAngle) {
		super(theRadius, theAngle);
		minAngle = minimumAngle;
		maxAngle = maximumAngle;
	}

	@Override
	public void setAngle(double theAngle) throws AnImpossibleAngle {
		if (theAngle < minAngle || theAngle > maxAngle) {
			throw new AnImpossibleAngle(theAngle + "is not in the range.");
		}
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "point", point.getAngle(), theAngle);
		point = new APolarPoint(point.getRadius(), theAngle);
		notifyAllObservers(propertyChangeEvent);
//		super.setAngle(theAngle);
	}

}
