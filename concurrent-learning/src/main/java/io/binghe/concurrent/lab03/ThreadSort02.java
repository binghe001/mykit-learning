/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.binghe.concurrent.lab03;

/**
 * @author binghe
 * @version 1.0.0
 * @description 线程的顺序，Thread.join()方法能够确保线程的执行顺序
 */
public class ThreadSort02 {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            System.out.println("thread1");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2");
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("thread3");
        });

        thread1.start();
        System.out.println(thread1.getState());
        System.out.println(Thread.currentThread().getState()
        );
        //实际上让主线程等待子线程执行完成
        thread1.join();
        System.out.println(thread1.getState());

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();
    }
}
