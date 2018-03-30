package com.zhang.jiwei.service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jiwei.zhang
 * @DATE 2017/12/23 0023
 */
public class StringTest {

    public static void main(String[] args) {
        String s = "1,2,3,4";
        Set<Integer> set = Arrays.stream(s.split(",")).map(Integer::valueOf).collect(Collectors.toSet());
        set.forEach(System.out::println);
    }
}
