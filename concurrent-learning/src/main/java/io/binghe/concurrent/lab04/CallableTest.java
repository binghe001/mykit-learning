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
package io.binghe.concurrent.lab04;

import java.util.concurrent.*;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试Callable的类
 */
public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "测试Callable";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new CallableTest());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        ExecutorService executorService = Executors.newFixedThreadPool(3);

    }
}
