package com.prince;

/**
 * @Description 找出字符串中第一个非重复的字符
 * @Author prince Chen
 * @Date 2019/11/23 23:10
 */

public class FindFirstNonrepeatedCharacter {

    /**
     * 两层循环，时间复杂度O(n*n)
     */
    public static void solution01(String str) {
        int len = str.length();
        char[] chars = str.toCharArray();
        int i,j;
        for (i=0; i<len; i++) {
            for (j=0; j<len; j++) {
                if (i==j) {
                    continue;
                }
                if (chars[i] == chars[j]) {
                    break;
                }
            }
            if (j==len) {
                System.out.println("the first nonrepeat char is==>" + chars[i]);
                return;
            }
        }
        if (i==len) {
            System.out.println("there is no nonrepeat char.");
        }
    }

    /**
     * 记录26个字母，每个字母在字符串中第一次和最后一次出现的位置，如果二者相等则此字符没有重复
     * 记录索引最小的值，即为第一个没有重复的字符
     * @param str 目标字符串
     */
    public static void solution02(String str) {
        int result = str.length();
        for (char s = 'a'; s<='z'; s++) {
            int start = str.indexOf(s);
            int end = str.lastIndexOf(s);
            if (start == end && start != -1) {
                result = Math.min(start, result);
            }
        }
        if (result == str.length()) {
            System.out.println("no nonrepeat char.");
        } else {
            System.out.println("the first nonrepeat char ==> " + str.charAt(result));
        }

    }

    public static void main(String[] args) {
        String str = "abcdeabcde";
        solution01(str);
        solution02(str);
    }
}
