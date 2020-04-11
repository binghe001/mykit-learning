package io.mykit.concurrent.blockingqueue.synchronous;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {
    public static void main(String[] args) throws Exception {
        SynchronousQueue<String> sq = new SynchronousQueue<String>(true);
        Thread putThread = new Thread(new PutRunnable<String>(sq, "One"), "PutThread");
        putThread.start();
        Thread putThread2 = new Thread(new PutRunnable<String>(sq, "Two"), "PutThread2");
        putThread2.start();
        Thread putThread3 = new Thread(new PutRunnable<String>(sq, "Three"), "PutThread3");
        putThread3.start();
        Thread putThread4 = new Thread(new PutRunnable<String>(sq, "Four"), "PutThread4");
        putThread4.start();
        Thread putThread5 = new Thread(new PutRunnable<String>(sq, "Five"), "PutThread5");
        putThread5.start();
        
        Thread.sleep(1000);
        
        Thread takerThread = new Thread(new TakerRunnable<String>(sq), "TakerThread");
        takerThread.start();
    }
}