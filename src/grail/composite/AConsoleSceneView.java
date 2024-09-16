package grail.composite;

import java.beans.PropertyChangeEvent;

import tags301.Comp301Tags;
import util.annotations.Tags;


@Tags({Comp301Tags.CONSOLE_SCENE_VIEW})
public class AConsoleSceneView implements ConsoleSceneView{
	BridgeScene bridgeScene;
	public AConsoleSceneView() {
		bridgeScene = FactoryClass.bridgeSceneFactoryMethod();
		bridgeScene.addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);

	}
	@Override
	public BridgeScene getBridgeScene() {
		return bridgeScene;
	}
}
