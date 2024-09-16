package grail.composite;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.COORDINATED_ANIMATING_COMMAND})
public class CoordinatedAnimatingCommand extends AnimatingCommand{

	public CoordinatedAnimatingCommand(Avatar anAvatar, AvatarAnimator aCoordinatingAnimator, boolean shouldWaitProceed) {
		super(anAvatar, aCoordinatingAnimator, shouldWaitProceed);
	}
	@Override
	public void run() {
		if(shouldWait) {
			manager.waitForProceed();
		}
		avatarAnimator.animateAvatar(avatar);
	}

}
