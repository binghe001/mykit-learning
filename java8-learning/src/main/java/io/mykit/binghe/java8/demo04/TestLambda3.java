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
package io.mykit.binghe.java8.demo04;

import io.mykit.binghe.java8.demo01.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public class TestLambda3 extends BaseTest {

    public void handlerConsumer(Integer number, Consumer<Integer> consumer){
        consumer.accept(number);
    }

    @Test
    public void test1(){
        this.handlerConsumer(10000, (i) -> System.out.println(i));
    }


    public List<Integer> getNumberList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < num; i++){
            list.add(supplier.get())
        }
        return list;
    }

    @Test
    public void test2(){
        List<Integer> numberList = this.getNumberList(10, () -> new Random().nextInt(100));
        numberList.stream().forEach(System.out::println);
    }

    public String handlerString(String str, Function<String, String> func){
        return func.apply(str);
    }

    @Test
    public void test3(){
        String str = this.handlerString("binghe", (s) -> s.toUpperCase());
        System.out.println(str);
    }

    public List<String> filterString(List<String> list, Predicate<String> predicate){
        List<String> strList = new ArrayList<>();
        for(String str : list){
            if(predicate.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello", "Lambda", "binghe", "lyz", "World");
        List<String> strList = this.filterString(list, (s) -> s.length() >= 5);
        strList.stream().forEach(System.out::println);
    }
}
