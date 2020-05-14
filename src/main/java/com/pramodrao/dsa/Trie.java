package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class Trie {

    static final int numAlphabets = 26;

    static final class Node {
        Node[] children =  new Node[numAlphabets];
        boolean endOfWord = false;
    }

    Node root;

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char start = 'a';
        Node temp = this.root;
        for (char letter : word.toCharArray()) {

            if (null == temp.children[letter-start]) temp.children[letter-start] = new Node();
            Node child = temp.children[letter-start];
            temp = child;
        }
        temp.endOfWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char start = 'a';
        Node temp = this.root;
        for (char letter : word.toCharArray()) {
            Node child = temp.children[letter-start];
            if (null == child) return false;
            temp = child;
        }

        if (temp.endOfWord) return true;
        else return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char start = 'a';
        Node temp = this.root;
        for (char letter : prefix.toCharArray()) {
            Node child = temp.children[letter-start];
            if (null == child) return false;
            temp = child;
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}
