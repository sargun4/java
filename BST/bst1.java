import java.util.*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class bst1 {
    private TreeNode root;
    public void insert(int val) {
        root = insertNode(root, val);
    }
    private TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else if (val > root.val) {
            root.right = insertNode(root.right, val);
        }
        return root;
    }
    // Create a BST from an array
    public void createBST(int[] values) {
        for (int val : values) {
            insert(val);
        }
    }
    // Delete a node in the BST
    public void delete(int val) {
        root = deleteNode(root, val);
    }
    private TreeNode deleteNode(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) {
            root.left = deleteNode(root.left, val);
        } else if (val > root.val) {
            root.right = deleteNode(root.right, val);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.val = findMinValue(root.right);
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }
    private int findMinValue(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }
    //Lowest Common Ancestor (LCA) of two nodes in the BST
    public TreeNode lca(int p, int q) {
        return findLCA(root, p, q);
    }
    private TreeNode findLCA(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    // Inorder Predecessor and Successor in BST
    public TreeNode inorderPredecessor(int val) {
        TreeNode predecessor = null;
        TreeNode current = root;

        while (current != null) {
            if (val <= current.val) {
                current = current.left;
            } else {
                predecessor = current;
                current = current.right;
            }
        }

        return predecessor;
    }

    public TreeNode inorderSuccessor(int val) {
        TreeNode successor = null;
        TreeNode current = root;

        while (current != null) {
            if (val >= current.val) {
                current = current.right;
            } else {
                successor = current;
                current = current.left;
            }
        }

        return successor;
    }

    // Helper method to print the BST using in-order traversal
    public void printInorder() {
        System.out.print("Inorder Traversal: ");
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("BST is empty.");
        }

        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.val;
    }

    // Find the minimum value in the BST
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("BST is empty.");
        }

        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.val;
    }

    // Check if a given value exists in the BST
    public boolean search(int val) {
        TreeNode current = root;
        while (current != null) {
            if (val == current.val) {
                return true;
            } else if (val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    // Find the height of the BST
    public int findHeight() {
        return getHeight(root);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // Level order traversal of the BST
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        System.out.print("Level Order Traversal: ");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        System.out.println();
    }
    public boolean isBalanced() {
        return checkBalanced(root) != -1;
    }

    private int checkBalanced(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkBalanced(node.left);
        int rightHeight = checkBalanced(node.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Convert a sorted array to a balanced BST
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = sortedArrayToBST(nums, left, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, right);

        return root;
    }

    // Find the kth smallest element in the BST
    public int kthSmallest(int k) {
        List<Integer> inorderList = new ArrayList<>();
        inOrderTraversal(root, inorderList);
        return inorderList.get(k - 1);
    }

    // Find the kth largest element in the BST
    public int kthLargest(int k) {
        List<Integer> reverseInorderList = new ArrayList<>();
        reverseInOrderTraversal(root, reverseInorderList);
        return reverseInorderList.get(k - 1);
    }
    public int size() {
        return countNodes(root);
    }

    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // Find the sum of all values in the BST
    public int sum() {
        return computeSum(root);
    }

    private int computeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.val + computeSum(node.left) + computeSum(node.right);
    }

    // Check if the BST is a valid Binary Search Tree
    public boolean isValidBST() {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long minVal, long maxVal) {
        if (node == null) {
            return true;
        }

        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }

        return isValidBST(node.left, minVal, node.val) && isValidBST(node.right, node.val, maxVal);
    }

    // Convert a Binary Search Tree to a sorted doubly linked list
    public TreeNode convertToDLL() {
        TreeNode[] prev = new TreeNode[1];
        TreeNode[] head = new TreeNode[1];
        convertToDLL(root, prev, head);
        return head[0];
    }

    private void convertToDLL(TreeNode node, TreeNode[] prev, TreeNode[] head) {
        if (node == null) {
            return;
        }

        convertToDLL(node.left, prev, head);

        if (prev[0] == null) {
            head[0] = node;
        } else {
            prev[0].right = node;
            node.left = prev[0];
        }

        prev[0] = node;

        convertToDLL(node.right, prev, head);
    }
    
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] values = {5, 3, 7, 2, 4, 6, 8};
        bst.createBST(values
        bst.printInorder(); // Inorder Traversal: 2 3 4 5 6 7 8
        TreeNode lca = bst.lca(4, 6);
        System.out.println("LCA of 4 and 6: " + lca.val); //LCA of 4 and 6: 5
      
        TreeNode predecessor = bst.inorderPredecessor(6);
        TreeNode successor = bst.inorderSuccessor(6);
        System.out.println("Inorder Predecessor of 6: " + (predecessor != null ? predecessor.val : "null")); //Inorder Predecessor of 6: 5
        System.out.println("Inorder Successor of 6: " + (successor != null ? successor.val : "null")); //Inorder Successor of 6: 7

        bst.delete(5);
        bst.printInorder(); //Inorder Traversal: 2 3 4 6 7 8
        int maxVal = bst.findMax();
        System.out.println("Maximum value in BST: " + maxVal); //Maximum value in BST: 8

        // Find and print the minimum value in the BST
        int minVal = bst.findMin();
        System.out.println("Minimum value in BST: " + minVal); //Minimum value in BST: 2

        // Check if a value exists in the BST
        int searchValue = 6;
        boolean exists = bst.search(searchValue);
        System.out.println("Value " + searchValue + " exists in BST: " + exists); //Value 6 exists in BST: true

        // Find and print the height of the BST
        int height = bst.findHeight();
        System.out.println("Height of BST: " + height); //Height of BST: 2

        // Perform level order traversal of the BST
        bst.levelOrderTraversal(); 

        boolean isBalanced = bst.isBalanced();
        System.out.println("Is the BST balanced? " + isBalanced); //Is the BST balanced? true

        // Convert a sorted array to a balanced BST
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode balancedBST = bst.sortedArrayToBST(sortedArray);
        bst.root = balancedBST;
        bst.printInorder(); //Inorder Traversal: 1 2 3 4 5 6 7 8

        // Find the kth smallest and largest elements in the BST
        int k = 3;
        int kthSmallest = bst.kthSmallest(k);
        int kthLargest = bst.kthLargest(k);
        System.out.println("Kth Smallest Element: " + kthSmallest); //Kth Smallest Element: 3
        System.out.println("Kth Largest Element: " + kthLargest); //Kth Largest Element: 6
        int size = bst.size();
        System.out.println("Number of nodes in the BST: " + size); //Number of nodes in the BST: 7

        // Find and print the sum of all values in the BST
        int sum = bst.sum();
        System.out.println("Sum of all values in the BST: " + sum); //Sum of all values in the BST: 35

        // Check if the BST is a valid Binary Search Tree
        boolean isValid = bst.isValidBST();
        System.out.println("Is the BST a valid Binary Search Tree? " + isValid); //Is the BST a valid Binary Search Tree? true

        // Convert the BST to a sorted doubly linked list and print the list
        TreeNode head = bst.convertToDLL();
        System.out.print("Sorted Doubly Linked List: ");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.right;
        }
        //Sorted Doubly Linked List: 2 3 4 5 6 7 8
    }
}

//del node 
// class Solution {
//     public TreeNode deleteNode(TreeNode root, int key) {
//         if (root == null) {
//             return null;
//         }
//         if (root.val == key) {
//             return helper(root);
//         }
//         TreeNode dummy = root;
//         while (root != null) {
//             if (root.val > key) {
//                 if (root.left != null && root.left.val == key) {
//                     root.left = helper(root.left);
//                     break;
//                 } else {
//                     root = root.left;
//                 }
//             } else {
//                 if (root.right != null && root.right.val == key) {
//                     root.right = helper(root.right);
//                     break;
//                 } else {
//                     root = root.right;
//                 }
//             }
//         }
//         return dummy;
//     }
//     public TreeNode helper(TreeNode root) {
//             if (root.left == null) {
//                 return root.right;
//             } else if (root.right == null){
//                 return root.left;
//             } else {
//                 TreeNode rightChild = root.right;
//                 TreeNode lastRight = findLastRight(root.left);
//                 lastRight.right = rightChild;
//                 return root.left;
//             }
//     }
//     public TreeNode findLastRight(TreeNode root) {
//         if (root.right == null) {
//             return root;
//         }
//         return findLastRight(root.right);
//     }
// }
