package io.mykit.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

	private final Logger logger = Logger.getLogger(Producer.class.getName());

	private final BlockingQueue<Integer> sharedQueue;

	public Producer(BlockingQueue<Integer> sharedQueue) {
		super();
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("Produced: " + this.toString() + " " + i);
				sharedQueue.put(i);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
	}
}
