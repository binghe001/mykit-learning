package io.mykit.concurrent.blockingqueue.linked;

import java.util.concurrent.LinkedBlockingQueue;

public class TicketConsumer implements Runnable {
  private LinkedBlockingQueue queue;
  private TicketProducer ticketProducer;

  public TicketConsumer(LinkedBlockingQueue queue,
		TicketProducer ticketProducer) {
	this.queue = queue;
	this.ticketProducer = ticketProducer;
  }

  @Override
  public void run() {

	while (ticketProducer.isRunning()) {

	  try {
		System.out.println("Removing Ticket: " + queue.take());

		Thread.sleep(2000);
	  } catch (InterruptedException e) {
		e.printStackTrace();
	  }
	}

	System.out.println("TicketConsumer completed.");
  }
}