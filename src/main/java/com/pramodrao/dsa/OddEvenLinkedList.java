package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class OddEvenLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode oddEvenList(ListNode head) {
        if (null == head || null == head.next) return head;

        ListNode evenNodes = head.next;
        ListNode evenStartNode = evenNodes;
        ListNode oddNodes = head;

        while (null != evenNodes && null != evenNodes.next) {
            oddNodes.next = evenNodes.next;
            oddNodes = oddNodes.next;
            evenNodes.next = oddNodes.next;
            evenNodes = evenNodes.next;
        }
        oddNodes.next = evenStartNode;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode r = oddEvenList(head);
        ListNode n = r;
        while (true) {
            System.out.print(n.val);
            n = n.next;
            if (null == n) return;
            else System.out.print("-->");
        }
    }
}
