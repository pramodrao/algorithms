package com.pramodrao.dsa;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * @author pramod.rao
 */
public class SortByFreq {
    public static String frequencySort(String s) {
        if (null == s || s.length() <= 1) return s;
        int len = s.length();
        Map<Character, Integer> charM = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            charM.put(c, charM.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> l = new LinkedList<>(charM.entrySet());
        Collections.sort(l, (o1, o2) -> -(o1.getValue().compareTo(o2.getValue())));

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : l) {
            int i = entry.getValue();
            while (i > 0) {
                sb.append(entry.getKey());
                i--;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        String s = "tree";
//        String s = "cccaaa";
//        String s = "Aabb";
//        String s = "gjfHyfdkk";
//        String s = "";
        String s = null;
        System.out.println(frequencySort(s));
    }
}
