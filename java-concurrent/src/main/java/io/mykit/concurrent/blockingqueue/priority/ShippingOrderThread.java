package io.mykit.concurrent.blockingqueue.priority;

import java.util.concurrent.BlockingQueue;

public class ShippingOrderThread implements Runnable {

	BlockingQueue<Order> orderQueue;

	public ShippingOrderThread(BlockingQueue<Order> orderQueue) {
		this.orderQueue = orderQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Order order = orderQueue.take();
				System.out.println("------ SHIPPING ------ Order shipped " + order);
				System.out.println();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}