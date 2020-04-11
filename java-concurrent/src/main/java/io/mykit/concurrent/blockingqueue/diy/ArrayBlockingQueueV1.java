package io.mykit.concurrent.blockingqueue.diy;

public class ArrayBlockingQueueV1<E> {

	final Object[] items;

	int takeIndex;

	int putIndex;

	int count;

	/**
	 * Returns item at index i.
	 */
	@SuppressWarnings("unchecked")
	final E itemAt(int i) {
		return (E) items[i];
	}

	public ArrayBlockingQueueV1(int capacity, boolean fair) {
		if (capacity <= 0)
			throw new IllegalArgumentException();
		this.items = new Object[capacity];

	}

	public ArrayBlockingQueueV1(int capacity) {
		this(capacity, false);
	}

	public synchronized boolean put(E e) throws InterruptedException {
		checkNotNull(e);
		try {
			while (count == items.length)
				this.wait();
			items[putIndex] = e;
			if (++putIndex == items.length)
				putIndex = 0;
			count++;
			this.notifyAll();

			return true;
		} finally {
		}
	}

	public synchronized E take() throws InterruptedException {
		try {
			while (count == 0)
				this.wait();
			E x = (E) items[takeIndex];
			items[takeIndex] = null;
			if (++takeIndex == items.length)
				takeIndex = 0;
			count--;

			this.notifyAll();
			return x;

		} finally {
		}
	}

	private static void checkNotNull(Object v) {
		if (v == null)
			throw new NullPointerException();
	}
}
