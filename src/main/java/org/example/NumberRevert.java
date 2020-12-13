package org.example;

public class NumberRevert {

    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }

    public static int reverse(int x) {
        int n = 0 ;
        while ( x != 0 ){
            int f = x % 10 ;
            x = x / 10 ;
            // 如果 n * 10 大于 最大值 则就是直接溢出了；
            //
            if(n > Integer.MAX_VALUE / 10 || (n == Integer.MAX_VALUE / 10 && f > Integer.MAX_VALUE % 10) ){
                return 0;
            }
            if(n < Integer.MIN_VALUE / 10 || (n == Integer.MIN_VALUE / 10 && f < Integer.MIN_VALUE % 10)){
                return 0;
            }
            n = n * 10 + f;
        }
        return n;
    }

}
