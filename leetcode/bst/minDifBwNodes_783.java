// class Solution {
//     static int mindiff=Integer.MAX_VALUE;
//     static TreeNode prev=null;
//     public void inorder(TreeNode root){
//         if(root==null) return;
//         inorder(root.left);
//         if(prev!=null){
//             int diff=Math.abs(root.val-prev.val);
//             mindiff=Math.min(mindiff,diff);
//         }
//         prev=root;
//         inorder(root.right);
//     }
//     public int minDiffInBST(TreeNode root) {
//         mindiff=Integer.MAX_VALUE;
//         prev=null;
//         inorder(root);
//         return mindiff;
//     }
// }
