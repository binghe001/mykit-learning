package io.mykit.concurrent.blockingqueue.linked;

import java.util.concurrent.LinkedBlockingQueue;

public class TicketProducer implements Runnable {

  private LinkedBlockingQueue queue;
  private boolean running;

  public TicketProducer(LinkedBlockingQueue queue) {
	this.queue = queue;
	running = true;
  }

  public boolean isRunning() {
	return running;
  }

  @Override
  public void run() {

	for (int i = 0; i < 15; i++) {
		String element = "Ticket" + i;

		try {
			queue.put(element);
			System.out.println("Ticket added: " + element);
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	System.out.println("TicketProducer Completed.");
	running = false;
  }

}