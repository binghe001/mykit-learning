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
package io.mykit.binghe.java8.demo02;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public class TestLambda {

    @Test
    public void test1(){
       Runnable r = () -> System.out.println("Hello Lambda");
       new Thread(r).start();
    }

    @Test
    public void test2(){
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("Hello Lambda");
    }

    @Test
    public void test3(){
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("Hello Lambda");
    }

    @Test
    public void test4(){
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }
    @Test
    public void test5(){
        Comparator<Integer> comparator = (x, y) ->  Integer.compare(x, y);
    }

    public String handlerString(MyFunc<String> myFunc, String str){
        return myFunc.getValue(str);
    }

    @Test
    public void test6(){
        String str = handlerString((s) -> s.toUpperCase(), "binghe");
        System.out.println(str);
    }
    @Test
    public void test7(){
        String str = handlerString((s) -> s.substring(0,4), "binghe");
        System.out.println(str);
    }
}
