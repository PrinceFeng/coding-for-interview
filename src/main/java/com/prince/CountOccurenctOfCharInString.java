package com.prince;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 统计字符在字符串中出现的次数
 * @Author prince Chen
 * @Date 2019/11/23 21:52
 */

public class CountOccurenctOfCharInString {

    /**
     * 貌似太简单了，感觉有点不对。。
     * @param key 需要统计的字符
     * @param str 目标字符串
     */
    public static void solution01(char key, String str) {
        char[] chars = str.toCharArray();
        int count=0;
        for (char c : chars) {
            if (c == key) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * 貌似也太简单。。
     * @param subString 要查找的子串
     * @param str 目标串
     */
    public static void solution02(String subString, String str) {

        Pattern compile = Pattern.compile(subString);
        Matcher matcher = compile.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        System.out.println(count);

    }

    /**
     * 好像这才是我要的
     * @param srcStr  目标串
     * @param findStr 要查找的串
     */
    public static void solution03(String srcStr, String findStr) {
        int count = 0;
        int index = 0;
        while((index = srcStr.indexOf(findStr, index)) != -1) {
            count++;
            index++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
//        solution01('c', "hahahac666c");
//        solution02("oco", "ococo");
        solution03("ococo", "oco");
    }
}
