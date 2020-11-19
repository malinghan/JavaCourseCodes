package io.kimmking.spring01;

import java.util.Arrays;

/**
 * @author : linghan.ma
 * @Package io.kimmking.spring01
 * @Description:
 * @date Date : 2020年11月19日 8:48 PM
 **/
public class LambdaDemo {

    public static void main(String[] args) {
        Arrays.asList(1,2,3).forEach(x -> System.out.println(x+4));
        Arrays.asList(1,2,3);
    }
}
