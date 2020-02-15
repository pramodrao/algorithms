package com.pramodrao.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pramod.rao
 */
public class QuickUnion {

    public int[] entries;
    private int numElements;

    QuickUnion(int n) {
        numElements = n;
        entries = new int[n];
        for ( int i = 0; i < n; i++ ) {
            entries[i] = i;
        }
    }

    private void union(int p, int q) {
        int startVal = entries[p];
        int endVal = entries[q];
        for ( int i = 0; i < numElements; i++ ) {
            if ( entries[i] == endVal ) entries[i] = startVal;
        }
    }

    private boolean connected(int p, int q) {
        if ( entries[p] == entries[q]) return true;
        return false;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of elements.");
        int numElements = Integer.valueOf(scanner.next());
        QuickUnion qf = new QuickUnion(numElements);

        System.out.println("Please enter the connections, separated by commas. Enter 'done' when finished.");
        while (scanner.hasNext("[0-9]{1,2},[0-9]*")) {
            String connection = scanner.next();
            String[] elements = connection.split(",");
            int start = Integer.valueOf(elements[0]);
            int end = Integer.valueOf(elements[1]);

            if ( qf.connected(start, end)) System.out.println("Already connected.");
            else {
                qf.union(start, end);
            }
        }

        Map<Integer, String> componentsM = new HashMap<Integer, String>();
        for ( int i = 0; i < numElements; i++ ) {
            int key = qf.entries[i];
            int val = i;
            String value = "";
            if ( componentsM.containsKey(key)) {
                value = componentsM.get(key) +":" +val;
            } else value = String.valueOf(val);
            componentsM.put(key, value);
        }

        System.out.println("Connected Components:");
        System.out.println(componentsM.toString());
    }
}
