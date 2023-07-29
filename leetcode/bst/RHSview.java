class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        List<Integer> l=new ArrayList<Integer>();
        if(root==null) return l;
        while(!q.isEmpty()){
            int nodesAtCurrLvl=q.size();
            for(int i=0;i<nodesAtCurrLvl;i++){
                TreeNode cur = q.poll();
                if (i == 0) l.add(cur.val);
                if (cur.right != null) q.add(cur.right);
                if (cur.left != null) q.add(cur.left);
            }
        }
        return l;
    }
}
// class Solution {
//     public List<Integer> rightSideView(TreeNode root) {
//         List<Integer> list = new ArrayList<Integer>();
//         if (root == null) return list;
//         bfs(list, root);
//         return list;
//     }
//     public void bfs(List<Integer> list, TreeNode root) {
//         Queue<TreeNode> q = new LinkedList<>();
//         q.add(root);
//         while (!q.isEmpty()) {
//             int levelSize = q.size();
//             for (int i = 0; i < levelSize; i++) {
//                 TreeNode cur = q.poll();
//                 if (i == 0) list.add(cur.val);
//                 if (cur.right != null) q.add(cur.right);
//                 if (cur.left != null) q.add(cur.left);
//             }
//         }
//     }
// }
// class Solution {
//     public static int ht(TreeNode root){
//         if(root==null || (root.left==null && root.right==null)) return 0;
//         int lht=ht(root.left);
//         int rht=ht(root.right);
//         return 1+Math.max(lht,rht);
//     }
//     public void preorder(TreeNode root,List<Integer> ans,int lvl){
//         if(root==null) return;
//         ans.set(lvl-1,root.val);
//         preorder(root.left,ans,lvl+1);
//         preorder(root.right,ans,lvl+1);
//     }
//     public List<Integer> rightSideView(TreeNode root) {
//         List<Integer> ans=new ArrayList<>();
//         if(root==null) return ans;
//         int lvl=ht(root)+1;
//         for(int i=0;i<lvl;i++){
//             ans.add(-1);
//         }
//         preorder(root,ans,1);
//         return ans;
//     }
// }
