package com.pramodrao.dsajune20;

/**
 * @author pramod.rao
 */
public class DeleteNode {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void deleteNode(ListNode node) {
        ListNode temp = node.next.next;
        int tempVal = node.next.val;
        node.next = node.next.next;
        node.val = tempVal;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode x = new ListNode(5);
        head.next = x;
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);

        deleteNode(x);
    }
}
