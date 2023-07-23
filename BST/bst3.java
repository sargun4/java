package BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int data;
    Node left, right;
    public Node(int data){
        this.data=data;
        left=null;
        right=null;
    }
}
public class bst3 {
    public static int ht(Node root){
        if(root==null||(root.left==null && root.right==null)) return 0;
        int lht=ht(root.left);
        int rht=ht(root.right);
        return 1+Math.max(lht,rht);
    }
    public void nthLvl(Node root,int n,List<Integer> arr){
        if(root==null) return;
        if(n==1){
            arr.add(root.data);
            return;
        }
        nthLvl(root.left,n-1,arr);
        nthLvl(root.right,n-1,arr);
    }
    public void nthLvl2(Node root,int n,List<Integer> arr){
        if(root==null) return;
        if(n==1){
            arr.add(root.data);
            return;
        }
        nthLvl2(root.right,n-1,arr);
        nthLvl2(root.left,n-1,arr);
    }
    public List<List<Integer>> zigzagLevelOrder(Node root) {
        int lvl=ht(root)+1;
        List<List<Integer>> ans=new ArrayList<>();
        if(root==null) return ans;
        for(int i=1;i<=lvl;i++){
            List<Integer> arr=new ArrayList<>();
            if(i%2!=0) nthLvl(root,i,arr);
            else nthLvl2(root,i,arr);
            // System.out.println();
            ans.add(arr);
        }
        return ans;
    }
    public List<List<Integer>> zigzagLevelOrder2(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<Node> q = new LinkedList<>();
        int lvl = 0;
        q.add(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> lvlnodes = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                Node curr = q.remove();
                if (lvl % 2 == 0) {
                    lvlnodes.add(curr.data);
                } else {
                    lvlnodes.add(0, curr.data);
                }
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
            ans.add(lvlnodes);
            lvl += 1;
        }
        return ans;
    }
    

    public static void lvlorder(Node root){
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        List<List<Node>> res=new ArrayList<>();
        while(q.size()>0){
            ArrayList<Node> lvl=new ArrayList<>();
            for(int i=0;i<q.size();i++){
                Node temp=q.peek();
                lvl.add(temp);
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
                System.out.println(temp.data+" ");
                q.remove();
            }
            res.add(lvl);
        }
    }

    public static int floor(Node root,int data){
        int floor=-1;
        while(root!=null){
            if(root.data==data){
                floor=root.data;
                return floor;
            }
            if(data>root.data){
                floor=root.data;
                root=root.right;
            }else{
                root=root.left;
            }
        }
        return floor;
    }
    public static void main(String[] args) {
        
    }
}

// class Solution {
//     public boolean isValidBST(Node root) {
//         return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
//     }
//     public boolean isValidBST(Node root,long minval,long maxval){
//         if(root==null) return true;
//         if(root.data>=maxval||root.data<=minval) return false;
//         return isValidBST(root.left, minval, root.data)
//         && isValidBST(root.right, root.data, maxval);
//     }
// }
