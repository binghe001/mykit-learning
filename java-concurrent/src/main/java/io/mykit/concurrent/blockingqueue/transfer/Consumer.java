package io.mykit.concurrent.blockingqueue.transfer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;


class Consumer implements Runnable {

	private TransferQueue<String> transferQueue;

	private String name;

	private int numberOfMessagesToConsume;

	public AtomicInteger numberOfConsumedMessages = new AtomicInteger();

	public Consumer(TransferQueue<String> queue, String name, int num) {
		this.name  = name;
		this.transferQueue  = queue;
		this.numberOfMessagesToConsume = num;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < numberOfMessagesToConsume; i++) {
			try {
				String element = transferQueue.take();
				longProcessing(element);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void longProcessing(String element) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + "---Consumed msg: " +element);
		numberOfConsumedMessages.incrementAndGet();
		Thread.sleep(500);
	}

 }