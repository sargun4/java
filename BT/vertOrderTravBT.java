package BT;

import java.util.*;
class TreeNode {
    int data;
    TreeNode left, right;
    TreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
class Tuple {
    TreeNode node;
    int row;
    int col;
    public Tuple(TreeNode _node, int _row, int _col) {
        node = _node;
        row = _row;
        col = _col;
    }
}
public class vertOrderTravBT {
    public static List < List < Integer >> findVertical(TreeNode root) {
        TreeMap < Integer, TreeMap < Integer, PriorityQueue < Integer >>> map = new TreeMap < > ();
        Queue < Tuple > q = new LinkedList < Tuple > ();
        q.offer(new Tuple(root, 0, 0));
        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            TreeNode node = tuple.node;
            int x = tuple.row;
            int y = tuple.col;


            if (!map.containsKey(x)) {
                map.put(x, new TreeMap < > ());
            }
            if (!map.get(x).containsKey(y)) {
                map.get(x).put(y, new PriorityQueue < > ());
            }
            map.get(x).get(y).offer(node.data);

            if (node.left != null) {
                q.offer(new Tuple(node.left, x - 1, y + 1));
            }
            if (node.right != null) {
                q.offer(new Tuple(node.right, x + 1, y + 1));
            }
        }
        List < List < Integer >> list = new ArrayList < > ();
        for (TreeMap < Integer, PriorityQueue < Integer >> ys: map.values()) {
            list.add(new ArrayList < > ());
            for (PriorityQueue < Integer > nodes: ys.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }

    public static void main(String args[]) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(10);
        root.left.left.right = new TreeNode(5);
        root.left.left.right.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(10);

        List < List < Integer >> list = new ArrayList < > ();
        list = findVertical(root);

        System.out.println("The Vertical Traversal is : ");
        for (List < Integer > it: list) {
            for (int nodeVal: it) {
                System.out.print(nodeVal + " ");
            }
            System.out.println();
        }
    }
}

// //using 3 DS
//  public List<List<Integer>> verticalTraversal(TreeNode root) {
//         List<List<Integer>> ans = new ArrayList();
//         solve(root,ans);
//         return ans;
//     }
    
//     private void solve(TreeNode root,List<List<Integer>> ans){
//         Map<Integer,List<TreeNode>> map = new TreeMap();//distance to list of nodes map, final list for each dist
//         Map<TreeNode,Integer> distMap = new HashMap(); // each node and its dist, need for child dist
//         Map<TreeNode,Integer>levelMap = new HashMap();// each node and its level
//         Queue<TreeNode> q = new LinkedList();
//         q.add(root);
//         q.add(null);
//         int dist =0;
//         int level =0;
//         List<TreeNode> l = new ArrayList();
//         l.add(root);
//         map.put(0,l);
//         distMap.put(root,0);
//         levelMap.put(root,0);
//         level++; 
//         while(!q.isEmpty()){
//             TreeNode node = q.poll(); 
//             if(node==null){
            
//             if(!q.isEmpty()) q.add(null);
//              level++; 
//               continue;
//             }
//             //dist of parent
//              dist =  distMap.get(node);
                
//             if(node.left!=null){
//               //  map.get()
//                 q.add(node.left);
//                 distMap.put(node.left,dist-1);
//                 levelMap.put(node.left,level+1);
//                 addToList(map,node.left,dist-1,level+1,levelMap);
//             }
             
//             if(node.right!=null){
//                 q.add(node.right);
//                 distMap.put(node.right,dist+1);
//                 levelMap.put(node.right,level+1);
//                 addToList(map,node.right,dist+1,level+1,levelMap);
//             }
             
//         }
//        for (Map.Entry<Integer, List<TreeNode>> item : map.entrySet()) {
//           Integer key = item.getKey();
//           List<TreeNode> list = item.getValue();
//           List<Integer> listAns = new ArrayList();
//            for(int i=0;i<list.size();i++){
//               listAns.add(list.get(i).val); 
//            }
//            ans.add(listAns);
//         }
//     }
//     private void addToList(Map<Integer,List<TreeNode>> map,TreeNode node,int dist,
//                            int level,Map<TreeNode,Integer>levelMap){
//             List<TreeNode> list ;
//             if(map.containsKey(dist)){
//              list = map.get(dist);
//             }else{
//               list  = new ArrayList();
//               map.put(dist,list);
//             }
         
//             List <TreeNode> sub= new ArrayList();
          
         
//                 list.add(node);
              
//                 Iterator<TreeNode> itr = list.iterator();
//                 while(itr.hasNext()){
//                     TreeNode node1 = itr.next();
//                     if(levelMap.get(node1) ==level){
//                     sub.add(node1);
//                     itr.remove(); 
//                    }
//                 }
             
//                 Collections.sort(sub,(a,b)->{
//                     return a.val-b.val;
//                 });
             
//       list.addAll(sub);
//     }
// //using preorder
// import java.util.ArrayList;
// import java.util.TreeMap;

// public class VerticalOrderByPreOrder {

//     static class Node {
//         int data;
//         Node left;
//         Node right;

//         Node(int data) {
//             this.data = data;
//             this.left = null;
//             this.right = null;
//         }
//     }

//     static class Pair {// to store Nodes with its vertical line value or Column index
//         Node node;
//         int key; // will store each Node's Column index

//         Pair(Node node, int key) {
//             this.node = node;
//             this.key = key;
//         }
//     }

//     public static TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();

//     public static void verticalOrder(Pair pair) {
//         if (pair.node == null) {
//             return;
//         }
//         Node currNode = pair.node;
//         int colIndex = pair.key;
//         if (!map.containsKey(colIndex)) {
//             map.put(colIndex, new ArrayList<>());
//         }
//         map.get(colIndex).add(currNode.data);

//         verticalOrder(new Pair(currNode.left, colIndex - 1));
//         verticalOrder(new Pair(currNode.right, colIndex + 1));
//     }

//     public static void main(String[] args) {
//         Node root = new Node(1);
//         root.left = new Node(2);
//         root.right = new Node(3);
//         root.left.left = new Node(4);
//         root.left.right = new Node(5);
//         root.right.left = new Node(6);
//         root.right.right = new Node(7);

//         verticalOrder(new Pair(root, 0));
//         System.out.println(map.values());
//     }
// }

// //in cpp
// static bool comp(vector<int> &a,vector<int> &b) {
//         if(a[0]==b[0] && a[1]==b[1]) {
//             return a[2]<b[2];
//         }
//         else if(a[1]==b[1]) {
//             return a[0]<b[0];
//         }
//         return a[1]<b[1];
//     }
    
    
//     vector<vector<int>> verticalTraversal(TreeNode* root) {
//         vector<vector<int>> res;
//         if(root==NULL) {
//             return res;
//         }
//         vector<vector<int>> temp;
//         queue<TreeNode*> q;
//         q.push(root);
//         temp.push_back({0,0,root->val});
//         int index=0;
//         while(!q.empty()) {
//             TreeNode* node=q.front();
//             q.pop();
//             if(node->left) {
//                 q.push(node->left);
//                 temp.push_back({temp[index][0]+1,temp[index][1]-1,node->left->val});
//             }
//             if(node->right) {
//                 q.push(node->right);
//                 temp.push_back({temp[index][0]+1,temp[index][1]+1,node->right->val});
//             }
//             index++;
//         }
//         sort(temp.begin(),temp.end(),comp);
//         vector<int> v;
//         v.push_back(temp[0][2]);
//         int cur_col=temp[0][1];
//         for(int i=1;i<temp.size();i++) {
//             if(cur_col==temp[i][1]) {
//                 v.push_back(temp[i][2]);
//             }
//             else {
//                 res.push_back(v);
//                 v.clear();
//                 v.push_back(temp[i][2]);
//                 cur_col=temp[i][1];
//             }
//         }
//         res.push_back(v);
//         return res;
//     }
