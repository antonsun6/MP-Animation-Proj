package grail.composite;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.ANIMATOR})
public interface AvatarAnimator {

	void animateAvatar(Avatar avatar);

	void clapAvatar(Avatar avatar);

}
