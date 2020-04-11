package io.mykit.concurrent.blockingqueue.synchronous;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueMultipleTakersExampleV2 {
	public static void main(String[] args) throws InterruptedException {
		SynchronousQueue sq = new SynchronousQueue();
		Thread takerThread1 = new Thread(new TakerRunnable(sq), "TakerThread1");
		takerThread1.start();

		Thread takerThread2 = new Thread(new TakerRunnable(sq), "TakerThread2");
		takerThread2.start();

		Thread.sleep(1000);

		Thread putThread1 = new Thread(new PutRunnable(sq, "One"), "PutThread1");
		putThread1.start();

		Thread putThread2 = new Thread(new PutRunnable(sq, "Two"), "PutThread2");
		putThread2.start();
	}
}
