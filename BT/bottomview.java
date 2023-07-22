import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}
public class BottomViewBinaryTree {
    public static void bottomView(TreeNode root) {
        if (root == null) return;
        // Create a TreeMap to store nodes based on their horizontal distance
        Map<Integer, Integer> bottomViewMap = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> distanceQueue = new LinkedList<>();
        queue.offer(root);
        distanceQueue.offer(0);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int distance = distanceQueue.poll();
            // Update the value in the map for each horizontal distance
            bottomViewMap.put(distance, node.val);
            // Enqueue the left child with distance - 1 and right child with distance + 1
            if (node.left != null) {
                queue.offer(node.left);
                distanceQueue.offer(distance - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                distanceQueue.offer(distance + 1);
            }
        }
        // Print the values in the bottom view map
        for (int value : bottomViewMap.values()) {
            System.out.print(value + " ");
        }
    }
    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);
        System.out.println("Bottom View of Binary Tree:");
        bottomView(root); // Output: 2 4 5 6 3
    }
}
