package io.mykit.concurrent.blockingqueue.delayed;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class DelayQueueProducer {

	private BlockingQueue queue;
	private final Random random = new Random();

	private static final String emailBody = "Email body text with delay :: ";

	public DelayQueueProducer(BlockingQueue queue) {
		super();
		this.queue = queue;
	}

	private Thread producerThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					int delay = random.nextInt(10000);
					String receipient = UUID.randomUUID().toString() + "@gmail.com";
					Email email = new Email(receipient, emailBody + delay, delay);

					System.out.printf("Put emial in a DelayQueue = %s%n", email);
					queue.put(email);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}, "Producer Thread");

	public void start() {
		this.producerThread.start();
	}
}
