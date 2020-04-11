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
package io.binghe.concurrent.lab05;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public class SchedulePoolTest {
    public static void main(String[] args){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        /*scheduledExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试scheduledExecutorService的submit方法");
            }
        });*/

        scheduledExecutorService.schedule(()->{
            System.out.println("测试scheduledExecutorService的schedule方法");
        },5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("测试scheduledExecutorService的scheduleAtFixedRate方法");
        },0, 5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleWithFixedDelay(()-> {
            System.out.println("测试scheduledExecutorService的scheduleWithFixedDelay方法");
        }, 0, 5, TimeUnit.SECONDS);

    }
}
