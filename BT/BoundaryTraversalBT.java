import java.util.*;
class Node {
    int data;
    Node left, right;
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
public class Main {
    static Boolean isLeaf(Node root) {
        return (root.left == null) && (root.right == null);
    }

    static void addLeftBoundary(Node root, ArrayList < Integer > res) {
        Node cur = root.left;
        while (cur != null) {
            if (isLeaf(cur) == false) res.add(cur.data);
            if (cur.left != null) cur = cur.left;
            else cur = cur.right;
        }
    }
    static void addRightBoundary(Node root, ArrayList < Integer > res) {
        Node cur = root.right;
        ArrayList < Integer > tmp = new ArrayList < Integer > ();
        while (cur != null) {
            if (isLeaf(cur) == false) tmp.add(cur.data);
            if (cur.right != null) cur = cur.right;
            else cur = cur.left;
        }
        int i;
        for (i = tmp.size() - 1; i >= 0; --i) {
            res.add(tmp.get(i));
        }
    }

    static void addLeaves(Node root, ArrayList < Integer > res) {
        if (isLeaf(root)) {
            res.add(root.data);
            return;
        }
        if (root.left != null) addLeaves(root.left, res);
        if (root.right != null) addLeaves(root.right, res);
    }
    static ArrayList < Integer > printBoundary(Node node) {
        ArrayList < Integer > ans = new ArrayList < Integer > ();
        if (isLeaf(node) == false) ans.add(node.data);
        addLeftBoundary(node, ans);
        addLeaves(node, ans);
        addRightBoundary(node, ans);
        return ans;
    }

    public static void main(String args[]) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.right = new Node(4);
        root.left.left.right.left = new Node(5);
        root.left.left.right.right = new Node(6);
        root.right = new Node(7);
        root.right.right = new Node(8);
        root.right.right.left = new Node(9);
        root.right.right.left.left = new Node(10);
        root.right.right.left.right = new Node(11);

        ArrayList < Integer > boundaryTraversal;
        boundaryTraversal = printBoundary(root);

        System.out.println("The Boundary Traversal is : ");
        for (int i = 0; i < boundaryTraversal.size(); i++) {
            System.out.print(boundaryTraversal.get(i) + " ");
        }

    }
}

// //alt
// class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode(int val) {
//         this.val = val;
//     }
// }

// public class BoundaryTraversalBinaryTree {
//     public static void boundaryTraversal(TreeNode root) {
//         if (root == null) return;

//         System.out.print(root.val + " ");

//         // Traverse left boundary (excluding the leftmost leaf)
//         traverseLeftBoundary(root.left);

//         // Traverse leaves of the binary tree
//         traverseLeaves(root.left);
//         traverseLeaves(root.right);

//         // Traverse right boundary (excluding the rightmost leaf)
//         traverseRightBoundary(root.right);
//     }

//     private static void traverseLeftBoundary(TreeNode node) {
//         if (node == null || (node.left == null && node.right == null)) return;

//         System.out.print(node.val + " ");

//         if (node.left != null)
//             traverseLeftBoundary(node.left);
//         else if (node.right != null)
//             traverseLeftBoundary(node.right);
//     }

//     private static void traverseLeaves(TreeNode node) {
//         if (node == null) return;

//         if (node.left == null && node.right == null)
//             System.out.print(node.val + " ");

//         traverseLeaves(node.left);
//         traverseLeaves(node.right);
//     }

//     private static void traverseRightBoundary(TreeNode node) {
//         if (node == null || (node.left == null && node.right == null)) return;

//         if (node.right != null)
//             traverseRightBoundary(node.right);
//         else if (node.left != null)
//             traverseRightBoundary(node.left);

//         System.out.print(node.val + " ");
//     }

//     public static void main(String[] args) {
//         // Create a sample binary tree
//         TreeNode root = new TreeNode(1);
//         root.left = new TreeNode(2);
//         root.right = new TreeNode(3);
//         root.left.left = new TreeNode(4);
//         root.left.right = new TreeNode(5);
//         root.right.left = new TreeNode(6);
//         root.right.right = new TreeNode(7);
//         root.left.left.left = new TreeNode(8);
//         root.left.left.right = new TreeNode(9);

//         System.out.println("Boundary Traversal of Binary Tree:");
//         boundaryTraversal(root); // Output: 1 2 4 8 9 5 6 7 3
//     }
// }
