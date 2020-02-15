package com.pramodrao.algorithms;

import java.util.*;

/**
 * @author pramod.rao
 */
public class QuickFind {

    private int[] entries;
    private int numElements;

    QuickFind(int n) {
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
        return entries[p] == entries[q];
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of elements.");
        int numElements = Integer.parseInt(scanner.next());
        QuickFind qf = new QuickFind(numElements);

        System.out.println("Please enter the connections, separated by commas. Enter 'done' when finished.");
        while (scanner.hasNext("[0-9]{1,2},[0-9]*")) {
            String connection = scanner.next();
            String[] elements = connection.split(",");
            int start = Integer.parseInt(elements[0]);
            int end = Integer.parseInt(elements[1]);

            if ( qf.connected(start, end)) System.out.println("Already connected.");
            else {
                qf.union(start, end);
            }
        }

        Map<Integer, String> componentsM = new HashMap<Integer, String>();
        for ( int i = 0; i < numElements; i++ ) {
            int key = qf.entries[i];
            String value;
            if ( componentsM.containsKey(key)) {
                value = componentsM.get(key) +":" +i;
            } else value = String.valueOf(i);
            componentsM.put(key, value);
        }

        System.out.println("Connected Components:");
        System.out.println(componentsM.toString());
    }
}
