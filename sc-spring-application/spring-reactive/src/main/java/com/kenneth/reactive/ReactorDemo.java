package com.kenneth.reactive;

import java.util.stream.Stream;

/**
 * @auther qinkai
 * @data 2019/5/7 9:18
 */
public class ReactorDemo {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
                .map(String::valueOf)
                .forEach(System.err::println);
    }
}
