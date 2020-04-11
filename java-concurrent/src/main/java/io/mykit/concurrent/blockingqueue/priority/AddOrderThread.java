package io.mykit.concurrent.blockingqueue.priority;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class AddOrderThread implements Runnable {

	BlockingQueue<Order> orderQueue;

	public AddOrderThread(BlockingQueue<Order> orderQueue) {
		this.orderQueue = orderQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Order order = getNextPalcedOrder();
				System.out.println("----- New Order -------Adding to Queue: " + order);
				orderQueue.put(order);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This is Just a Mock method which is returning a Order with random Data
	 * 
	 * @return
	 */
	private Order getNextPalcedOrder() {
		Integer orderId = getRandomInRange(10000, 1);
		String item = Inventory.getRandomItem();
		Integer priority = getRandomInRange(3, 1);
		priority = priority == 0 ? PRIORITY.NORMAL.value() : priority;
		Order order = new Order(orderId, item, PRIORITY.fromValue(priority));
		order.setShippingAddress("Shipping Address");
		return order;
	}

	private int getRandomInRange(int maximum, int minimum) {
		return (new Random()).nextInt(maximum - minimum + 1) + minimum;
	}
}