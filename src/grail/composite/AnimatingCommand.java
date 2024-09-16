package grail.composite;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.ANIMATING_COMMAND})
public class AnimatingCommand implements Runnable{

	AvatarAnimator avatarAnimator;
	Avatar avatar;
//	int animationStep;
//	int animationPauseTime;
	boolean shouldWait;
	BroadcastingClearanceManager manager = FactoryClass.broadcastingClearanceManagerFactoryMethod();

	public AnimatingCommand(Avatar anAvatar, AvatarAnimator anAvatarAnimator, boolean shouldWaitProceed) {
		avatar = anAvatar;
		avatarAnimator = anAvatarAnimator;
//		animationStep = anAnimationStep;
//		animationPauseTime = anAnimationPauseTime;
		shouldWait = shouldWaitProceed;
	}

	@Override
	public void run() {
		if(shouldWait) {
			manager.waitForProceed();
		}
		avatarAnimator.animateAvatar(avatar);
	}
}
