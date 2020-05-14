package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class RemoveKDigits {
    public static String removeKdigits(String num, int k) {
        int len = num.length();
        if (k == len) return "0";

        while (k > 0){
            char fc = num.charAt(0);
            char sc = num.charAt(1);
            int i = 0;
            int numLen = num.length()-1;
            while (fc <= sc && i < numLen-1) {
                i++;
                fc = num.charAt(i);
                sc = num.charAt(i+1);
            }

            if (i == numLen-1 && fc <= sc) num = num.substring(0, numLen);
            else num = num.substring(0, i) + num.substring(i+1, numLen+1);
            k--;
        }

        while (num.startsWith("0")) num = num.substring(1);
        len = num.length();
        if (len == 0) return "0";
        return num;
    }

    public static void main(String[] args) {
        String num = "1432219"; int k = 3;
//        String num = "10200"; int k = 1;
//        String num = "10"; int k = 2;

        System.out.println(removeKdigits(num, k));
    }
}
