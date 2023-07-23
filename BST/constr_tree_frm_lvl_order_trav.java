package BST;

import java.util.LinkedList;
import java.util.Queue;
class Node{
    int data;
    Node left,right;
    public Node(int data){
        this.data=data;
    }
}
public class constr_tree_frm_lvl_order_trav {
    public static int ht(Node root){
        if(root==null|| (root.left==null && root.right==null)) return 0;
        int lht=ht(root.left);
        int rht=ht(root.right);                
        return 1+Math.max(lht, rht);
    }
    public static void nthLvl(Node root,int n){
        if(root==null) return;
        if(n==1){
            System.out.print(root.data+" ");
            return;
        }
        nthLvl(root.left,n-1);
        nthLvl(root.right,n-1);
    }
    public static Node constrBFS(String[] arr){
        int x=Integer.parseInt(arr[0]);
        int n=arr.length;
        Node root=new Node(x);
        Queue<Node> q=new LinkedList<>();
        int i=1;
        q.add(root);
        while(i<n-1){
            Node temp=q.remove();
            Node lchild=new Node(10); //random vals
            Node rchild=new Node(100);
            if(arr[i].equals("")){
                lchild=null;
            }else{ //if left child of temp is not null
                int l=Integer.parseInt(arr[i]);
                lchild.data=l;
                q.add(lchild);
            }
            if(arr[i+1].equals("")){
                rchild=null;
            }else{ //if left child of temp is not null
                int r=Integer.parseInt(arr[i+1]);
                rchild.data=r;
                q.add(rchild);
            }
            temp.left=lchild;
            temp.right=rchild;   
            i+=2;
        }
        return root;
    }
    public static void main(String[] args) {
        String[] arr={"1","2","3","4","5","","6","","7","","","8","","","","9",""};
        //constr tree frm lvl order traversal
        Node root=constrBFS(arr);
        int lvl=ht(root)+1;
        for (int i = 1;i<=lvl; i++) {
            nthLvl(root,i);
            System.out.println();
        }
    }
}
