//dfs
class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        createGraph(root, graph);
        return maxDistance(graph, new HashSet<>(), start, 0, 0);
    }
    private void createGraph(TreeNode root, Map<Integer, List<Integer>> graph) {
        List<Integer> adjacent = graph.computeIfAbsent(root.val, parameter -> new ArrayList<>());

        if (root.left != null) {
            graph.computeIfAbsent(root.left.val, param -> new ArrayList<>()).add(root.val);
            adjacent.add(root.left.val);
            createGraph(root.left, graph);
        }
        if (root.right != null) {
            graph.computeIfAbsent(root.right.val, param -> new ArrayList<>()).add(root.val);
            adjacent.add(root.right.val);
            createGraph(root.right, graph);
        }
    }

    private int maxDistance(Map<Integer, List<Integer>> graph, Set<Integer> visited, int currentNode, int maxDistance, int currentDistance) {
        if (!visited.contains(currentNode)) {
            visited.add(currentNode);
            maxDistance = Math.max(maxDistance, currentDistance);

            for (int neighbour : graph.get(currentNode)) {
                maxDistance = Math.max(maxDistance(graph, visited, neighbour, maxDistance, currentDistance + 1), maxDistance);
            }
        }
        return maxDistance;
    }
}

// //bfs
// class Solution {
//     public int amountOfTime(TreeNode root, int start) {
//         Map<Integer, List<Integer>> graph = new HashMap<>();
//         createGraph(root, graph);
//         return maxDistance(graph, new LinkedList<>(List.of(start)), new HashSet<>(Set.of(start)));
//     }

//     private void createGraph(TreeNode root, Map<Integer, List<Integer>> graph) {
//         List<Integer> adjacent = graph.computeIfAbsent(root.val, parameter -> new ArrayList<>());

//         if (root.left != null) {
//             graph.computeIfAbsent(root.left.val, param -> new ArrayList<>()).add(root.val);
//             adjacent.add(root.left.val);
//             createGraph(root.left, graph);
//         }
//         if (root.right != null) {
//             graph.computeIfAbsent(root.right.val, param -> new ArrayList<>()).add(root.val);
//             adjacent.add(root.right.val);
//             createGraph(root.right, graph);
//         }
//     }

//     private int maxDistance(Map<Integer, List<Integer>> graph, Queue<Integer> queue, Set<Integer> visited) {
//         int maxDistance = 0;
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             while (size-- > 0) {
//                 int currentNode = queue.poll();
//                 for (int neighbour : graph.get(currentNode)) {
//                     if (!visited.contains(neighbour)) {
//                         queue.offer(neighbour);
//                         visited.add(neighbour);
//                     }
//                 }
//             }
//             maxDistance++;
//         }

//         return maxDistance - 1;
//     }
// }
// //bfs
// class Solution {
//     // Helper function to find the node with the given value 'start' in the tree
//     public TreeNode getNode(TreeNode root, int start) {
//         if (root == null) return null;
//         if (root.val == start) return root;
//         TreeNode left = getNode(root.left, start);
//         TreeNode right = getNode(root.right, start);
//         if (left == null) return right;
//         else return left;
//     }
//     // Preorder traversal to populate the parent HashMap
//     public void preorder(TreeNode root, HashMap<TreeNode, TreeNode> p) {
//         if (root == null) return;
//         if (root.left != null) p.put(root.left, root);
//         if (root.right != null) p.put(root.right, root);
//         preorder(root.left, p);
//         preorder(root.right, p);
//     }
//     public int amountOfTime(TreeNode root, int start) {
//         // Find the target node with value 'start' in the tree
//         TreeNode node = getNode(root, start);
//         if (node == null) return -1; // Node with the given value doesn't exist in the tree        
//         HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
//         // Populate the parent HashMap using the preorder traversal
//         preorder(root, parentMap);   
//         // BFS to find the amount of time to reach the target node
//         Queue<TreeNode> q = new LinkedList<>();
//         q.add(node);        
//         HashMap<TreeNode, Integer> lvls = new HashMap<>();
//         lvls.put(node, 0);        
//         while (!q.isEmpty()) {
//             TreeNode temp = q.poll();
//             int level = lvls.get(temp);            
//             if (temp.left != null && !lvls.containsKey(temp.left)) {
//                 q.add(temp.left);
//                 lvls.put(temp.left, level + 1);
//             }
//             if (temp.right != null && !lvls.containsKey(temp.right)) {
//                 q.add(temp.right);
//                 lvls.put(temp.right, level + 1);
//             }
//             if (parentMap.containsKey(temp) && !lvls.containsKey(parentMap.get(temp))) {
//                 q.add(parentMap.get(temp));
//                 lvls.put(parentMap.get(temp), level + 1);
//             }
//         }        
//         // Find the maximum level (time) reached during BFS traversal
//         int maxTime = -1;
//         for (int level : lvls.values()) {
//             maxTime = Math.max(maxTime, level);
//         }        
//         return maxTime;
//     }
// }
