package io.mykit.concurrent.blockingqueue.synchronous;

import java.util.concurrent.SynchronousQueue;

public class PutRunnable<T> implements Runnable {
	private T value;
	private SynchronousQueue<T> syncQ;

	PutRunnable(SynchronousQueue<T> syncQ, T value) {
		this.syncQ = syncQ;
		this.value = value;
	}

	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + ":" +"Put " + value);
			syncQ.put(value);
			System.out.println(Thread.currentThread().getName() + ":" + "Returned from put");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}