//rev inorder traversal
class Solution {
    static int sum=0;
    public void helper(TreeNode root){
        if(root==null) return;
        helper(root.right);
        root.val=root.val+sum;
        sum=root.val;
        helper(root.left);
    }
    public TreeNode convertBST(TreeNode root) {
        sum=0;
        helper(root);
        return root;
    }
}
