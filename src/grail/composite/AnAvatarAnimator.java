package grail.composite;

import tags301.Comp301Tags;
import util.annotations.Tags;
//import lectures.animation.threads_commands.extra.ThreadSupport;
import util.misc.ThreadSupport;

@Tags({Comp301Tags.ANIMATOR})
public class AnAvatarAnimator implements AvatarAnimator {

	int animateX;
	int animateY;
	int animationStep;
	int animationPauseTime;

	public AnAvatarAnimator(int animateToX, int animateToY, int anAnimationStep, int anAnimationPauseTime) {
		animateX = animateToX;
		animateY = animateToY;
		animationStep = anAnimationStep;
		animationPauseTime = anAnimationPauseTime;
	}

	public AnAvatarAnimator() {
	}

	@Override
	public synchronized void animateAvatar(Avatar avatar) {
//		avatar.move(animateX, animateY);
//		System.out.println(Thread.currentThread() + " started animating in Y Direction in:" + this);
		// make sure we don�t go past final Y position
		int startX = avatar.getHead().getX();
		int startY = avatar.getHead().getY();
		int endX = startX+animateX;
		int endY = startY + animateY;
		
		System.out.println("startX: "+startX+" animateX: "+ animateX+" In thread:"+ Thread.currentThread());
		while (startX < endX || startY < endY) {
			ThreadSupport.sleep(animationPauseTime);
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

	@Override
	public synchronized void clapAvatar(Avatar avatar) {
		double constantV = Math.PI/32;
		double originalLeftAngle = avatar.getArms().getLeftLine().getAngle();
		double originalRightAngle = avatar.getArms().getRightLine().getAngle();
		double startLeftUnit = originalLeftAngle;
		double startRightUnit = originalRightAngle;
//		System.out.println("left/right start angle: "+startLeftUnit+ " --- "+startRightUnit);
		while (startLeftUnit > 16*constantV || startRightUnit < 16*constantV) {

			ThreadSupport.sleep(animationPauseTime); 
			if(startLeftUnit > 16*constantV) {
				avatar.getArms().getLeftLine().rotate(-animationStep);
			}
			if(startRightUnit < 16*constantV) {
				avatar.getArms().getRightLine().rotate(animationStep);
			}



			startLeftUnit = avatar.getArms().getLeftLine().getAngle();
			startRightUnit = avatar.getArms().getRightLine().getAngle();



		}
//		System.out.println("left/right original angle: "+originalLeftAngle+ " --- "+originalRightAngle);
		try {
			avatar.getArms().getLeftLine().setAngle(originalLeftAngle);
		    avatar.getArms().getRightLine().setAngle(originalRightAngle);
		    avatar.move(avatar.getHead().getX(), avatar.getHead().getY());
		}catch(AnImpossibleAngle e) {}
//		System.out.println("After left/right original angle: "+originalLeftAngle+ " --- "+originalRightAngle);

	}


}
