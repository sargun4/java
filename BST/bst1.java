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
        System.out.println("Maximum value in BST: " + maxVal); // Output: Maximum value in BST: 8

        // Find and print the minimum value in the BST
        int minVal = bst.findMin();
        System.out.println("Minimum value in BST: " + minVal); // Output: Minimum value in BST: 2

        // Check if a value exists in the BST
        int searchValue = 6;
        boolean exists = bst.search(searchValue);
        System.out.println("Value " + searchValue + " exists in BST: " + exists); // Output: Value 6 exists in BST: true

        // Find and print the height of the BST
        int height = bst.findHeight();
        System.out.println("Height of BST: " + height); // Output: Height of BST: 2

        // Perform level order traversal of the BST
        bst.levelOrderTraversal(); 
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