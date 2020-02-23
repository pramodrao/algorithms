package com.pramodrao.algorithms.week1;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.Scanner;

/**
 * @author pramod.rao
 */
public class QuickUnionWPC {

    private int[] entries;
    private int[] numNodes;

    QuickUnionWPC(int n) {
        entries = new int[n];
        numNodes = new int[n];
        for ( int i = 0; i < n; i++ ) {
            entries[i] = i;
            numNodes[i] = 1;
        }
    }

    private void weightedUnion(int p, int q) {

        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if ( numNodes[p] < numNodes[q] ) {
            entries[pRoot] = qRoot;
            numNodes[qRoot] += numNodes[pRoot];
        } else {
            entries[qRoot] = pRoot;
            numNodes[pRoot] += numNodes[qRoot];
        }
    }

    private boolean connected(int p, int q) {
        if ( entries[p] == q || entries[q] == p ) return true;
        return findRoot(p) == findRoot(q);
    }

    private int findRoot(int p) {
        while ( entries[p] != p ) {
            entries[p] = entries[entries[p]];
            p = entries[p];
        }
        return p;
    }

    private void plotGraph() {

        Graph graph = new SingleGraph("Quick Union");
        graph.setStrict(false);
        graph.setAutoCreate(true);

        int numEntries = entries.length;
        for ( int i = 0; i < numEntries; i++ ) {
            String id = entries[i] +":" +i;
            graph.addEdge(id, String.valueOf(entries[i]), String.valueOf(i));
        }

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
        }

        graph.display(true);

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of elements.");
        int numElements = Integer.parseInt(scanner.next());
        QuickUnionWPC quickUnion = new QuickUnionWPC(numElements);

        System.out.println("Please enter the connections, separated by commas. Enter 'done' when finished.");
        while (scanner.hasNext("[0-9]{1,2},[0-9]*")) {
            String connection = scanner.next();
            String[] elements = connection.split(",");
            int start = Integer.parseInt(elements[0]);
            int end = Integer.parseInt(elements[1]);

            if ( quickUnion.connected(start, end)) System.out.println("Already connected.");
            else {
                quickUnion.weightedUnion(start, end);
            }
        }

        quickUnion.plotGraph();
        System.out.println("6 and 2 are connected: " +quickUnion.connected(6,2));
    }
}
