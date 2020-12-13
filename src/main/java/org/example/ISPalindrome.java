package org.example;

/**
 *  回文数
 *  判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 *  https://leetcode-cn.com/problems/palindrome-number/
 *
 */
public class ISPalindrome {

    public static void main(String[] args) {

    }


    /**
     * 如果使用整数反转的方法，会遇到溢出的问题；
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        if(x < 10){
            return true;
        }
        // 最后一位是0 的 不可能是 回文数
        if(x % 10 == 0){
            return false;
        }
        int n = 0 ;
        int c = x ;
        while (c > n){
            n = n * 10 + c % 10;
            c = c / 10;
        }
        return c == n || c == n /10;
    }

}
