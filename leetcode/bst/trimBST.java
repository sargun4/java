class Solution {
    public void helper(TreeNode root, int low, int high) {
        if(root==null) return;
        while(root.left!=null){
            if(root.left.val<low) root.left=root.left.right;
            else if(root.left.val>high) root.left=root.left.left;
            else break;
        }
        while(root.right!=null){
            if(root.right.val>high) root.right=root.right.left;
            else if(root.right.val<low) root.right=root.right.right;
            else break;
        }
        helper(root.left,low,high);
        helper(root.right,low,high);
    }
    public TreeNode trimBST(TreeNode root, int low, int high) {
        TreeNode parent=new TreeNode(Integer.MAX_VALUE);
        parent.left=root;
        helper(parent,low,high);
        return parent.left;
    }
}

// class Solution {
//     public TreeNode trimBST(TreeNode root, int low, int high) {
//         if (root == null) return null;
//         // If the current node's value is less than low,
//         // then we need to trim its left subtree and return the trimmed right subtree.
//         if (root.val < low) {
//             return trimBST(root.right, low, high);
//         }
//         // If the current node's value is greater than high,
//         // then we need to trim its right subtree and return the trimmed left subtree.
//         if (root.val > high) {
//             return trimBST(root.left, low, high);
//         }
//         // If the current node's value is within the range [low, high],        // then we need to recursively trim both its left and right subtrees.
//         root.left = trimBST(root.left, low, high);
//         root.right = trimBST(root.right, low, high);
//         // Return the updated root after the trimming process.
//         return root;
//     }
// }
