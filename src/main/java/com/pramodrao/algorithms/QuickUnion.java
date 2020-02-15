package com.pramodrao.algorithms;

import java.util.Scanner;

/**
 * @author pramod.rao
 */
public class QuickUnion {

    private int[] entries;

    QuickUnion(int n) {
        entries = new int[n];
        for ( int i = 0; i < n; i++ ) {
            entries[i] = i;
        }
    }

    private void union(int p, int q) {
        entries[p] = q;
    }

    private boolean connected(int p, int q) {
        if ( entries[p] == q || entries[q] == p ) return true;
        return findRoot(p) == findRoot(q);
    }

    private int findRoot(int p) {
        int root = entries[p];
        while ( entries[root] != root ) {
            root = entries[root];
        }
        return root;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of elements.");
        int numElements = Integer.parseInt(scanner.next());
        QuickUnion quickUnion = new QuickUnion(numElements);

        System.out.println("Please enter the connections, separated by commas. Enter 'done' when finished.");
        while (scanner.hasNext("[0-9]{1,2},[0-9]*")) {
            String connection = scanner.next();
            String[] elements = connection.split(",");
            int start = Integer.parseInt(elements[0]);
            int end = Integer.parseInt(elements[1]);

            if ( quickUnion.connected(start, end)) System.out.println("Already connected.");
            else {
                quickUnion.union(start, end);
            }
        }

        System.out.println("6 and 2 are connected: " +quickUnion.connected(6,2));
    }
}
