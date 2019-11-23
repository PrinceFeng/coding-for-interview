package com.prince;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 归并排序 时间复杂度nlog(n)
 * @Author prince Chen
 * @Date 2019/11/23 20:03
 */

public class MergeSort {

    public static void solution01(int[] numbers) {
        mergeSort(numbers, 0, numbers.length-1);
    }

    /**
     * 归并排序：分而治之的思想，先把要排序的数列不断分割成小数列，然后将小数列排序好，两两归并，最后合成排序好的大数列
      * @param numbers 要排序的数组
     * @param start 数组起始下标
     * @param end  数组结束下标
     */
    private static void mergeSort(int[] numbers, int start, int end) {
        // 首先数据切分
        int middle = (start + end) / 2;
        if(start < middle) {
            mergeSort(numbers, start, middle);
            mergeSort(numbers, middle+1, end);
        }
        // 排序 & 归并
        int i=0, first=start, last=middle+1;
        int[] temp = new int[end-start+1];
        while(first<=middle && last<=end) {
            temp[i++] = numbers[first] < numbers[last] ? numbers[first++] : numbers[last++];
        }

        while (first <= middle) {
            temp[i++] = numbers[first++];
        }

        while (last<=end) {
            temp[i++] = numbers[last++];
        }

        // 以及排序归并好了，放入原数组
        i = 0;
        while (start<=end) {
            numbers[start++] = temp[i++];
        }
        System.out.println(Arrays.toString(numbers));
    }

    /**
     * 其实和方案一一毛一样，就是想再写一遍，熟悉熟悉，哈哈哈
     * @param nunbers 待排序的数组
     * @param start 数组其实索引
     * @param end 数组结束索引
     */
    public static void solution02(int[] nunbers, int start, int end) {
        // 首先把数据切分成小块
        int middle = (start + end) / 2;
        if (start < middle) {
            solution02(nunbers, start, middle);
            solution02(nunbers, middle+1, end);
        }

        // 切分完了，进行排序
        int i=0, first=start, last=middle+1;
        int[] temp=new int[end-start+1];
        while (first<=middle && last<=end) {
            temp[i++] = nunbers[first] < nunbers[last] ? nunbers[first++] : nunbers[last++];
        }

        // 将两部分剩余的元素放到数组中, 以下两个while只会执行一个
        while (first <= middle) {
            temp[i++] = nunbers[first++];
        }
        while (last<=end) {
            temp[i++] = nunbers[last++];
        }

        // 将排好序的元素放回到原数组中
        i = 0;
        while(start<=end) {
            nunbers[start++] = temp[i++];
        }
        System.out.println(Arrays.toString(nunbers));
    }

    public static void main(String[] args) {
        int[] numbers = {7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(numbers));
//        solution01(numbers);
        solution02(numbers, 0, numbers.length-1);
    }
}
