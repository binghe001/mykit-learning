package io.mykit.concurrent.blockingqueue.delayed;

import java.util.concurrent.BlockingQueue;

public class DelayQueueConsumer {

	private String name;
	private BlockingQueue queue;

	public DelayQueueConsumer(String name, BlockingQueue queue) {
		super();
		this.name = name;
		this.queue = queue;
	}

	private Thread consumerThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					// Take the email out from the DelayQueue object and send the mail
					Email emial = (Email) queue.take();
					System.out.printf("[%s] - Sending mail when delay is over = %s%n", Thread.currentThread().getName(),
							emial);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});

	public void start() {
		this.consumerThread.setName(name);
		this.consumerThread.start();
	}

}