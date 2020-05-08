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
package io.mykit.binghe.java8.demo03;

import io.mykit.binghe.java8.demo01.BaseTest;
import org.junit.Test;

import java.util.Collections;

/**
 * @author binghe
 * @version 1.0.0
 * @description
 */
public class TestLambda extends BaseTest {

    @Test
    public void test1(){
        Collections.sort(employees, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }
            return -Integer.compare(e1.getAge(), e2.getAge());
        });
        employees.stream().forEach(System.out::println);
    }

    public String stringHandler(String str, MyFunction myFunction){
        return myFunction.getValue(str);
    }

    @Test
    public void test2(){
        String value = stringHandler("binghe", (s) -> s.toUpperCase());
        System.out.println(value);
    }


    public void operate(Long num1, Long num2, MyFunc<Long, Long> myFunc){
        System.out.println(myFunc.getValue(num1, num2));
    }

    @Test
    public void test3(){
        operate(100L, 200L, (x, y) -> x + y);
    }

    @Test
    public void test5(){
        operate(100L, 200L, (x, y) -> x * y);
    }
}
