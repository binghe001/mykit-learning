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

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author binghe
 * @version 1.0.0
 * @description 监控线程池的工具类
 */
public class MonitorThreadPool implements Runnable{

    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public MonitorThreadPool(ThreadPoolExecutor executor, int delay){
        this.executor = executor;
        this.seconds = delay;
    }

    public void shutdown(){
        this.run = false;
    }

    @Override
    public void run() {
        while (run){
            if(this.executor.isTerminated()){
                System.out.println("任务已经执行完成了...");
                break;
            }
            System.out.println(String.format("[monitor] 池大小: %d, 核心数: %d, 活跃数: %d, 完成数: %d, 任务数: %d, 线程是否已经结束: %s, 线程是否运行完成: %s",
                    this.executor.getPoolSize(),
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getTaskCount(),
                    this.executor.isShutdown(),
                    this.executor.isTerminated()));
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
