package grail.composite;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;

//import java.beans.PropertyChangeListener;
import grail.atomic.RotatingLine;
import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.PAINT_LISTENER})
@SuppressWarnings("serial")
public class AvatarView extends Component implements PaintListener{

	private Avatar avatar;
	private ObservableBridgeScenePainter painter;

	public AvatarView(Avatar anAvatar) {
		painter = FactoryClass.observableBridgeScenePainterFactoryMethod();
		avatar = anAvatar;
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

	public void draw(Graphics g, Image head) {
		java.awt.Image img = Toolkit.getDefaultToolkit().getImage(head.getImageFileName());
		g.drawImage(img, head.getX(), head.getY(), this);
	}
	public void draw(Graphics g, RotatingLine torso) {
		g.drawLine(torso.getX(), torso.getY(), torso.getX() + torso.getWidth(), torso.getY() + torso.getHeight());
	}
	public void draw(Graphics g, Angle limbs) {
//		System.out.println(limbs.getLeftLine().getX()+" ---  "+ limbs.getLeftLine().getY()+" ---"+limbs.getLeftLine().getHeight()+"++++"+limbs.getLeftLine().getWidth()+"++++"+limbs.getLeftLine().getRadius()+"*****"+limbs.getLeftLine().getAngle());
		g.drawLine(limbs.getX(), limbs.getY(), limbs.getX()+limbs.getLeftLine().getWidth(), limbs.getY()+limbs.getLeftLine().getHeight());
		g.drawLine(limbs.getX(), limbs.getY(), limbs.getX()+limbs.getRightLine().getWidth(),limbs.getY()+limbs.getRightLine().getHeight());
	}

	public void draw(Graphics g, Legs leg) {
//		System.out.println("Avatar "+leg.toString());
		g.drawLine(leg.getX(), leg.getY(), leg.getX()+leg.getLeftLeg().getWidth(), leg.getY()+leg.getLeftLeg().getHeight());
		g.drawLine(leg.getX(), leg.getY(), leg.getX()+leg.getRightLeg().getWidth(), leg.getY()+leg.getRightLeg().getHeight());
	}

	public void draw(Graphics g, Text text) {
		g.drawString(text.getText(), text.getX(), text.getY());
	}

	@Override
	public void paint(Graphics2D g) {
		draw(g, avatar.getHead());
		draw(g, avatar.getTorso());
		draw(g, avatar.getArms());
		draw(g, avatar.getLegs());
		draw(g, avatar.getStringShape());

	}

}
