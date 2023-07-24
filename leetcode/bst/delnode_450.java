class Solution {
    public TreeNode minNode(TreeNode root){
        TreeNode curr=root;
        while(curr!=null && curr.left!=null){
            curr=curr.left;
        }
        return curr;
    }
    public TreeNode deleteNode(TreeNode root, int val) {
        if(root==null) return root;
        if(val<root.val){//if node to be inserted is less than root's data
            root.left=deleteNode(root.left, val); //go to left
        }else if(val>root.val){
            root.right=deleteNode(root.right, val);
        }else{//node found, del it
            if(root.left==null){
                TreeNode temp=root.right;
                // free(root);
                return temp;
            }
            else if(root.right==null){
                TreeNode temp=root.left;
                return temp;
            }
            TreeNode temp=minNode(root.right);
            root.val=temp.val;
            root.right=deleteNode(root.right, temp.val);
        }
        return root;
    }
}
