package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // 负数的 模除  先去掉符号，，然后整数模除，再加上符号；
        System.out.println(-10 % 7);
        System.out.println(revert(123));
        System.out.println(revert(2147483647));
    }

    /**
     * 默认不做处理会溢出
     * @param num
     * @return
     */
    private static int revert(int num){
        int n = 0 ;
        while (num > 0){
            n = n * 10 + num % 10 ;
            num = num / 10;
        }
        return n;
    }

}
