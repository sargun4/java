package BST;

import java.util.LinkedList;
import java.util.Queue;

public class BoundaryTrav {
    public static void boundaryTrav(Node root){
        LeftBoundary(root);
        bottomBoundary(root);
        RightBoundary(root.right);
    }
    public static void LeftBoundary(Node root){
        if(root==null) return;
        if(root.left==null && root.right==null) return;
        System.out.print(root.data+" ");
        if(root.left!=null) LeftBoundary(root.left);
        else LeftBoundary(root.right);
    }
    public static void RightBoundary(Node root){
        if(root==null) return;
        if(root.left==null && root.right==null) return;
        
        if(root.right!=null) RightBoundary(root.right);
        else RightBoundary(root.left);
        System.out.print(root.data+" ");
    }
    public static void bottomBoundary(Node root){
        if(root==null) return;
        if(root.left==null && root.right==null){
            System.out.print(root.data+" ");
            return;
        }
        bottomBoundary(root.left);
        bottomBoundary(root.right);
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
        
        boundaryTrav(root);
    }
}
