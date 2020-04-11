package io.mykit.concurrent.blockingqueue.synchronous;

import java.util.concurrent.SynchronousQueue;

public class TakerRunnable<T> implements Runnable {
    private T value;
    private SynchronousQueue<T> syncQ;
    TakerRunnable(SynchronousQueue<T> syncQ) {
        this.syncQ = syncQ;
    }
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ":" +"Retrieve using take");
            value = syncQ.take();
            System.out.println(Thread.currentThread().getName() + ":" +"take() returned " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public T getValue() {
        return value;
    }
}