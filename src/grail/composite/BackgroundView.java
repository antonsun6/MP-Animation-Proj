package grail.composite;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;

//import java.beans.PropertyChangeListener;
import grail.atomic.RotatingLine;
import grail.atomic.ScalableRectangle;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.PAINT_LISTENER})
@SuppressWarnings("serial")
public class BackgroundView extends Component implements PaintListener{

	private BuildGorge gorge = new BuildGorgeShape();
	private BridgeScene bridgeScene;
	private ObservableBridgeScenePainter painter;

	public BackgroundView() {
		painter = FactoryClass.observableBridgeScenePainterFactoryMethod();
		bridgeScene = FactoryClass.bridgeSceneFactoryMethod();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		painter.repaint();
	}

//	public void draw(Graphics g, Avatar avatar) {
//		draw(g, avatar.getHead());
//		draw(g, avatar.getTorso());
//		draw(g, avatar.getArms());
//		draw(g, avatar.getLegs());
//		draw(g, avatar.getStringShape());
//	}

	public void draw(Graphics g, ScalableRectangle rect) {
		g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}

	public void draw(Graphics g, RotatingLine line) {
		g.drawLine(line.getX(), line.getY(), line.getX() + line.getWidth(), line.getY() + line.getHeight());
	}




	@Override
	public void paint(Graphics2D g) {
		draw(g, gorge.getRectangle());
		draw(g, bridgeScene.getGuardArea());
		draw(g, bridgeScene.getKnightArea());
		draw(g, gorge.getSide1());
		draw(g, gorge.getSide2());
	}

}
