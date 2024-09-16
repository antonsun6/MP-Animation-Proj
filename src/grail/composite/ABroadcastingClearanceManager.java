package grail.composite;

import util.annotations.ComponentWidth;
import util.annotations.Row;

//import lectures.animation.threads.wait_notify.ComponentWidth;
//import lectures.animation.threads.wait_notify.Row;

public class ABroadcastingClearanceManager extends AClearanceManager
implements BroadcastingClearanceManager {

	@Override
	@Row(1)
	@ComponentWidth(150)
	public synchronized void proceedAll() {
		notifyAll(); // not notify, hover over the method and see Java's explanation for it
		System.out.println( Thread.currentThread()  + ": after notifyAll");

	}
}
