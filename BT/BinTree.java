package BT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BinTree {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    static class BinaryTree{
        static int idx=-1;
       public static Node buildTree(int arr[]) { //O(n)
            idx++;
            if (arr[idx] == -1) {
                return null;
            }
            Node newnode = new Node(arr[idx]);
            newnode.left = buildTree(arr);
            newnode.right = buildTree(arr);
            return newnode;
        }
        public static void preorder(Node root){//O(n)
            if(root==null) return;
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }
        
        //lvl order traversal
        public static void lvlorder(Node root){
            if(root==null) return;
            Queue<Node> q=new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
                Node currNode=q.remove();
                if(currNode==null){
                    System.out.println();
                    if(q.isEmpty()){
                        break;
                    }else{
                        q.add(null);
                    }
                }else{
                    System.out.print(currNode.data+" ");
                    if(currNode.left!=null){
                        q.add(currNode.left);
                    }
                    if(currNode.right!=null){
                        q.add(currNode.right);
                    }
                }
            }
        }
    
        public static int ht(Node root){//O(n)
            if(root==null) return 0;
            int lht=ht(root.left);
            int rht=ht(root.right);
            return Math.max(lht,rht)+1;
        }

        public static int count(Node root){//O(n)
            if(root==null) return 0;
            int lcount=count(root.left);
            int rcount=count(root.right);
            return lcount+rcount+1;
        }
    
        public static int sum(Node root){
            if(root==null) return 0;
            int lsum=sum(root.left);
            int rsum=sum(root.right);
            return lsum+rsum+root.data;
        }

        public static int diam(Node root){//O(n^2)
            if(root==null) return 0;
            int ldiam=diam(root.left);
            int rdiam=diam(root.right);
            int lht=ht(root.left);
            int rht=ht(root.right);
            int selfdiam=lht+rht+1;
            return Math.max(Math.max(ldiam,rdiam),selfdiam);
        }
    
        static class info{
            int diam;
            int ht;
            public info(int diam,int ht){
                this.diam=diam;
                this.ht=ht;
            }
        }
        public static info diam2(Node root){
            if(root==null) return new info(0, 0);
            info linfo=diam2(root.left);
            info rinfo=diam2(root.right);
            int diam =Math.max(Math.max(linfo.diam,rinfo.diam),linfo.ht+rinfo.ht+1);
            int ht=Math.max(linfo.ht,rinfo.ht)+1;
            return new info(diam, ht);
        }
    }
    
    public static boolean isidentical(Node node,Node subroot){
            if(node==null && subroot==null){
                return true;
            }else if(node==null|| subroot==null||node.data!=subroot.data){
                return false;
            }
            if(!isidentical(node.left, subroot.left)){
                return false;
            }
            if(!isidentical(node.right, subroot.right)){
                return false;
            }
            return true;
        }
    public static boolean isSubtree(Node root,Node subroot){
        if(root==null) return false;
        if(root.data==subroot.data){
            if(isidentical(root,subroot)){
                return true;
            }
        }   
        return isSubtree(root.left, subroot)|| isSubtree(root.right, subroot);
     }

    public class Info2{
        Node node;
        int hd; //horizontal dist
        public Info2(Node node,int hd){
            this.node=node;
            this.hd=hd;
        }
    }
    public static void topview(Node root){
        //lvl order
        Queue<Info2> q=new LinkedList<>();
        HashMap<Integer,Node> map=new HashMap<>();
        int min=0,max=0;
        q.add(new Info2(root,0));
        q.add(null);

        while(!q.isEmpty()){
            Info2 curr=q.remove();
            if(curr==null){
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }
            else{
                if(!map.containsKey(curr.hd)){ //first time hd is occuring
                    map.put(curr.hd, curr.node);
                }
                if(curr.node.left!=null){
                    q.add(new Info2(curr.node.left, curr.hd-1));
                    min=Math.min(min,curr.hd-1);
                }
                if(curr.node.right!=null){
                    q.add(new Info2(curr.node.right, curr.hd+1));
                    max=Math.max(max,curr.hd+1);
                }
            }
        }
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data+" ");
        }System.out.println();
    }

    public static void kthlvl(Node root,int lvl,int k){
        if(root==null){
            return;
        }
        if(lvl==k){
            System.out.print(root.data+" ");
            return;
        }
        kthlvl(root.left, lvl+1, k);
        kthlvl(root.right, lvl+1, k);
    }
   
    public static boolean getpath(Node root,int n,ArrayList<Node> path){
        if(root==null) return false;
        path.add(root);
        if(root.data==n) return true;
        boolean foundleft=getpath(root.left, n, path);
        boolean foundright=getpath(root.right, n, path);
        if(foundleft||foundright) return true;
        path.remove(path.size()-1);
        return false;
    }
    public static Node lca(Node root,int n1,int n2){//O(n)
        ArrayList<Node> path1=new ArrayList<>();
        ArrayList<Node> path2=new ArrayList<>();
        getpath(root,n1,path1);
        getpath(root,n2,path2);

        int i=0;
        for(i=0;i<path1.size() && i<path2.size();i++){
            if(path1.get(i)!=path2.get(i)){
                break;
            }
        }
        Node lca=path1.get(i); //last equal node- (i-1)th
        return lca;
    }
   
    public static Node lca2(Node root,int n1,int n2){
        if((root==null)||root.data==n1||root.data==n2){
            return root;
        }
        Node leftlca=lca2(root.left,n1,n2);
        Node rightlca=lca2(root.right,n1,n2);
        if(rightlca==null){
            return leftlca;
        }
        if(leftlca==null){
            return rightlca;
        }
        return root;
    }
    
    public static int lcaDist(Node root,int n){
        if(root==null) return -1;
        if(root.data==n){
            return 0;
        }
        int leftdist=lcaDist(root.left, n);
        int rightdist=lcaDist(root.right, n);
        if(leftdist==-1 && rightdist==-1){
            return -1;
        }else if(leftdist==-1){
            return rightdist+1;
        }else{
            return leftdist+1;
        }

    }
    public static int mindist(Node root,int n1,int n2){
        Node lca=lca2(root,n1,n2);
        int dist1=lcaDist(lca,n1);
        int dist2=lcaDist(lca,n2);
        return dist1+dist2;
    }
    
    public static int kthAncestor(Node root,int n,int k) {
        if(root==null) return -1;
        if(root.data==n) return 0;
        int leftdist=kthAncestor(root.left, n, k);
        int rightdist=kthAncestor(root.right, n, k);

        if(leftdist==-1 && rightdist==-1){
            return -1; //doesnt exist
        }
        int max=Math.max(leftdist,rightdist);
        if(max+1==k){
            System.out.println(root.data);
        }
        return max+1;
    }

    public static int sumtree(Node root){
        if(root==null) return 0;
        int lchild=sumtree(root.left);
        int rchild=sumtree(root.right);
        int data=root.data;
        int newleft=root.left==null?0:root.left.data;
        int newright=root.right==null?0:root.right.data;
        root.data=newleft+lchild+newright+rchild;
        return data;
    }
    public static void preorder(Node root){
        if(root==null) return;
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
    public static void main(String[] args) {
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree=new BinaryTree();
        Node root=tree.buildTree(nodes);
        // System.out.println(root.data);
        // tree.preorder(root);
        tree.lvlorder(root);
        System.out.println("Ht of tree = "+tree.ht(root));
        System.out.println("No of nodes in tree = "+tree.count(root));
        System.out.println("sum of tree = "+tree.sum(root));
        System.out.println("diameter of tree = "+tree.diam(root));
        
        // Node subroot=new Node(2);
        // subroot.left=new Node(4);
        // subroot.right=new Node(5);

        // System.out.println(isSubtree(root, subroot));
        // topview(root);
        // int k=2;
        // kthlvl(root, 1, 3);
        // int n1=4,n2=6;
        // System.out.println(lca2(root, n1, n2).data);
        // System.out.println(mindist(root, n1, n2));
        // int n=5,k=2;
        // kthAncestor(root, n, k);

        sumtree(root);
        preorder(root);
    }
}
