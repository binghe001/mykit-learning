package io.mykit.concurrent.blockingqueue.delayed;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class DelayQueueTest {

 public static void main(String[] args) throws InterruptedException {
   BlockingQueue queue = new DelayQueue();

   new DelayQueueProducer(queue).start();

   new DelayQueueConsumer("Consumer Thread-1", queue).start();
 }
}