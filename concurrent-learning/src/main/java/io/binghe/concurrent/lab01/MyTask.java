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
package io.binghe.concurrent.lab01;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试count--操作与System.out.println的联合使用
 */
public class MyTask extends Thread {
    private int count  = 5;
    @Override
    public void run() {
        System.out.println("count = " + (count --) + " 当前线程的名称为：" + Thread.currentThread().getName());
    }
}
