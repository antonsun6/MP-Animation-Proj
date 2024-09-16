package grail.composite;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

//import bus.uigen.OEFrame;
//import bus.uigen.ObjectEditor;
import grail.atomic.ABoundedShape;
import grail.atomic.AScalableRectangle;
import grail.atomic.ScalableRectangle;
import tags301.Comp301Tags;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;
//import util.misc.ThreadSupport;
import util.models.PropertyListenerRegisterer;

@Tags({Comp301Tags.BRIDGE_SCENE})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Arthur", "Lancelot", "Robin", "Galahad", "Guard", 
	"Gorge", "knightArea", "guardArea", "X", "Y", "Height", "Width", 
	"Occupied", "KnightTurn", "pass", "fail"})
public class ABridgeScene extends ABoundedShape implements BridgeScene, PropertyListenerRegisterer{

	static final int ARTHUR_X = 100;
	static final int ARTHUR_Y = 100;
	static final int GALAHAD_X = 100;
	static final int GALAHAD_Y = 300;
	static final int ROBIN_X = 300;
	static final int ROBIN_Y = 100;
	static final int LANCELOT_X = 300;
	static final int LANCELOT_Y = 300;
	static final int GUARD_X = 600;
	static final int GUARD_Y = 480;
	static final int KNIGHT_AREA_X = 400;
	static final int KNIGHT_AREA_Y = 480;
	static final int KNIGHT_AREA_WIDTH = 50;
	static final int KNIGHT_AREA_HEIGHT = 125;
	static final int GUARD_AREA_X = 600;
	static final int GUARD_AREA_Y = 480;
	static final int GUARD_AREA_WIDTH = 50;
	static final int GUARD_AREA_HEIGHT = 125;
	static final int GORGE_RIGHT_X = 900;
	static final int GORGE_RIGHT_Y = 480;
	static final int AVATAR_FALL_X = 925;
	static final int AVATAR_FALL_Y = 700;

	static int threadNumber = 0;
	static final int ANIMATION_STEP = 25;
	static final int ANIMATION_PAUSE = 100;


//	private Avatar arthur, galahad, lancelot, robin, guard;
	private BuildGorge gorge;
	private ScalableRectangle knightArea, guardArea;
	private Table<Avatar> avatarTable;

	private boolean occupied = false;
	private boolean knightTurn = false;
	private boolean pass = false;
	private boolean fail = false;
	private Avatar interactingKnight; //use only in class

	private boolean shouldWait = true;
	private BroadcastingClearanceManager manager = FactoryClass.broadcastingClearanceManagerFactoryMethod();
	
	AvatarAnimator arthurAnimator;
	AvatarAnimator galahadAnimator;
	AvatarAnimator lancelotAnimator;
	AvatarAnimator robinAnimator;

	 

//	private Avatar arthur = new AnAvatar(100, 100, "images/arthur.jpg");
//	private Avatar galahad = new AnAvatar(100, 300, "images/galahad.jpg");
//	private Avatar lancelot = new AnAvatar(300, 100, "images/lancelot.jpg");
//	private Avatar robin = new AnAvatar(300, 300, "images/robin.jpg");
//	private Avatar guard = new AnAvatar(500, 500, "images/guard.jpg");
//	private Image bridge = new ImageShape();


