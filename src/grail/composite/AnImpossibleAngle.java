package grail.composite;

import java.io.IOException;

import tags301.Comp301Tags;
import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({Comp301Tags.IMPOSSIBLE_ANGLE})
public class AnImpossibleAngle extends IOException{

	public AnImpossibleAngle(String message) {
		super(message);
	}
}
