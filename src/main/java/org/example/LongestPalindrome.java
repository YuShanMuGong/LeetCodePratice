package org.example;

/**
 * 最长回文串
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("1abad"));
    }

    public static String longestPalindrome(String s) {
        if(s == null || s.isEmpty()){
            return "";
        }
        if(s.length() == 1){
            return s;
        }
        int len = s.length();
        boolean[][] flag = new boolean[len][len];
        for (int i = 0 ; i < len ; i++){
            flag[i][i] = true;
        }
        int maxLen = 1 ;
        int start = 0;
        for (int j = 1 ; j < len ; j++){
            for (int i = 0 ; i < j ; i++){
                if(s.charAt(i) != s.charAt(j)){
                    flag[i][j] = false;
                }else{
                    if(j - i <= 2){
                        flag[i][j] = true;
                    }else{
                        // 因为我们们 j 是递增的，，所以，我们 的 flag[i + 1 ][j - 1] 必然是已经计算完成的
                        flag[i][j] = flag[i + 1 ][j - 1];
                    }
                }
                if(flag[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start,start + maxLen);
    }

}
