package com.prince;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 冒泡排序算法
 * @Author prince Chen
 * @Date 2019/11/23 18:56
 */

public class BubbleSort {

    /**
     * 每一次冒泡，都将一个数放到他正确的位置，所以n个数排序，需要n-1次递归
     * @param numbers  需要排序的列表
     */
    public static void solution01(List<Integer> numbers) {
        int len = numbers.size();
        for (int i=0; i<len-1; i++) {
            for (int j=0; j<len-1; j++) {
                if(numbers.get(j) > numbers.get(j+1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j+1));
                    numbers.set(j+1, temp);
                }
            }
            System.out.println(numbers);
        }
    }

    /**
     * 优化：如果某一次排序后并没有发生交换，则表明以及有序，不需要继续进行排序了
     * @param numbers
     */
    public static void solution02(List<Integer> numbers) {
        int len = numbers.size();
        for (int i=0; i<len-1; i++) {
            boolean flag = false;
            for (int j = 0; j<len - 1; j++) {
                if (numbers.get(j) > numbers.get(j + 1)) {
                    int temp = numbers.get(j);
                    numbers.set(j, numbers.get(j+1));
                    numbers.set(j+1, temp);
                    flag = true;
                }
            }
            System.out.println(numbers);
            if (!flag) {
                break;
            }
        }

    }


    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(12,5,2,11,8,1,3,4));
        ArrayList<Integer> numCopy = numbers;
        System.out.println(numbers);
//        solution01(numbers); // 需要七次
        solution02(numbers); // 需要6次
    }
}
