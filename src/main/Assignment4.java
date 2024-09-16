package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTextField;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import gradingTools.comp301ss21.assignment4.SS21Assignment4Suite;
import grail.composite.Avatar;
//import gradingTools.comp301ss21.assignment4.SS21Assignment4Suite;
import grail.composite.BackgroundView;
//import grail.atomic.ARotatingLine;
//import grail.atomic.RotatingLine;
//import grail.composite.ABridgeScene;
import grail.composite.BridgeScene;
//import grail.composite.BridgeSceneController;
import grail.composite.DelegatingBridgeSceneView;
import grail.composite.FactoryClass;
import grail.composite.ObservableBridgeScenePainter;
import grail.composite.Table;

//import util.misc.ThreadSupport;
//import bus.uigen.OEFrame;
//import bus.uigen.ObjectEditor;

public class Assignment4 {

	final static int TIME_BETWEEN = 1000;
	final static int LONG_TIME_BETWEEN = 5000;

	static JFrame frame = new JFrame("Bridge Scene");
	static JPanel buttonPanel = new JPanel();
//	static JButton approachButton = new JButton("Approach");
//	static JButton sayButton = new JButton("Say");
//	static JButton passedButton = new JButton("Passed");
//	static JButton failedButton = new JButton("Failed");

	static DelegatingBridgeSceneView sceneView = FactoryClass.delegatingBridgeSceneViewFactoryMethod();

	public static void composePanel(JPanel aPanel, JButton approachButton, JButton sayButton, JButton passedButton, JButton failedButton) {
		aPanel.setLayout(new GridLayout(1, 4)); // align the components of aPanel in a row with two columns
		aPanel.add(approachButton);
		aPanel.add(sayButton);
		aPanel.add(passedButton);
		aPanel.add(failedButton);
		approachButton.setEnabled(true);
		sayButton.setEnabled(false);
		passedButton.setEnabled(false);
		failedButton.setEnabled(false);
	}

	public static void composeFrame() {
		frame.add(sceneView.getBridgeScenePainter(), BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
//		frame.setLayout(new GridLayout(4, 4));
//		frame.add(buttonPanel);


	}

	public static void composeMVC() {
		FactoryClass.bridgeSceneControllerFactoryMethod();
//		new BridgeSceneController(sceneView.getBridgeScenePainter(), approachButton, sayButton, passedButton, failedButton);
	}

	public static void main(String[] args) {

//		composePanel(buttonPanel, FactoryClass.approachButton, FactoryClass.sayButton, FactoryClass.passedButton, FactoryClass.failedButton);
//		composeFrame();
//		composeMVC();
//
//		ObservableBridgeScenePainter scenePainter = FactoryClass.observableBridgeScenePainterFactoryMethod();
//		PropertyChangeListener backgroundView = new BackgroundView();
//		Table<Avatar> avatarTable = FactoryClass.avatarTableFactoryMethod();
//
//
//		BridgeScene scene = FactoryClass.bridgeSceneFactoryMethod();
//		OEFrame anOEFrame = ObjectEditor.edit(scene);
//		scene.asynchronousArthur();
//		scene.asynchronousGalahad();
//		scene.asynchronousLancelot();
//		scene.asynchronousRobin();
//		scene.asynchronousGuard();
//
//		scene.waitingArthur();
//		scene.waitingGalahad();
//		scene.waitingLancelot();
//		scene.waitingRobin();

//		scene.lockstepArthur();
//		scene.lockstepGalahad();
//		scene.lockstepLancelot();
//		scene.lockstepRobin();
//		scene.lockstepGuard();
//
//
////		ThreadSupport.sleep(LONG_TIME_BETWEEN);
////
////		scene.startAnimation();
////
////		scene.approach(avatarTable.get("Lancelot"));
//		anOEFrame.refresh();
//
//		ThreadSupport.sleep(TIME_BETWEEN);
//		scene.say("You pass!");
//		anOEFrame.refresh();
//		ThreadSupport.sleep(TIME_BETWEEN);
//		scene.say("Thanks!");
//		anOEFrame.refresh();
//		ThreadSupport.sleep(TIME_BETWEEN);
//		scene.passed();
//		anOEFrame.refresh();
//		ThreadSupport.sleep(TIME_BETWEEN);
//
//		scene.approach(avatarTable.get("Galahad"));
//		anOEFrame.refresh();
//
//		ThreadSupport.sleep(TIME_BETWEEN);
//		scene.say("You fail!");
//		anOEFrame.refresh();
//		ThreadSupport.sleep(TIME_BETWEEN);
//		scene.say("NoOoOoOoOoOOoo!");
//		anOEFrame.refresh();
//		ThreadSupport.sleep(TIME_BETWEEN);
//		scene.failed();
//		anOEFrame.refresh();
//		ThreadSupport.sleep(TIME_BETWEEN);
//
		SS21Assignment4Suite.setRunNewPaintListenerTest(true);

		SS21Assignment4Suite.main(args);

	}
}
