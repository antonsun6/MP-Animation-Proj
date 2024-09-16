package grail.composite;

import tags301.Comp301Tags;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({Comp301Tags.COORDINATED_ANIMATOR})
public class CoordinatingAnimator extends AnAvatarAnimator{

	BroadcastingClearanceManager manager = FactoryClass.broadcastingClearanceManagerFactoryMethod();

	public CoordinatingAnimator(int animateToX, int animateToY, int anAnimationStep, int anAnimationPauseTime) {
		super(animateToX, animateToY, anAnimationStep, anAnimationPauseTime);
	}

	@Override
	public synchronized void animateAvatar(Avatar avatar) {
//		avatar.move(animateX, animateY);
		System.out.println(Thread.currentThread() + " started animating in Y Direction in:" + this);
		// make sure we don�t go past final Y position
		int startX = avatar.getHead().getX();
		int startY = avatar.getHead().getY();
		int endX = startX+animateX;
		int endY = startY + animateY;
		
//		System.out.println("startX: "+startX+" animateX: "+ animateX+" In thread:"+ Thread.currentThread());
		while (startX < endX || startY < endY) {
			manager.waitForProceed();
			startX += animationStep;
			startY += animationStep;
			int currX, currY;
			if(startX > endX) {
				currX = endX;
			} else {
				currX = startX;
			}
			if(startY > endY) {
				currY = endY;
			} else {
				currY = startY;
			}
			System.out.println("Inside loop, startX: "+startX+" animateX: "+ animateX);
			avatar.move(currX, currY);
		}
		 

//		System.out.println(Thread.currentThread() + " finished animating in Y Direction in:" + this);
	}
}
