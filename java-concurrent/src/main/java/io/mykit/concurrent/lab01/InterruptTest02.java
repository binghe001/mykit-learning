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
public class InterruptTest02 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(()->{
            try {
                System.out.println("threadOne begin sleep for 2000 seconds");
                Thread.sleep(2000000);
                System.out.println("thread One awaking");
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            System.out.println("threadOne-leaving normal");
        });
        threadOne.start();
        Thread.sleep(1000);
        threadOne.interrupt();
        threadOne.join();
        System.out.println("main thread is over");
    }
}
