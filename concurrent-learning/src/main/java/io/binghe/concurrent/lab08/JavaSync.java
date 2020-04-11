package io.binghe.concurrent.lab08;

public class JavaSync {
    public static void main(String[] args) {
        SyncContent syncContent = new SyncContent("JavaSync");
 
        ThreadA a = new ThreadA(syncContent);
        a.setName("A");
        a.start();
 
        ThreadB b = new ThreadB(syncContent);
        b.setName("B");
        b.start();
 
        ThreadC c = new ThreadC(syncContent);
        c.setName("C");
        c.start();
    }
}
 
class SyncContent {
    String content = new String();
 
    public SyncContent(String content){
        this.content = content;
    }
 
    synchronized public void syncFunc(String str){
        try {
            System.out.println("syncFunc.Thread: " + Thread.currentThread().getName() + " enter: " + System.currentTimeMillis());
            System.out.println("syncFunc.Thread: " + Thread.currentThread().getName() + " content old: " + content);
            content = str;
            System.out.println("syncFunc.Thread: " + Thread.currentThread().getName() + " content new: " + content);
            Thread.sleep(2000);
            System.out.println("syncFunc.Thread: " + Thread.currentThread().getName() + " content final: " + content);
            System.out.println("syncFunc.Thread: " + Thread.currentThread().getName() + " exit: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    public void syncThis(String str){
        synchronized(this) {
            try {
                System.out.println("syncThis.Thread: " + Thread.currentThread().getName() + " enter: " + System.currentTimeMillis());
                System.out.println("syncThis.Thread: " + Thread.currentThread().getName() + " content old: " + content);
                content = str;
                System.out.println("syncThis.Thread: " + Thread.currentThread().getName() + " content new: " + content);
                Thread.sleep(2000);
                System.out.println("syncThis.Thread: " + Thread.currentThread().getName() + " content final: " + content);
                System.out.println("syncThis.Thread: " + Thread.currentThread().getName() + " exit: " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 
    public void syncVariable(String str) {
        synchronized(content) {
            System.out.println("syncVariable.Thread: " + Thread.currentThread().getName() + " enter: " + System.currentTimeMillis());
            System.out.println("syncVariable.Thread: " + Thread.currentThread().getName() + " content old: " + content);
            content = str;
            System.out.println("syncVariable.Thread: " + Thread.currentThread().getName() + " content new: " + content);
            System.out.println("syncVariable.Thread: " + Thread.currentThread().getName() + " exit: " + System.currentTimeMillis());
        }
    }
}
 
class ThreadA extends Thread {
    private SyncContent syncContent;
    private String me = "ThreadA";
 
    public ThreadA(SyncContent syncContent) {
        super();
        this.syncContent = syncContent;
    }
 
    @Override
    public void run() {
        syncContent.syncThis(me);
    }
}
 
 
class ThreadB extends Thread {
    private SyncContent syncContent;
    private String me = "ThreadB";
 
    public ThreadB(SyncContent syncContent) {
        super();
        this.syncContent = syncContent;
    }
 
    @Override
    public void run() {
        syncContent.syncFunc(me);
    }
}
 
class ThreadC extends Thread {
    private SyncContent syncContent;
    private String me = "ThreadC";
 
    public ThreadC(SyncContent syncContent) {
        super();
        this.syncContent = syncContent;
    }
 
    @Override
    public void run() {
        syncContent.syncVariable(me);
    }
}