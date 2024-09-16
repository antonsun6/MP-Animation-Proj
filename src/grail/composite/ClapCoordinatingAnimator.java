package grail.composite;

import tags301.Comp301Tags;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({Comp301Tags.COORDINATED_ANIMATOR})
public class ClapCoordinatingAnimator extends AnAvatarAnimator{

	BroadcastingClearanceManager manager = FactoryClass.broadcastingClearanceManagerFactoryMethod();
	int steps;

	public ClapCoordinatingAnimator(int animationStep) {
		steps = animationStep;
	}

	@Override
	public synchronized void animateAvatar(Avatar avatar) {
		double downArm = Math.PI / 2;
		double upleftArm = Math.PI-0.2;
		double upRightArm = 0+0.2;
		double originalLeftAngle = avatar.getArms().getLeftLine().getAngle();
		double originalRightAngle = avatar.getArms().getRightLine().getAngle();
//		double startLeftUnit = originalLeftAngle;
//		double startRightUnit = originalRightAngle;

		for(int i = 0; i <= steps; i++) {
			manager.proceedAll();
			try {
				if (i%2==0) {
					avatar.getArms().getLeftLine().setAngle(downArm+0.2);
				    avatar.getArms().getRightLine().setAngle(downArm-0.2);
				}else {

				    avatar.getArms().getLeftLine().setAngle(upleftArm);
				    avatar.getArms().getRightLine().setAngle(upRightArm);
//				    System.out.println("left up angle: "+ avatar.getArms().getLeftLine().getAngle());
				}
			    ThreadSupport.sleep(200);
			    avatar.move(avatar.getHead().getX(), avatar.getHead().getY());
			} catch(AnImpossibleAngle e) {}
		}

		try {
			avatar.getArms().getLeftLine().setAngle(originalLeftAngle);
		    avatar.getArms().getRightLine().setAngle(originalRightAngle);
		    avatar.move(avatar.getHead().getX(), avatar.getHead().getY());
		}catch(AnImpossibleAngle e) {}

	}
}

