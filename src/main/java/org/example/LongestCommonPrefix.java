package org.example;


/**
 * 最长公共前缀 Easy 题
 *
 * 思路就是 取第一个字符串中 每一个 char ,和后面的字符串 相应的位置比较；不一致 直接返回
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"1234", "1235","1236"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        char[] chars =  strs[0].toCharArray();
        int index = 0 ;
        f1 : for (;index < chars.length ; index ++){
            char it = chars[index];
            // 尝试比对其他字符串
            for (int j = 1 ; j < strs.length ; j++){
                // 如果字符串的长度 小于 index ；则直接可以返回
                if(strs[j].length() - 1<index){
                    break f1;
                }
                // 如果 char 不等于 第一个字符串的 char ,则可以直接返回
                if(strs[j].charAt(index) != it){
                    break f1;
                }
            }
        }
        return strs[0].substring(0,index);
    }

}
