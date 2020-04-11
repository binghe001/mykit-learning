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
 * @description 测试锁
 */
public class SharedLockTest {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        //创建线程
        Thread threadA  = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    synchronized (resourceA){
                        System.out.println("threadA get resourceA lock");
                        synchronized (resourceB){
                            System.out.println("threadA get resourceB lock");

                            //线程A阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    synchronized (resourceA){
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB try get resourceB lock...");
                        synchronized (resourceB){
                            System.out.println("threadB get resourceB lock");

                            //线程B阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        threadB.start();
        //等待两个线程结束
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }
}
