package io.mykit.concurrent.blockingqueue.diy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingQueue<E> {
	private final Queue<E> queue = new LinkedList<E>();
	private final int capacity;
	private final AtomicInteger count = new AtomicInteger(0);

	private final ReentrantLock putLock = new ReentrantLock();
	private final ReentrantLock takeLock = new ReentrantLock();

	private final Condition notFull = putLock.newCondition();
	private final Condition notEmpty = takeLock.newCondition();

	public LinkedBlockingQueue(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("The capacity of the queue must be > 0.");
		this.capacity = capacity;
	}

	public int size() {
		return count.get();
	}

	public void add(E e) throws InterruptedException {
		if (e == null)
			throw new NullPointerException("Null element is not allowed.");

		int oldCount = -1;
		putLock.lock();
		try {
			// we use count as a wait condition although count isn't protected by a lock
			// since at this point all other put threads are blocked, count can only
			// decrease (via some take thread).
			while (count.get() == capacity)
				notFull.await();

			queue.add(e);
			oldCount = count.getAndIncrement();
			if (oldCount + 1 < capacity) {
				notFull.signal(); // notify other producers for count change
			}
		} finally {
			putLock.unlock();
		}

		// notify other waiting consumers
		if (oldCount == 0) {
			takeLock.lock();
			try {
				notEmpty.signal();
			} finally {
				takeLock.unlock();
			}
		}
	}

	public E remove() throws InterruptedException {
		E e;

		int oldCount = -1;
		takeLock.lock();
		try {
			while (count.get() == 0)
				notEmpty.await();

			e = queue.remove();
			oldCount = count.getAndDecrement();
			if (oldCount > 1) {
				notEmpty.signal(); // notify other consumers for count change
			}
		} finally {
			takeLock.unlock();
		}

		// notify other waiting producers
		if (oldCount == capacity) {
			putLock.lock();
			try {
				notFull.signal();
			} finally {
				putLock.unlock();
			}
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

		takeLock.lock();
		try {
			return queue.peek();
		} finally {
			takeLock.unlock();
		}
	}
}
