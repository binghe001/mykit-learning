package io.mykit.concurrent.blockingqueue.synchronous;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueMultipleTakersExample {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> sq = new SynchronousQueue<String>();
        Thread takerThread1 = new Thread(new TakerRunnable<String>(sq), "TakerThread1");
        takerThread1.start();
         
        Thread takerThread2 = new Thread(new TakerRunnable<String>(sq), "TakerThread2");
        takerThread2.start();
         
        Thread putThread1 = new Thread(new PutRunnable<String>(sq, "One"), "PutThread1");
        putThread1.start();
         
        Thread putThread2 = new Thread(new PutRunnable<String>(sq, "Two"), "PutThread2");
        putThread2.start();
    }
}
