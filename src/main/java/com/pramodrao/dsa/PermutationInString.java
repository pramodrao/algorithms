package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class PermutationInString {
    public static boolean checkInclusion(String s1, String s2) {
        if (null == s2 || null == s1 || s1.length() > s2.length()) return false;

        char a = 'a';
        int[] s1Counts = new int[26];
        int s1Len = s1.length();
        for (int i = 0; i < s1Len; i++) {
            s1Counts[s1.charAt(i) - a]++;
        }

        int start = 0;
        for (int i = 0; i < s1Len; i++) {
            s1Counts[s2.charAt(i) - a] -= 1;
        }

        boolean present = true;
        for (int i : s1Counts) {
            if (i != 0) {
                present = false;
                break;
            }
        }

        if (present) return true;
        start++;

        while (start <= s2.length() - s1Len) {

            int prevChar = s2.charAt(start-1) - a;
            s1Counts[prevChar] += 1;

            int currChar = s2.charAt(start + s1Len - 1) - a;
            s1Counts[currChar] -= 1;


            present = true;
            for (int i : s1Counts) {
                if (i != 0) {
                    present = false;
                    break;
                }
            }

            if (present) return true;
            start++;
        }
        return false;
    }

    private static void printCounts(int[] a) {
        for ( int i = 0; i < a.length; i++) {
            System.out.print(a[i] +",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        String s1 = "ab"; String s2 = "eidbaooo";
        String s1 = "ab"; String s2 = "eidboaoo";
        System.out.println(checkInclusion(s1, s2));
    }
}
