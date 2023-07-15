package BST;

import java.util.ArrayList;

public class BST {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
        }
    }
    public static Node insert(Node root,int data){
        if(root==null){
            root=new Node(data);
            return root;
        }
        if(root.data>data){
            root.left=insert(root.left, data);
        }else{//right subtree
            root.right=insert(root.right, data);
        }
        return root;
    }   
    public static boolean search(Node root,int data){//O(h) h-ht of BST
        if(root==null) return false;
        if(root.data==data) return true;
        if(root.data> data){
            return search(root.left, data);
        }else{
            return search(root.right, data);
        }
    }
    public static void inorder(Node root) {
        if(root==null) return;
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    
    public static Node del(Node root,int data){
        if(root.data<data){
            root.right=del(root.right,data);
        }else if(root.data>data){
            root.left=del(root.left,data);
        }else{//node found
            //case1-leaf node
            if(root.left==null && root.right==null){
                return null;
            }
            //case2-single child
            if(root.left==null){
                return root.right;
            }else if(root.right==null){
                return root.left;
            }
            //case3-2 children
            Node inordersuccNode=inordersucc(root.right); 
            root.data=inordersuccNode.data;
            del(root.right,inordersuccNode.data);
        }
        return root;
    }   
    public static Node inordersucc(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }
   
    public static void printinrange(Node root,int k1,int k2){
        if(root==null) return;
        if(root.data>=k1 && root.data<=k2){
            printinrange(root.left, k1, k2);
            System.out.print(root.data+" ");
            printinrange(root.right, k1, k2);
        }
        else if(root.data<k1){
            printinrange(root.left, k1, k2);
        }else{
            printinrange(root.right, k1, k2);
        }
    }
    
    public static void printpath(ArrayList<Integer>path){
        for(int i=0;i<path.size();i++){
            System.out.print(path.get(i)+"->");
        }System.out.println("Null");
    }
    public static void printroot2leaf(Node root,ArrayList<Integer> path){
        if(root==null) return;
        path.add(root.data);
        if(root.left==null && root.right==null){
            printpath(path);
        }
        printroot2leaf(root.left, path);
        printroot2leaf(root.right,path);
        path.remove(path.size()-1);
    }
   
    public static boolean isvalidBST(Node root,Node min,Node max){
        if(root==null) return true;
        if(min!=null && root.data<=min.data){
            return false;
        }
        else if(max!=null && root.data>=max.data){
            return false;
        }
        return isvalidBST(root.left, min, root) &&
                isvalidBST(root.right, root, max);
    }

    public static Node mirror(Node root){//O(n)
        if(root==null) return root;
        Node leftmirror=mirror(root.left);
        Node rightmirror=mirror(root.right);
        root.left=rightmirror;
        root.right=leftmirror;
        return root;
    }
    public static void preorder(Node root){
        if(root==null) return;
        preorder(root.left);
        preorder(root.right);
        System.out.print(root.data+" ");
    }


    public static void main(String[] args) {
        int values[]={5,1,3,4,2,7};
        Node root=null;
        for(int i=0;i<values.length;i++){
            root=insert(root, values[i]);
        }
        root=mirror(root);
        preorder(root);
        // inorder(root);
        // System.out.println();
        // del(root, 1);
        // inorder(root);
        // System.out.println();
        // printinrange(root, 5, 12);
        // printroot2leaf(root, new ArrayList<>());
        // if(isvalidBST(root, null, null)){
        //     System.out.println("isvalid");
        // }else{
        //     System.out.println("invalid");
        // }
    }
}
