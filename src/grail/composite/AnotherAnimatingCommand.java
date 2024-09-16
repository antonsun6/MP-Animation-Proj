package grail.composite;


public class AnotherAnimatingCommand extends AnimatingCommand{

	public AnotherAnimatingCommand(Avatar anAvatar, AvatarAnimator anAvatarAnimator, boolean shouldWaitProceed) {
		super(anAvatar, anAvatarAnimator, shouldWaitProceed);
	}

	@Override
	public void run() {
		avatarAnimator.clapAvatar(avatar);
	}
}
