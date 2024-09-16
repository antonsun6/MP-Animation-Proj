package grail.composite;

import grail.atomic.BoundedShape;
import grail.atomic.ScalableRectangle;
import tags301.Comp301Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({Comp301Tags.BRIDGE_SCENE})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface BridgeScene extends BoundedShape{
	Avatar getArthur();
	Avatar getGalahad();
	Avatar getGuard();
	Avatar getLancelot();
	Avatar getRobin();
	BuildGorge getGorge();
	ScalableRectangle getKnightArea();
	ScalableRectangle getGuardArea();
	Avatar getInteractingKnight();
	boolean getOccupied();
	void setOccupied(boolean newVal);
	boolean getKnightTurn();

	void approach(Avatar avatar);
	void approach(String name);
	void say(String speech);
	void passed();
	void failed();
	void setKnightTurn(boolean newVal);

	boolean getPass();
	void setPass(boolean newVal);
	boolean getFail();
	void setFail(boolean newVal);
	
	boolean preApproach();
	boolean preSay();
	boolean prePassed();
	boolean preFailed();

	void asynchronousArthur();
	void asynchronousLancelot();
	void asynchronousRobin();
	void asynchronousGalahad();
	void asynchronousGuard();

	void waitingArthur();
	void waitingGalahad();
	void waitingLancelot();
	void waitingRobin();

	public void startAnimation();

	void lockstepArthur();
	void lockstepGalahad();
	void lockstepLancelot();
	void lockstepRobin();
	public void lockstepGuard();
}
