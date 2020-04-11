package io.mykit.concurrent.blockingqueue.delayed;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Ninja Panda
 */
public class Email implements Delayed {

	private String receipient;
	private String mailBody;
	private long startTime;

	public Email(String receipient, String body, long delay) {
		this.receipient = receipient;
		this.mailBody = body;
		this.startTime = System.currentTimeMillis() + delay;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff = startTime - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		if (this.startTime < ((Email) o).startTime) {
			return -1;
		}
		if (this.startTime > ((Email) o).startTime) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Email [receipient=" + receipient + ", mailBody=" + mailBody + ", startTime=" + startTime + "]";
	}
}