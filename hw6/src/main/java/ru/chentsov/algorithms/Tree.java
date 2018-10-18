package ru.chentsov.algorithms;

public class Tree {

    private class TreeNode {
        private int c;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int c) {
            this.c = c;
        }
    }

    TreeNode root;

    public void insert(int c) {
        TreeNode node = new TreeNode(c);
        if (root == null)
            root = node;
        else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (c < current.c) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else if (c > current.c) {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }
                } else
                    return;
            }
        }
    }

    public boolean find(int id) {
        TreeNode current = root;
        while (current.c != id) {
            current = (id < current.c) ? current.left : current.right;
            if (current == null) return false;
        }
        return true;
    }

    public void displayTree() {
        inOrderTravers(root);
    }

    private void inOrderTravers(TreeNode node) {
        if (node != null) {
            inOrderTravers(node.left);
            System.out.println(node);
            inOrderTravers(node.right);
        }
    }

    public boolean delete(int id) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;
        while (current.c != id) {
            parent = current;
            if (id < current.c) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null)
                return false;
        }

        // if a leaf
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        }
        // if has one ancestor
        else if (current.right == null) {
            if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        } else if (current.left == null) {
            if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        }
        // if two ancestors
        else {
            TreeNode successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;
            successor.left = current.left;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode parent = node;
        TreeNode s = node;
        TreeNode current = node.right;

        while (current != null) {
            parent = s;
            s = current;
            current = current.left;
        }
        if (s != node.right) {
            parent.left = s.right;
            s.right = node.right;
        }
        return s;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * Tree is called balanced if and only if
     * the height of every subtree differs by 1 or less from each other
     *
     * @return true if tree is balanced
     */
    private boolean isBalanced(TreeNode node) {
        if (node != null) {
            boolean isTreeBalanced = Math.abs(getHeight(node.left) - getHeight(node.right)) <= 1;
            boolean isLeftSubtreeBalanced = isBalanced(node.left);
            boolean isRightSubtreeBalanced = isBalanced(node.right);
            return isTreeBalanced && isLeftSubtreeBalanced && isRightSubtreeBalanced;
        }
        return true;
    }

    private int getHeight(TreeNode node) {
        int currentHeight = 0;
        if (node != null) currentHeight = 1 + Math.max(currentHeight,
                Math.max(getHeight(node.left), getHeight(node.right)));
        return currentHeight;
    }

}
