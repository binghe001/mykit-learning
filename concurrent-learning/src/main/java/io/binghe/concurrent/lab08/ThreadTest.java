package io.binghe.concurrent.lab08;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
 
/**
 * @author binghe
 * @version 1.0.0
 * @description 测试SimpleDateFormat的线程不安全问题
 */
public class ThreadTest {
    //执行总次数
    private static final int EXECUTE_COUNT = 1000;
    //同时运行的线程数量
    private static final int THREAD_COUNT = 200;
    private  int count = 50;
    private Object object = new Object();

    public void handler() throws InterruptedException {
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        synchronized (object){
                            if(count>0){
                                System.out.println(Thread.currentThread().getName()+":  抢到第"+count--+"张,");
                               // Thread.sleep(50);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + " 出现线程安全问题");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("程序结束");

    }
 
    public static void main(String[] args) throws InterruptedException {
        new ThreadTest().handler();
    }
}