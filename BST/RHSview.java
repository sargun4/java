package BST;

import java.util.ArrayList;
import java.util.List;

class Node {
    int data;
    Node left;
    Node right;
    public Node(int data) {
        this.data = data;
    }
}
public class RHSview {
    public static int ht(Node root) {
        if (root == null || (root.left == null && root.right == null))
            return 0;
        int lht = ht(root.left);
        int rht = ht(root.right);
        return 1 + Math.max(lht, rht);
    }
    public void preorder(Node root, List<Integer> ans, int lvl) {
        if (root == null)
            return;
        ans.set(lvl - 1, root.data);
        preorder(root.left, ans, lvl + 1);
        preorder(root.right, ans, lvl + 1);
    }
    public List<Integer> rightSideView(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        int lvl = ht(root) + 1;
        for (int i = 0; i < lvl; i++) {
            ans.add(-1);
        }
        preorder(root, ans, 1);
        return ans;
    }
    public static void main(String[] args) {
        // Example usage:
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(5);
        root.right.right = new Node(4);
        RHSview rhsView = new RHSview();
        List<Integer> rightSideView = rhsView.rightSideView(root);
        // Print the right side view 
        for (int nodeValue : rightSideView) {
            if (nodeValue == -1) {
                System.out.print("  ");
            } else {
                System.out.print(nodeValue + " ");
            }
        }
        System.out.println();
    }
}
