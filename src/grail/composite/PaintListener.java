package grail.composite;

import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.PAINT_LISTENER,Comp301Tags.OBSERVABLE_BRIDGE_SCENE_PAINTER})
public interface PaintListener extends PropertyChangeListener{
	void paint (Graphics2D g);
}
