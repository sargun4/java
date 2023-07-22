import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.LinkedList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BSTOperations {
    private TreeNode prev;

    // Convert BST to Linked List (In-place)
    public TreeNode convertToLinkedList(TreeNode root) {
        if (root == null) return null;

        TreeNode dummy = new TreeNode(0);
        prev = dummy;

        inOrderTraversal(root);

        TreeNode head = dummy.right;
        head.left = null;
        return head;
    }

    private void inOrderTraversal(TreeNode node) {
        if (node == null) return;

        inOrderTraversal(node.left);

        prev.right = node;
        node.left = prev;
        prev = node;

        inOrderTraversal(node.right);
    }

    // Find In-order Successor of a Node in BST
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return successor;
    }

    // Find Root-to-Leaf Paths in BST
    public List<List<Integer>> rootToLeafPaths(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        findPaths(root, currentPath, paths);

        return paths;
    }

    private void findPaths(TreeNode node, List<Integer> currentPath, List<List<Integer>> paths) {
        if (node == null) return;

        currentPath.add(node.val);

        if (node.left == null && node.right == null) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            findPaths(node.left, currentPath, paths);
            findPaths(node.right, currentPath, paths);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    // Find kth Smallest Element in BST
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> inorderList = new ArrayList<>();
        inOrderTraversal(root, inorderList);
        return inorderList.get(k - 1);
    }

    private void inOrderTraversal(TreeNode node, List<Integer> inorderList) {
        if (node == null) return;

        inOrderTraversal(node.left, inorderList);
        inorderList.add(node.val);
        inOrderTraversal(node.right, inorderList);
    }

    // Find kth Largest Element in BST
    public int kthLargest(TreeNode root, int k) {
        List<Integer> reverseInorderList = new ArrayList<>();
        reverseInOrderTraversal(root, reverseInorderList);
        return reverseInorderList.get(k - 1);
    }

    private void reverseInOrderTraversal(TreeNode node, List<Integer> reverseInorderList) {
        if (node == null) return;

        reverseInOrderTraversal(node.right, reverseInorderList);
        reverseInorderList.add(node.val);
        reverseInOrderTraversal(node.left, reverseInorderList);
    }

    // Main method for testing all operations
    public static void main(String[] args) {
        // Create a sample binary search tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        BSTOperations bstOps = new BSTOperations();

        // Convert BST to Linked List
        TreeNode head = bstOps.convertToLinkedList(root);
        System.out.println("BST to Linked List:");
        bstOps.printLinkedList(head); // Output: 1 2 3 4 5 6 7

        // Find In-order Successor of a Node in BST
        TreeNode targetNode = root.left.right; // Node with value 3
        TreeNode successor = bstOps.inorderSuccessor(root, targetNode);
        System.out.println("\nIn-order Successor of " + targetNode.val + ": " + (successor != null ? successor.val : "null"));
        // Output: In-order Successor of 3: 4

        // Find Root-to-Leaf Paths in BST
        List<List<Integer>> paths = bstOps.rootToLeafPaths(root);
        System.out.println("Root-to-Leaf Paths in BST:");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
        // Output:
        // [4, 2, 1]
        // [4, 2, 3]
        // [4, 6, 5]
        // [4, 6, 7]

        // Find kth Smallest Element in BST
        int k = 3;
        int kthSmallest = bstOps.kthSmallest(root, k);
        System.out.println("Kth Smallest Element: " + kthSmallest); // Output: Kth Smallest Element: 3

        // Find kth Largest Element in BST
        int kthLargest = bstOps.kthLargest(root, k);
        System.out.println("Kth Largest Element: " + kthLargest); // Output: Kth Largest Element: 5
    }

    // Helper method to print the linked list
    private void printLinkedList(TreeNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.right;
        }
    }
}
