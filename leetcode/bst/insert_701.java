class Solution { //O(logN)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null) return new TreeNode(val);
        if(root.val>val){//go left
            if(root.left==null) root.left=new TreeNode(val);
            else insertIntoBST(root.left,val);
        }else{//go right
            if(root.right==null) root.right=new TreeNode(val);
            else insertIntoBST(root.right,val);
        }
        return root;
    }
}
