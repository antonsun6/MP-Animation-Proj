package grail.composite;

import javax.swing.JButton;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags(Comp301Tags.FACTORY_CLASS)
public class FactoryClass {


	static BridgeScene scene;
	static ConsoleSceneView consoleScene;
	static ObservableBridgeScenePainter bridgeScenePainter;
	static DelegatingBridgeSceneView bridgeSceneView;
	static BridgeSceneController bridgeSceneController;
	static Table<Avatar> table;
	static BroadcastingClearanceManager aBroadcastingClearanceManager;

	static JButton approachButton = new JButton("Approach");
	static JButton sayButton = new JButton("Say");
	static JButton passedButton = new JButton("Passed");
	static JButton failedButton = new JButton("Failed");

	public static synchronized BridgeScene bridgeSceneFactoryMethod(){
	   if (scene == null) {
			 scene = new ABridgeScene();
	   }
	   return scene;
	}


	public static synchronized ConsoleSceneView consoleSceneViewFactoryMethod() {
		 if (consoleScene == null) {
			 consoleScene = new AConsoleSceneView();
	   }
	   return consoleScene;
	}

	public static synchronized Angle legsFactoryMethod(int x, int y, double leftAngle, double leftRadius, double rightAngle, double rightRadius) {
		Angle angle = new AngleShape();
		try {

			angle.setX(x);
			angle.setY(y);
			angle.getLeftLine().setAngle(leftAngle);
			angle.getLeftLine().setRadius(leftRadius);
			angle.getRightLine().setAngle(rightAngle);
			angle.getRightLine().setRadius(rightRadius);

		} catch(AnImpossibleAngle e) {

		}

		return angle;

	}

	public static synchronized Legs restrictedLegsFactoryMethod(int x, int y, double leftAngle, double leftRadius, double rightAngle, double rightRadius) {
		Legs leg = new Legs();
		try {
			leg.setX(x);
			leg.setY(y);
			leg.getLeftLeg().setAngle(leftAngle);
			leg.getLeftLeg().setRadius(leftRadius);
			leg.getRightLeg().setAngle(rightAngle);
			leg.getRightLeg().setRadius(rightRadius);
		} catch(AnImpossibleAngle e) {
			System.out.println(e.getMessage());
		}
		return leg;

	}

	public  static synchronized ObservableBridgeScenePainter observableBridgeScenePainterFactoryMethod() {
		 if (bridgeScenePainter == null) {
			 bridgeScenePainter = new ObservableBridgeScenePainter();
	   }
	   return bridgeScenePainter;
	}

	public static synchronized DelegatingBridgeSceneView delegatingBridgeSceneViewFactoryMethod() {
		if (bridgeSceneView == null) {
			 bridgeSceneView = new DelegatingBridgeSceneView();
			 }
	    return bridgeSceneView;
	}

	public static synchronized BridgeSceneController bridgeSceneControllerFactoryMethod() {
		 if (bridgeSceneController == null) {
			 bridgeSceneController = new BridgeSceneController(FactoryClass.observableBridgeScenePainterFactoryMethod(), approachButton, sayButton, passedButton, failedButton);
	   }
	   return bridgeSceneController;
	}


	public static synchronized Table<Avatar> avatarTableFactoryMethod() {
		if (table == null) {
			 table = new ATable<>();
	   }
	   return table;

	}

	public static synchronized BroadcastingClearanceManager broadcastingClearanceManagerFactoryMethod() {
		if (aBroadcastingClearanceManager == null) {
			aBroadcastingClearanceManager = new ABroadcastingClearanceManager();
	   }
	   return aBroadcastingClearanceManager;
	}

}
