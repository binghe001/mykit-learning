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
package io.mykit.concurrent.lab03;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public class Demo02 {

    public static void main(String[] args){
        ThreadGroup threadGroup = new ThreadGroup("tg"){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        };
        Thread thread = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("测试线程抛出异常");
            }
        });
        thread.start();
        thread = new Thread(threadGroup, new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("测试线程抛出异常");
            }
        });
        thread.start();
    }
}
