package io.mykit.concurrent.blockingqueue.transfer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable {
	private TransferQueue<String> transferQueue;

	private String name;

	private Integer numberOfMessagesToProduce;

	public AtomicInteger numberOfProducedMessages = new AtomicInteger();

	public Producer(TransferQueue<String> queue, String name, int num) {
		this.name  = name;
		this.transferQueue  = queue;
		this.numberOfMessagesToProduce = num;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < numberOfMessagesToProduce; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + "---Transfer msg: " +"A" + i);

				boolean added = transferQueue.tryTransfer("A" + i, 4000, TimeUnit.MILLISECONDS);
				if (added) {
					numberOfProducedMessages.incrementAndGet();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
 }