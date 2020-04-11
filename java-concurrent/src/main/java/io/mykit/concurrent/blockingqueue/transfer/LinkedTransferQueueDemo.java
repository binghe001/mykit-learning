package io.mykit.concurrent.blockingqueue.transfer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
public class LinkedTransferQueueDemo {
   static LinkedTransferQueue<String> lnkTransQueue = new LinkedTransferQueue<String>();
   public static void main(String[] args) {
	    ExecutorService exService = Executors.newFixedThreadPool(2);
	    Producer producer = new LinkedTransferQueueDemo().new Producer();
	    Consumer consumer = new LinkedTransferQueueDemo().new Consumer();
		exService.execute(producer);
		exService.execute(consumer);
		exService.shutdown();
   }
   class Producer implements Runnable{
		@Override
		public void run() {
			for(int i=0;i<3;i++){
				try {
					System.out.println("Producer is waiting to transfer...");
					lnkTransQueue.transfer("A"+i);
					System.out.println("producer transfered element: A"+i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}	
   }
   class Consumer implements Runnable{
		@Override
		public void run() {
			for(int i=0;i<3;i++){
				try {
					System.out.println("Consumer is waiting to take element...");
					String s= lnkTransQueue.take();
		     		        System.out.println("Consumer received Element: "+s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
  }
}
