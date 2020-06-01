package com.pramodrao.dsajune20;

/**
 * @author pramod.rao
 */
public class InvertBinaryTree {

    public static TreeNode invertTree(TreeNode root) {
        if (null == root || (null == root.left && null == root.right)) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(7, new TreeNode(6), new TreeNode(9));

        BTreePrinter.printNode(root);

        TreeNode inverted = invertTree(root);

        BTreePrinter.printNode(inverted);
    }
}
