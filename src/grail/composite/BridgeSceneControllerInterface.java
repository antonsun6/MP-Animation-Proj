package grail.composite;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.BRIDGE_SCENE_CONTROLLER})
public interface BridgeSceneControllerInterface extends MouseListener, KeyListener{

	JButton getApproach();
	JButton getSay();
	JButton getPassed();
	JButton getFailed();
}
