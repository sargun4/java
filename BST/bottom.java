package xtra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class bottom {
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
    private static void printVertical(Node root, int givenLvl, int currLvl){
        if(root==null) return;
        if(currLvl==givenLvl){
            System.out.print(root.data);
            // return; //use for getting topview
        }
        printVertical(root.left, givenLvl, currLvl-1);
        printVertical(root.right, givenLvl, currLvl+1);
    }
    private static int right(Node root) {
        if(root==null) return 0;
        return 1+right(root.right);
    }
    private static int left(Node root) {
        if(root==null) return 0;
        return 1+left(root.left);
    }
    public static void main(String[] args) {
        String[] arr={"1","2","3","4","5","6","7"};
        //constr tree frm lvl order traversal
        Node root=constrBFS(arr);
        int lvl=ht(root)+1;
        for (int i = 1;i<=lvl; i++) {
            nthLvl(root,i);
            System.out.println();
        }
        int leftcount=left(root);
        int rightcount=right(root);
        HashMap<Integer,Integer> map=new HashMap<>();
        bottomView(root,0,map);
        // System.out.println("vertLine node");
        // for (int key : map.keySet()) {
        //     System.out.println(key+" "+map.get(key));
        // }
        for (int i = 1-leftcount;i<rightcount; i++) {//i=vertical line
            System.out.print(map.get(i)+" ");
        }
    }
    private static void bottomView(Node root, int lvl, HashMap<Integer, Integer> map) {
        if(root==null) return;
        if(map.containsKey(lvl)){
            map.remove(lvl);
            map.put(lvl, root.data);
        }else{
            map.put(lvl, root.data);
        }
        bottomView(root.right, lvl+1, map);
        bottomView(root.left, lvl-1, map);
    }
}
