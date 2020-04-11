package io.mykit.concurrent.blockingqueue.priority;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestPriorityBlockingQueue {

	public static void main(String[] args) {
		final BlockingQueue<Order> priorityBlockingQueue = new LinkedBlockingQueue<Order>();

		for (int i = 0; i < 10; i++) {
			AddOrderThread queueProducer = new AddOrderThread(priorityBlockingQueue);
			new Thread(queueProducer).start();
		}

		for (int i = 0; i < 10; i++) {
			ShippingOrderThread queueConsumer = new ShippingOrderThread(priorityBlockingQueue);
			new Thread(queueConsumer).start();
		}

	}
}