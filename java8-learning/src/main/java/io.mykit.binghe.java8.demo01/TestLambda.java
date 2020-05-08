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
package io.mykit.binghe.java8.demo01;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试Lambda表达式
 */
public class TestLambda extends BaseTest {

    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(com);

    }

    @Test
    public void test2(){
        TreeSet<Integer> treeSet = new TreeSet<>((x, y) -> Integer.compare(x, y));
    }

    public List<Employee> filterEmployeesByAge(List<Employee> list){
        List<Employee> employees = new ArrayList<>();
        for(Employee e : list){
            if(e.getAge() >= 30){
                employees.add(e);
            }
        }
        return employees;
    }

    public List<Employee> filterEmployeesBySalary(List<Employee> list){
        List<Employee> employees = new ArrayList<>();
        for(Employee e : list){
            if(e.getSalary() >= 5000){
                employees.add(e);
            }
        }
        return employees;
    }

    @Test
    public void test3(){
        List<Employee> employeeList = filterEmployeesByAge(this.employees);
        for (Employee e : employeeList){
            System.out.println(e);
        }
    }

    //优化方式一
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> myPredicate){
        List<Employee> employees = new ArrayList<>();
        for(Employee e : list){
            if(myPredicate.filter(e)){
                employees.add(e);
            }
        }
        return employees;
    }

    @Test
    public void test4(){
        List<Employee> employeeList = this.filterEmployee(this.employees, new FilterEmployeeByAge());
        for (Employee e : employeeList){
            System.out.println(e);
        }
    }
    @Test
    public void test5(){
        List<Employee> employeeList = this.filterEmployee(this.employees, new FilterEmployeeBySalary());
        for (Employee e : employeeList){
            System.out.println(e);
        }
    }
    @Test
    public void test6(){
        List<Employee> employeeList = this.filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean filter(Employee employee) {
                return employee.getAge() >= 30;
            }
        });
        for (Employee e : employeeList){
            System.out.println(e);
        }
    }
    @Test
    public void test7(){
        List<Employee> employeeList = this.filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean filter(Employee employee) {
                return employee.getSalary() >= 5000;
            }
        });
        for (Employee e : employeeList){
            System.out.println(e);
        }
    }

    @Test
    public void test8(){
        filterEmployee(this.employees, (e) -> e.getAge() >= 30).forEach(System.out::println);
    }
    @Test
    public void test9(){
        filterEmployee(this.employees, (e) -> e.getSalary() >= 5000).forEach(System.out::println);
    }
    @Test
    public void test10(){
        employees.stream().filter((e) -> e.getSalary() >= 5000).forEach(System.out::println);
    }
    @Test
    public void test11(){
        employees.stream().filter((e) -> e.getSalary() >= 5000).limit(2).forEach(System.out::println);
    }
    @Test
    public void test12(){
        employees.stream().filter((e) -> e.getSalary() >= 5000).map(Employee::getName).forEach(System.out::println);
    }
}
