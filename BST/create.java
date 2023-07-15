package BST;

import java.util.ArrayList;

public class create {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
        }
    }
    public static void preorder(Node root){
        if(root==null) return;
        preorder(root.left);
        preorder(root.right);
        System.out.print(root.data+" ");
    }
    public static Node createBST(int arr[],int st,int end){    //O(n)
        if(st>end) return null;
        int mid=(st+end)/2;
        Node root=new Node(arr[mid]);
        root.left=createBST(arr, st, mid-1);
        root.right=createBST(arr, mid+1, end);
        return root;
    }

    public static void getinorder(Node root,ArrayList<Integer> inorder){
        if(root==null) return;
        getinorder(root.left, inorder);
        inorder.add(root.data);
        getinorder(root.right, inorder);
    }
    public static Node formBST(ArrayList<Integer>inorder,int st,int end){ //O(n)
        if(st>end) return null;
        int mid=(st+end)/2;
        Node root=new Node(inorder.get(mid));
        root.left=formBST(inorder, st, mid-1);
        root.right=formBST(inorder, mid+1, end);
        return root;
    }
    public static Node balanceBST(Node root){ //O(n)
        //inorder seq
        ArrayList<Integer> inorder=new ArrayList<>();
        getinorder(root, inorder);
        //sorted inorder->balanced BST
        root=formBST(inorder, 0, inorder.size()-1);
        return root;
    }
   
   
    public static void main(String[] args) {
        int arr[]={3,5,6,8,13,11,12};
        Node root=createBST(arr, 0, arr.length-1);
        preorder(root);
        System.out.println();
        root=balanceBST(root);
        preorder(root);
    }
}
