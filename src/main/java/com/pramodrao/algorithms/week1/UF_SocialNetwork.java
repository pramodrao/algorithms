package com.pramodrao.algorithms.week1;

import java.util.Scanner;

/**
 * @author pramod.rao
 */
public class UF_SocialNetwork {

    int[] members;
    int[] numNodes;
    int disjointedComponents;

    UF_SocialNetwork(int numMembers) {
        members = new int[numMembers];
        numNodes = new int[numMembers];
        for ( int i = 0; i < numMembers; i++ ) {
            members[i] = i;
            numNodes[i] = 1;
        }
        disjointedComponents = numMembers;
    }

    private void weightedUnion(int p, int q) {

        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if ( numNodes[p] < numNodes[q] ) {
            members[pRoot] = qRoot;
            numNodes[qRoot] += numNodes[pRoot];
        } else {
            members[qRoot] = pRoot;
            numNodes[pRoot] += numNodes[qRoot];
        }
        disjointedComponents--;
    }

    private boolean connected(int p, int q) {
        if ( members[p] == q || members[q] == p ) return true;
        return findRoot(p) == findRoot(q);
    }

    private int findRoot(int p) {
        while ( members[p] != p ) {
            members[p] = members[members[p]];
            p = members[p];
        }
        return p;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of members.");
        int numMembers = Integer.parseInt(scanner.next());
        UF_SocialNetwork network = new UF_SocialNetwork(numMembers);

        System.out.println("Please enter the connections, separated by commas. Enter 'done' when finished.");
        while (scanner.hasNext("[0-9]{1,2},[0-9]*") || network.disjointedComponents > 1) {
            String connection = scanner.next();
            String[] elements = connection.split(",");
            int start = Integer.parseInt(elements[0]);
            int end = Integer.parseInt(elements[1]);

            if ( network.connected(start, end)) System.out.println("Already connected.");
            else {
                network.weightedUnion(start, end);
            }
        }
    }
}
