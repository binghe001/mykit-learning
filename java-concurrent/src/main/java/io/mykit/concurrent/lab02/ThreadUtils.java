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
package io.mykit.concurrent.lab02;

/**
 * @author binghe
 * @version 1.0.0
 * @description 根据线程id拿到线程
 */
public class ThreadUtils {

    /**
     * 根据指定的线程id获取线程
     * @param threadId 线程id
     * @return id为threadId的线程
     */
    public static Thread getThread(long threadId){
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while (group != null){
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++){
                if(threadId == threads[i].getId()){
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }

    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    long threadId1 = Thread.currentThread().getId();
                    Thread thread2 = getThread(threadId1);
                    long thread2Id = thread2.getId();
                    boolean x = threadId1 == thread2Id;
                    System.out.println(x);
                    System.out.println(threadId1);
                    System.out.println(thread2Id);
                    if(x){
                        break;
                    }
                }
            }
        });
        thread.start();

    }
}
