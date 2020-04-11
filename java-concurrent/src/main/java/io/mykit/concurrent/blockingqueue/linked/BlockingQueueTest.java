package io.mykit.concurrent.blockingqueue.linked;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

  public static void main(String[] args) throws InterruptedException {
	LinkedBlockingQueue queue = new LinkedBlockingQueue<>(10);

	TicketProducer producer = new TicketProducer(queue);
	TicketWatcher watcher = new TicketWatcher(queue, producer);
	TicketConsumer consumer = new TicketConsumer(queue, producer);

	Thread producerThread = new Thread(producer);
	Thread watcherThread = new Thread(watcher);
	Thread consumerThread = new Thread(consumer);

	producerThread.start();
	Thread.sleep(2000);
	consumerThread.start();
	Thread.sleep(2000);
	watcherThread.start();
  }
}