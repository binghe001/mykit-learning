package io.mykit.concurrent.blockingqueue.diy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<E> {

	final Object[] items;

	int takeIndex;

	int putIndex;

	int count;

	final ReentrantLock lock;

	private final Condition notEmpty;

	private final Condition notFull;

	/**
	 * Returns item at index i.
	 */
	@SuppressWarnings("unchecked")
	final E itemAt(int i) {
		return (E) items[i];
	}

	public ArrayBlockingQueue(int capacity, boolean fair) {
		if (capacity <= 0)
			throw new IllegalArgumentException();
		this.items = new Object[capacity];
		lock = new ReentrantLock(fair);
		notEmpty = lock.newCondition();
		notFull = lock.newCondition();
	}

	public ArrayBlockingQueue(int capacity) {
		this(capacity, false);
	}

	public boolean put(E e) throws InterruptedException {
		checkNotNull(e);
		lock.lock();
		try {
			while (count == items.length)
				notFull.await();
			items[putIndex] = e;
			if (++putIndex == items.length)
				putIndex = 0;
			count++;
			notEmpty.signalAll();

			return true;
		} finally {
			lock.unlock();
		}
	}

	public E take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			E x = (E) items[takeIndex];
			items[takeIndex] = null;
			if (++takeIndex == items.length)
				takeIndex = 0;
			count--;

			notFull.signalAll();
			return x;

		} finally {
			lock.unlock();
		}
	}

	private static void checkNotNull(Object v) {
		if (v == null)
			throw new NullPointerException();
	}
}
