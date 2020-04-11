package io.mykit.concurrent.blockingqueue.transfer;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TransferQueueExample {
    public static void main(String[] args) throws Exception {
    	//whenUseOneProducerAndNoConsumers_thenShouldFailWithTimeout();
    	//whenUseOneConsumerAndOneProducer_thenShouldProcessAllMessages();
    	whenMultipleConsumersAndProducers_thenProcessAllMessages();
    }
    
    public static void whenUseOneProducerAndNoConsumers_thenShouldFailWithTimeout()
    		  throws InterruptedException {
    		    TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
    		    ExecutorService exService = Executors.newFixedThreadPool(2);
    		    Producer producer = new Producer(transferQueue, "1", 3);
    		 
    		    exService.execute(producer);
    		 
    		    exService.awaitTermination(5000, TimeUnit.MILLISECONDS);
    		    exService.shutdown();
    		 
    		 }
    
    public static void whenUseOneConsumerAndOneProducer_thenShouldProcessAllMessages() 
    		  throws InterruptedException {
    		    TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
    		    ExecutorService exService = Executors.newFixedThreadPool(2);
    		    Producer producer = new Producer(transferQueue, "1", 3);
    		    Consumer consumer = new Consumer(transferQueue, "1", 3);
    		 
    		    exService.execute(producer);
    		    exService.execute(consumer);
    		 
    		    exService.awaitTermination(5000, TimeUnit.MILLISECONDS);
    		    exService.shutdown();
    		 
    		    System.out.println(producer.numberOfProducedMessages.intValue());
    		    System.out.println(consumer.numberOfConsumedMessages.intValue());
    		}
    
    public static void whenMultipleConsumersAndProducers_thenProcessAllMessages()
    		  throws InterruptedException {
    		    TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
    		    ExecutorService exService = Executors.newFixedThreadPool(3);
    		    Producer producer1 = new Producer(transferQueue, "1", 3);
    		    Producer producer2 = new Producer(transferQueue, "2", 3);
    		    Consumer consumer1 = new Consumer(transferQueue, "1", 3);
    		    Consumer consumer2 = new Consumer(transferQueue, "2", 3);
    		 
    		    exService.execute(producer1);
    		    exService.execute(producer2);
    		    exService.execute(consumer1);
    		    exService.execute(consumer2);
    		 
    		    exService.awaitTermination(10_000, TimeUnit.MILLISECONDS);
    		    exService.shutdown();
    		 
    		    System.out.println(producer1.numberOfProducedMessages.intValue());
    		    System.out.println(producer2.numberOfProducedMessages.intValue());
    		}
}
