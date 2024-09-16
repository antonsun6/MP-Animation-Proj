package grail.composite;

import java.awt.Component;
import java.awt.Graphics;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
//import java.beans.PropertyChangeEvent;
import java.util.List;

import tags301.Comp301Tags;
import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({Comp301Tags.OBSERVABLE_BRIDGE_SCENE_PAINTER})
public class ObservableBridgeScenePainter extends Component //implements PaintListener
{

	private List<PaintListener> paintListeners = new ArrayList<>();


	public void addPaintListener(PaintListener listener) {
		paintListeners.add(listener);
	}


	public List<PaintListener> getPaintListeners() {
		return paintListeners;
	}

	@Override
	public void paint(Graphics g) {
		for(PaintListener listener: paintListeners) {

			listener.paint((Graphics2D) g);
		}
	}



}
