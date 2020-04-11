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

import java.util.Date;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public class Monitor implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("java.class.path: " + System.getProperty("java.class.path"));
        System.out.println("user.dir: " + System.getProperty("user.dir"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
        System.out.println("=====================================");
    }
}
