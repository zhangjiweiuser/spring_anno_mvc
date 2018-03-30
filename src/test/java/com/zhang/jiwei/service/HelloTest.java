package com.zhang.jiwei.service;

/**
 * @author jiwei.zhang
 * @DATE 2018/1/4 0004
 */
public class HelloTest {

    public static void main(String[] args) {
        validate();

        sayHello("zhang");
        sayHello("zhang");
    }

    private static void validate() {
        String name = "zhang";
        sayHello(name);
        sayHello(name);
    }

    private static void sayHello(String name){
        System.out.println(name);
    }
}
