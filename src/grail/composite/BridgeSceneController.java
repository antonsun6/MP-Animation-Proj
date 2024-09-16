package grail.composite;

//import java.awt.event.MouseListener;
//import java.awt.event.KeyListener;
//import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import tags301.Comp301Tags;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@PropertyNames({"approachButton", "sayButton", "passedButton", "failedButton"})
@Tags({Comp301Tags.BRIDGE_SCENE_CONTROLLER})
public class BridgeSceneController implements BridgeSceneControllerInterface, ActionListener, PropertyChangeListener{

	ObservableBridgeScenePainter scenePainter;
	BridgeScene scene;

	Point lastClickPoint;

	private JButton approachButton;
	private JButton sayButton;
	private JButton passedButton;
	private JButton failedButton;

	List<String> sayList = new ArrayList<>();

	int counter = 0;



	public BridgeSceneController(ObservableBridgeScenePainter painter, JButton theApproachButton, JButton theSayButton, JButton thePassedButton, JButton theFailedButton) {
		scene = FactoryClass.bridgeSceneFactoryMethod();
		scenePainter = painter;
		scenePainter.addMouseListener(this);
		scenePainter.addKeyListener(this);

		approachButton = theApproachButton;
		sayButton = theSayButton;
		passedButton = thePassedButton;
		failedButton = theFailedButton;

		approachButton.addActionListener(this);
		sayButton.addActionListener(this);
		passedButton.addActionListener(this);
		failedButton.addActionListener(this);

		sayList.add("Hello");
		sayList.add("Can I pass?");
		sayList.add("No!");
		sayList.add("Dang it!");

		scene.addPropertyChangeListener(this);
	}
	
   public JButton getApproach() {
	   return approachButton;
   } 	 
   
   public JButton getSay() {
	   return sayButton;
   } 	 
   
   public JButton getPassed() {
	   return passedButton;
   } 	 
   
   public JButton getFailed() {
	   return failedButton;
   } 	 

	@Override
	public void keyTyped(KeyEvent e) {
		char typedChar = e.getKeyChar();
	    switch (typedChar) {
	      case 'a' :
	        scene.getArthur().move((int)lastClickPoint.getX(), (int)lastClickPoint.getY());
	        break;
	      case 'g' :
		    scene.getGalahad().move((int)lastClickPoint.getX(), (int)lastClickPoint.getY());
		    break;
	      case 'l' :
		    scene.getLancelot().move((int)lastClickPoint.getX(), (int)lastClickPoint.getY());
		    break;
		  case 'r' :
			scene.getRobin().move((int)lastClickPoint.getX(), (int)lastClickPoint.getY());
			break;
		  case 'o' :
			scene.getArthur().move(ABridgeScene.ARTHUR_X, ABridgeScene.ARTHUR_Y);
			scene.getGalahad().move(ABridgeScene.GALAHAD_X, ABridgeScene.GALAHAD_Y);
			scene.getLancelot().move(ABridgeScene.LANCELOT_X, ABridgeScene.LANCELOT_Y);
			scene.getRobin().move(ABridgeScene.ROBIN_X, ABridgeScene.ROBIN_Y);
			break;
	    }
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		lastClickPoint = e.getPoint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton source = (JButton) e.getSource();

		if (source == approachButton) {

			if (scene.preApproach()) {
				source.setEnabled(false);
     			scene.approach("Robin");
				sayButton.setEnabled(true);
				
			} else {
				source.setEnabled(true);
			}
		}

		if (source == sayButton) {
			if (scene.preSay()) {
				scene.say(sayList.get(counter));
				System.out.println("After button say: "+ sayList.get(counter));
				counter++;
				if (counter >= sayList.size()) {
					source.setEnabled(false);
					passedButton.setEnabled(true);
					failedButton.setEnabled(true);
					scene.setOccupied(false);
					counter=0;
				}
			} else {
				source.setEnabled(false);
			}
		}

		if (source == passedButton) {
			if (scene.prePassed()) {
				scene.passed();
				approachButton.setEnabled(true);
				sayButton.setEnabled(true);
				source.setEnabled(false);
			} else {
				source.setEnabled(true);
			}
		}

		if (source == failedButton) {
			if (scene.preFailed()) {
				scene.failed();
				approachButton.setEnabled(true);
				sayButton.setEnabled(true);
				source.setEnabled(false);
			} else {
				source.setEnabled(false);
			}
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		approachButton.setEnabled(scene.preApproach());
		sayButton.setEnabled(scene.preSay());
		passedButton.setEnabled(scene.prePassed());
		failedButton.setEnabled(scene.preFailed());
		
	}


}
