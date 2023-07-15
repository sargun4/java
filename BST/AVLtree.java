package BST;

public class AVLtree {
    static class Node{
        int data,ht;
        Node left,right;
        Node(int data){
            this.data=data;
            ht=1;
        }
    }
    public static Node root;
    public static int ht(Node root){
        if(root==null) return 0;
        return root.ht;
    }
    static int max(int a,int b){
        return (a>b)?a:b;
    }
    public static int getbalancefactor(Node root){
        if(root==null){
            return 0;
        }
        return ht(root.left)-ht(root.right);
    }
    public static Node leftrotate(Node x){
        Node y=x.right;
        Node t2=y.left;
        //perform rotation
        y.left=x;
        x.right=t2;
        //update hts
        x.ht=max(ht(x.left),ht(x.right))+1;
        y.ht=max(ht(y.left),ht(y.right))+1;
        return y;//new root
    }
    public static Node rightrotate(Node y){
        Node x=y.left;
        Node t2=x.right;
        //perform rotation
        y.left=t2;
        x.right=y;
        //update hts
        x.ht=max(ht(x.left),ht(x.right))+1;
        y.ht=max(ht(y.left),ht(y.right))+1;
        return x;//new root
    }

    public static Node insert(Node root,int data) {
        if(root==null){
            return new Node(data);
        }
        if(data<root.data){
            root.left=insert(root.left, data);
        }else if(data>root.data){
            root.right=insert(root.right, data);
        }else{
            return root;
        }
        //update root ht
        root.ht=1+Math.max(ht(root.left),ht(root.right));
        int bf=getbalancefactor(root);

        //LL
        if(bf>1 && data<root.left.data){
            return rightrotate(root);
        }
        //RR
        if(bf<-1 && data>root.right.data){ //data-val to be inserted
            return leftrotate(root);
        }
        //LR
        if(bf>1 && data>root.left.data){
            root.left=leftrotate(root.left);
            return rightrotate(root);
        }
        //RL
        if(bf<-1 && data<root.right.data){
            root.right=rightrotate(root.right);
            return leftrotate(root);
        }
        return root; //if avl is balanced
    }
    public static void preorder(Node root){
        if(root==null) return;
        preorder(root.left);
        preorder(root.right);
        System.out.print(root.data+" "); 
    }
    public static void main(String[] args) {
        root=insert(root,10);
        root=insert(root,20);
        root=insert(root,30);
        root=insert(root,40);
        root=insert(root,50);
        root=insert(root,25);

        preorder(root);
    }
}
