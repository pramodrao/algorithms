package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class CousinsInBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static boolean isCousins(TreeNode root, int x, int y) {

        if (null == root) return false;
        int[] XparentAndDepth = getParentAndDepth(root, x, root.val, 0);
        if (null == XparentAndDepth) return false;

        int[] YparentAndDepth = getParentAndDepth(root, y, root.val, 0);
        if (null == YparentAndDepth) return false;

        return  (XparentAndDepth[0] == YparentAndDepth[0]) ? false : (XparentAndDepth[1] == YparentAndDepth[1]);
    }

    private static int[] getParentAndDepth(TreeNode node, int val, int parent, int depth)
    {
        if (null == node) return null;
        if (node.val != val) {
            int[] a = getParentAndDepth(node.left, val, node.val, depth+1);
            if (null == a)
                a = getParentAndDepth(node.right, val, node.val, depth+1);
            return a;
        } else {
            return new int[]{parent, depth};
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2; t1.right = t3;
        t2.left = null; t2.right = t4;
        t3.left = null; t3.left = t5;
        System.out.println(isCousins(t1, 2, 4));
    }
}
