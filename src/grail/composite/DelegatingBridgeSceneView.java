package grail.composite;

//import java.awt.Component;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.DELEGATING_BRIDGE_SCENE_VIEW})
public class DelegatingBridgeSceneView{

	BridgeScene scene = FactoryClass.bridgeSceneFactoryMethod();
	ObservableBridgeScenePainter bridgeScenePainter = FactoryClass.observableBridgeScenePainterFactoryMethod();

	AvatarView arthurView = new AvatarView(scene.getArthur());
	AvatarView galahadView = new AvatarView(scene.getGalahad());
	AvatarView lancelotView = new AvatarView(scene.getLancelot());
	AvatarView robinView = new AvatarView(scene.getRobin());
	AvatarView guardView = new AvatarView(scene.getGuard());

	public DelegatingBridgeSceneView() {

		bridgeScenePainter.addPaintListener(arthurView);
		bridgeScenePainter.addPaintListener(galahadView);
		bridgeScenePainter.addPaintListener(lancelotView);
		bridgeScenePainter.addPaintListener(robinView);
		bridgeScenePainter.addPaintListener(guardView);
		bridgeScenePainter.addPaintListener(new BackgroundView());

//		AnAvatar.addPropertyChangeListener(scene.getArthur(), arthurView);
//		AnAvatar.addPropertyChangeListener(scene.getGalahad(), galahadView);
//		AnAvatar.addPropertyChangeListener(scene.getLancelot(), lancelotView);
//		AnAvatar.addPropertyChangeListener(scene.getRobin(), robinView);
//		AnAvatar.addPropertyChangeListener(scene.getGuard(), guardView);
		scene.getArthur().addPropertyChangeListener(arthurView);
		scene.getGalahad().addPropertyChangeListener(galahadView);
		scene.getLancelot().addPropertyChangeListener(lancelotView);
		scene.getRobin().addPropertyChangeListener(robinView);
		scene.getGuard().addPropertyChangeListener(guardView);

	}
	public ObservableBridgeScenePainter getBridgeScenePainter() {
		return bridgeScenePainter;
	}

}
