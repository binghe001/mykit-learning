package io.mykit.concurrent.blockingqueue.diy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class LinkedBlockingQueueV1<E> {
	private final Queue<E> queue = new LinkedList<E>();
	private final int capacity;
	private final AtomicInteger count = new AtomicInteger(0);

	public LinkedBlockingQueueV1(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("The capacity of the queue must be > 0.");
		this.capacity = capacity;
	}

	public int size() {
		return count.get();
	}

	public synchronized void add(E e) throws InterruptedException {
		if (e == null)
			throw new NullPointerException("Null element is not allowed.");

		int oldCount = -1;
		while (count.get() == capacity)
			wait();

		queue.add(e);
		oldCount = count.getAndIncrement();
		if (oldCount == 0) {
			notifyAll(); // notify other waiting threads (could be producers or consumers)
		}
	}

	public synchronized E remove() throws InterruptedException {
		E e;

		int oldCount = -1;
		while (count.get() == 0)
			wait();

		e = queue.remove();
		oldCount = count.getAndDecrement();
		if (oldCount == this.capacity) {
			notifyAll(); // notify other waiting threads (could be producers or consumers)
		}
		return e;
	}

	/*
	 * Retrieves, but does not remove, the head of this queue, or returns null if
	 * this queue is empty.
	 */
	public E peek() {
		if (count.get() == 0)
			return null;
		synchronized (this) {
			return queue.peek();
		}
	}
}
