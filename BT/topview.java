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

public class TopViewBinaryTree {
    public static void topView(TreeNode root) {
        if (root == null) return;

        // Create a TreeMap to store nodes based on their horizontal distance
        Map<Integer, Integer> topViewMap = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> distanceQueue = new LinkedList<>();

        queue.offer(root);
        distanceQueue.offer(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int distance = distanceQueue.poll();

            // Store the node's value in the map if the distance is not already present
            if (!topViewMap.containsKey(distance)) {
                topViewMap.put(distance, node.val);
            }

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

        // Print the values in the top view map
        for (int value : topViewMap.values()) {
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

        System.out.println("Top View of Binary Tree:");
        topView(root); // Output: 2 1 3 6
    }
}

// class Solution{
//     //Function to return a list of nodes visible from the top view 
//     //from left to right in Binary Tree.
//     static ArrayList<Integer> topView(Node root){
//         ArrayList<Integer> ans = new ArrayList<>(); 
//         if(root == null) return ans;
//         Map<Integer, Integer> map = new TreeMap<>();
//         Queue<Pair> q = new LinkedList<Pair>();
//         q.add(new Pair(root, 0)); 
//         while(!q.isEmpty()) {
//             Pair it = q.remove();
//             int hd = it.hd; 
//             Node temp = it.node; 
//             if(map.get(hd) == null) map.put(hd, temp.data); 
//             if(temp.left != null) {                
//                 q.add(new Pair(temp.left, hd - 1)); 
//             }
//             if(temp.right != null) {                
//                 q.add(new Pair(temp.right, hd + 1)); 
//             }
//         }    
//         for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
//             ans.add(entry.getValue()); 
//         }
//         return ans;       
//     }
// }
