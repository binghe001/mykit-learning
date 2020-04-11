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
package io.mykit.concurrent.lab01;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public class DeadLockTest02 {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args){
        Thread threadA = new Thread(()->{
            synchronized (resourceA){
                System.out.println(Thread.currentThread() + " get resourceA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resourceB");
                synchronized (resourceB){
                    System.out.println(Thread.currentThread() + " get resourceB");
                }

            }
        });

        Thread threadB = new Thread(()->{
            synchronized (resourceA){
                System.out.println(Thread.currentThread() + " get resourceB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resourceA");
                synchronized (resourceB){
                    System.out.println(Thread.currentThread() + " get resourceA");
                }

            }
        });
        threadA.start();
        threadB.start();

    }
}
