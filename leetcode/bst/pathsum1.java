class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;
        if(root!=null && root.left==null && root.right==null){
            if(root.val==targetSum) return true;
        } 
        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);
    }
}
 // class Solution {
//     public boolean hasPathSum(TreeNode root, int sum) {
//         if(root==null)return false;
//         sum = sum - root.val;
//         if(root.left==null && root.right==null){
//             if(sum == 0)return true;
//             else return false;
//         }
//         return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
        
        
//     }
public boolean hasPathSum(TreeNode root, int sum) {
    // iteration method
    if (root == null) {return false;}
    Stack<TreeNode> path = new Stack<>();
    Stack<Integer> sub = new Stack<>();
    path.push(root);
    sub.push(root.val);
    while (!path.isEmpty()) {
        TreeNode temp = path.pop();
        int tempVal = sub.pop();
        if (temp.left == null && temp.right == null) {if (tempVal == sum) return true;}
        else {
            if (temp.left != null) {
                path.push(temp.left);
                sub.push(temp.left.val + tempVal);
            }
            if (temp.right != null) {
                path.push(temp.right);
                sub.push(temp.right.val + tempVal);
            }
        }
    }
    return false;
}
