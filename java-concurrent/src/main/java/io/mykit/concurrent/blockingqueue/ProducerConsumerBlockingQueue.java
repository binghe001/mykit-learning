package io.mykit.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerBlockingQueue {

	public static void main(String[] args) {
		
		//Create shared object
		BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();
		
		Producer producer = new Producer(sharedQueue);
		Consumer consumer1 = new Consumer(sharedQueue);
		Consumer consumer2 = new Consumer(sharedQueue);
		
		new Thread(producer).start();
		new Thread(consumer1).start();
		new Thread(consumer2).start();
	}
	
}