	public ABridgeScene() {
		Image headA = new ImageShape();
		headA.setX(ARTHUR_X);
		headA.setY(ARTHUR_Y);
		headA.setImageFileName("images/arthur.jpg");
		Image headG = new ImageShape();
		headG.setX(GALAHAD_X);
		headG.setY(GALAHAD_Y);
		headG.setImageFileName("images/galahad.jpg");
		Image headL = new ImageShape();
		headL.setX(LANCELOT_X);
		headL.setY(LANCELOT_Y);
		headL.setImageFileName("images/lancelot.jpg");
		Image headR = new ImageShape();
		headR.setX(ROBIN_X);
		headR.setY(ROBIN_Y);
		headR.setImageFileName("images/robin.jpg");
		Image headGu = new ImageShape();
		headGu.setX(GUARD_X);
		headGu.setY(GUARD_Y);
		headGu.setImageFileName("images/guard.jpg");

		gorge = new BuildGorgeShape();
		knightArea = new AScalableRectangle(KNIGHT_AREA_X, KNIGHT_AREA_Y, KNIGHT_AREA_WIDTH, KNIGHT_AREA_HEIGHT);
		guardArea = new AScalableRectangle(GUARD_AREA_X, GUARD_AREA_Y, GUARD_AREA_WIDTH, GUARD_AREA_HEIGHT);

		avatarTable = FactoryClass.avatarTableFactoryMethod();
		avatarTable.put("Arthur", new AnAvatar(headA));
		avatarTable.put("Galahad", new AnAvatar(headG));
		avatarTable.put("Robin", new AnAvatar(headR));
		avatarTable.put("Lancelot", new AnAvatar(headL));
		avatarTable.put("Guard", new AnAvatar(headGu));
		arthurAnimator = new AnAvatarAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE); 
		galahadAnimator = new AnAvatarAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE); 
		lancelotAnimator = new AnAvatarAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE); 
		robinAnimator = new AnAvatarAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE); 
	}

	@Override
	public Avatar getArthur() {
		return avatarTable.get("Arthur");
	}

	@Override
	public Avatar getGalahad() {
		return avatarTable.get("Galahad");
	}

	@Override
	public Avatar getLancelot() {
		return avatarTable.get("Lancelot");
	}

	@Override
	public Avatar getRobin() {
		return avatarTable.get("Robin");
	}

	@Override
	public Avatar getGuard() {
		return avatarTable.get("Guard");
	}

	@Override
	public BuildGorge getGorge() {
		return gorge;
	}

	@Override
	public ScalableRectangle getKnightArea() {
		return knightArea;
	}

	@Override
	public ScalableRectangle getGuardArea() {
		return guardArea;
	}

	@Override
	@Visible(false)
	public Avatar getInteractingKnight() {
		return interactingKnight;
	}

	@Override
	public void setOccupied(boolean newVal) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "occupied", occupied, newVal);
		occupied = newVal;
		notifyAllObservers(propertyChangeEvent);
	}
	@Override
	public void setPass(boolean newVal) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "pass", pass, newVal);
		pass = newVal;
		notifyAllObservers(propertyChangeEvent);
	}
	@Override
	public void setFail(boolean newVal) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "fail", fail, newVal);
		fail = newVal;
		notifyAllObservers(propertyChangeEvent);
	}
	
	 
	@Override
	public void setKnightTurn(boolean newVal) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "knightTurn", knightTurn, newVal);
		knightTurn = newVal;
		notifyAllObservers(propertyChangeEvent);
	}

	@Override
	public void approach(Avatar avatar) {
			assert preApproach() : "Already occupied";
			setOccupied(true);
			setPass(true);
			setFail(true); 
			
			try {
				avatar.getLegs().getLeftLeg().setAngle((Math.PI)/3);
			} catch(AnImpossibleAngle e) {
				System.out.println(e.getMessage());
			}
			avatar.move(KNIGHT_AREA_X, KNIGHT_AREA_Y);
			interactingKnight = avatar;
	//		System.out.println("the size of listeners: "+ getPropertyChangeListeners().size());
			PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "this", "approach", false);
			notifyAllObservers(propertyChangeEvent);
			PropertyChangeEvent propertyChangeEvent1 = new PropertyChangeEvent(this, "this", "say" , true);
			notifyAllObservers(propertyChangeEvent1);
			PropertyChangeEvent propertyChangeEvent2 = new PropertyChangeEvent(this, "this", "passed" , true);
			notifyAllObservers(propertyChangeEvent2);
			PropertyChangeEvent propertyChangeEvent3 = new PropertyChangeEvent(this, "this", "failed" , true);
			notifyAllObservers(propertyChangeEvent3);

	}

	@Override
	public void approach(String name) {
		assert preApproach() : "Already occupied";
		approach(avatarTable.get(name));
	}

	@Override
	public boolean getOccupied() {
		return occupied;
	}
	@Override
	public boolean getPass() {
		return pass;
	}
	@Override
	public boolean getFail() {
		return fail;
	}

	@Override
	public boolean getKnightTurn() {
		return knightTurn;
	}

	@Override
	public void say(String speech) {
		assert preSay() : "not speech turn"; 
		
		if (knightTurn) {
//			System.out.println("scene Knight say: "+ speech);
			interactingKnight.speech(speech);
			getGuard().speech("");
			setPass(true);
			setFail(true);
			setKnightTurn(false);
			PropertyChangeEvent propertyChangeEvent1 = new PropertyChangeEvent(this, "this", "passed" , true);
			notifyAllObservers(propertyChangeEvent1);
		} else {
//			System.out.println("scene Guard say: "+ speech);
			getGuard().speech(speech);
			interactingKnight.speech("");
			setPass(false);
			setFail(false);
			setKnightTurn(true);
     		PropertyChangeEvent propertyChangeEvent1 = new PropertyChangeEvent(this, "this", "passed" , false);
			notifyAllObservers(propertyChangeEvent1);
		}
//		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "this", "say" , true);
//		notifyAllObservers(propertyChangeEvent);
	}

	@Override
	public void passed() {

		assert prePassed();
		if (!knightTurn) {
			 
			interactingKnight.move(GORGE_RIGHT_X, GORGE_RIGHT_Y);
			setOccupied(false);			
			setKnightTurn(false);
			setPass(false);
			setFail(false);
			PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "this", "approach", true);
			notifyAllObservers(propertyChangeEvent);
			PropertyChangeEvent propertyChangeEvent1 = new PropertyChangeEvent(this, "this", "say" , false);
			notifyAllObservers(propertyChangeEvent1);
			PropertyChangeEvent propertyChangeEvent2 = new PropertyChangeEvent(this, "this", "passed" , false);
			notifyAllObservers(propertyChangeEvent2);
			PropertyChangeEvent propertyChangeEvent3 = new PropertyChangeEvent(this, "this", "failed" , false);
			notifyAllObservers(propertyChangeEvent3);
			
		}  
	}

	@Override
	public void failed() {

		assert preFailed();
		if (knightTurn) {
			// TODO need to add rotate
			getGuard().move(AVATAR_FALL_X, AVATAR_FALL_Y);
			setOccupied(false);
//			setPass(false);
//			setFail(false);
//			PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "this", "failed" , false);
//			notifyAllObservers(propertyChangeEvent);
		} else {
			// TODO need to add rotate
			interactingKnight.move(AVATAR_FALL_X, AVATAR_FALL_Y);
			setOccupied(false);
//			PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "this", "failed", false);
//			notifyAllObservers(propertyChangeEvent);
			setKnightTurn(false);
			setPass(false);
			setFail(false);
//			PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "this", "failed" , false);
//			notifyAllObservers(propertyChangeEvent);
		}
		PropertyChangeEvent propertyChangeEvent1 = new PropertyChangeEvent(this, "this", "approach" , true);
		notifyAllObservers(propertyChangeEvent1);
		PropertyChangeEvent propertyChangeEvent2 = new PropertyChangeEvent(this, "this", "say" , false);
		notifyAllObservers(propertyChangeEvent2);
		PropertyChangeEvent propertyChangeEvent3 = new PropertyChangeEvent(this, "this", "passed" , false);
		notifyAllObservers(propertyChangeEvent3);
		PropertyChangeEvent propertyChangeEvent4 = new PropertyChangeEvent(this, "this", "failed" , false);
		notifyAllObservers(propertyChangeEvent4);
	}

	@Override
	public boolean preApproach() {
		return !getOccupied();
	}

	@Override
	public boolean preSay() {
		return getOccupied();
	}

	@Override
	public boolean prePassed() {
		return getPass();
	}

	@Override
	public boolean preFailed() {
		return getFail();
	}

	public void scroll(int newX, int newY) {
		PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "this", "scoll", true);
		setX(newX);
		setY(newY);
		notifyAllObservers(propertyChangeEvent);
	}

	public static void addPropertyChangeListener(BridgeScene scene, PropertyChangeListener aListener) {

	}

	
	@Override
	public void asynchronousArthur() {
		    Thread aThread = new Thread(new AnimatingCommand(getArthur(), arthurAnimator, !shouldWait));

			threadNumber++;
			aThread.setName("Child thread" + " " + threadNumber);

			/*
			 * Comment this out when asked:
			 */
			aThread.start();
			System.out.println ("Thread:" + Thread.currentThread() + " has started " + aThread);
	}
	 
	@Override
	public void asynchronousGalahad() {
		    Thread aThread = new Thread(new AnimatingCommand(getGalahad(), galahadAnimator, !shouldWait));

			threadNumber++;
			aThread.setName("Child thread" + " " + threadNumber);

			/*
			 * Comment this out when asked:
			 */
			aThread.start();
			System.out.println ("Thread:" + Thread.currentThread() + " has started " + aThread);

	}

	@Override
	public void asynchronousLancelot() {
		    Thread aThread = new Thread(new AnimatingCommand(getLancelot(), lancelotAnimator, !shouldWait));

			threadNumber++;
			aThread.setName("Child thread" + " " + threadNumber);

			/*
			 * Comment this out when asked:
			 */
			aThread.start();
			System.out.println ("Thread:" + Thread.currentThread() + " has started " + aThread);
		}

	@Override
	public void asynchronousRobin() {
		    Thread aThread = new Thread(new AnimatingCommand(getRobin(), robinAnimator, !shouldWait));

			threadNumber++;
			aThread.setName("Robin animation is one of" + " " + threadNumber);

			/*
			 * Comment this out when asked:
			 */
			aThread.start();
			System.out.println ("Thread:" + Thread.currentThread() + " has started " + aThread);
	}

	@Override
	public void asynchronousGuard() {
		    Thread aThread = new Thread(new AnotherAnimatingCommand(getGuard(), new AnAvatarAnimator(getGuard().getHead().getX(), getGuard().getHead().getY(),2, 200), !shouldWait));
  
			threadNumber++;
			aThread.setName("Guard animation is one of" + " " + threadNumber);

			/*
			 * Comment this out when asked:
			 */
			aThread.start();
			System.out.println ("Thread:" + Thread.currentThread() + " has started " + aThread);
	}

	@Override
	public void waitingArthur() {
		Thread aThread = new Thread(new AnimatingCommand(getArthur(), new AnAvatarAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE), shouldWait));
		threadNumber++;
		aThread.setName("child thread" + " " + threadNumber);
		aThread.start();
	}
	@Override
	public void waitingGalahad() {
		Thread aThread = new Thread(new AnimatingCommand(getGalahad(), new AnAvatarAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE), shouldWait));
		threadNumber++;
		aThread.setName("child thread" + " " + threadNumber);
		aThread.start();
	}
	@Override
	public void waitingLancelot() {
		Thread aThread = new Thread(new AnimatingCommand(getLancelot(), new AnAvatarAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE), shouldWait));
		threadNumber++;
		aThread.setName("child thread" + " " + threadNumber);
		aThread.start();
	}
	@Override
	public void waitingRobin() {
		Thread aThread = new Thread(new AnimatingCommand(getRobin(), new AnAvatarAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE), shouldWait));
		threadNumber++;
		aThread.setName("child thread" + " " + threadNumber);
		aThread.start();
	}

	@Override
	public void startAnimation() {
		manager.proceedAll();
	}

	@Override
	public void lockstepArthur() {
		Thread aThread = new Thread(new CoordinatedAnimatingCommand(getArthur(), new CoordinatingAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE), shouldWait));
		threadNumber++;
		aThread.start();
	}
	@Override
	public void lockstepGalahad() {
		Thread aThread = new Thread(new CoordinatedAnimatingCommand(getGalahad(), new CoordinatingAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE), shouldWait));
		threadNumber++;
		aThread.start();
	}
	@Override
	public void lockstepLancelot() {
		Thread aThread = new Thread(new CoordinatedAnimatingCommand(getLancelot(), new CoordinatingAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE), shouldWait));
		threadNumber++;
		aThread.start();
	}
	@Override
	public void lockstepRobin() {
		Thread aThread = new Thread(new CoordinatedAnimatingCommand(getRobin(), new CoordinatingAnimator(50, 50, ANIMATION_STEP, ANIMATION_PAUSE), shouldWait));
		threadNumber++;
		aThread.start();
	}
	@Override
	public void lockstepGuard() {
		Thread aThread = new Thread(new CoordinatedAnimatingCommand(getGuard(), new ClapCoordinatingAnimator(5), !shouldWait));

		threadNumber++;
		aThread.setName("Guard animation is one of" + " " + threadNumber);

		/*
		 * Comment this out when asked:
		 */
		aThread.start();
		System.out.println ("Thread:" + Thread.currentThread() + " has started " + aThread);
	}


}
