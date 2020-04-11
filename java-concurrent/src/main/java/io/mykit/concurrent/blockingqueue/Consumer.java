package io.mykit.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

	private final BlockingQueue<Integer> sharedQueue;

	public Consumer(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		
		while(true) {
			try {
				System.out.println("Consumer: " + this.toString() + " " + sharedQueue.take());
			} catch (InterruptedException e) {
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
}
