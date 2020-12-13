package org.example;

/**
 * 字符串相乘
 */
public class StringMultiply {

    public static void main(String[] args) {
        System.out.println(multiply("9999999999","99"));
    }

    public static String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] result = new int[len1 + len2];
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        for (int i = 0; i < chars2.length; i++) {
            int value1 = chars2[len2 - 1 - i] - '0';
            for (int j = 0; j < chars1.length; j++) {
                int value2 = chars1[len1 - 1 - j] - '0';
                int index = i + j;
                result[index] = result[index] + value1 * value2;
            }
        }
        for (int i = 0; i < (len1 + len2); i++) {
            while (result[i] >= 10) {
                int value = result[i];
                result[i] = value % 10;
                result[i + 1] += value / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean skip = true;
        for (int i = (len1 + len2 - 1); i >= 0; i--) {
            if (result[i] == 0 && skip) {
                continue;
            }
            skip = false;
            sb.append(result[i]);
        }
        String res = sb.toString();
        if(res.equals("")){
            return "0";
        }
        return res;
    }

}
