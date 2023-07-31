import java.util.*;
class Solution{
   static boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, int s,
            boolean vis[], int parent[]){
       Queue<Node> q =  new LinkedList<>(); //BFS
       q.add(new Node(s, -1));
       vis[s] =true;
       // until the queue is empty
       while(!q.isEmpty()) {
           // source node and its parent node
           int node = q.peek().first;
           int par = q.peek().second;
           q.remove();       
           // go to all the adjacent nodes
           for(Integer it: adj.get(node)){
               if(vis[it]==false)  {
                   q.add(new Node(it, node));
                   vis[it] = true; 
               }
                // if adjacent node is visited and is not its own parent node
               else if(par != it) return true;
           }
       }
       return false;
    }
    // function to detect cycle in an undirected graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj){
        boolean vis[] = new boolean[V];
        Arrays.fill(vis,false);
        int parent[] = new int[V];
        Arrays.fill(parent,-1);  
        for(int i=0;i<V;i++)
            if(vis[i]==false) 
                if(checkForCycle(adj, i,vis, parent)) 
                    return true;
        return false;
    }
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);            
        Solution obj = new Solution();
        boolean ans = obj.isCycle(4, adj);
        if (ans)
            System.out.println("1");    
        else
            System.out.println("0");
    }
}
class Node {
    int first;
    int second;
    public Node(int first, int second) {
        this.first = first;
        this.second = second; 
    }
}

// #include <bits/stdc++.h>
// using namespace std;
// class Solution {
//   private: 
//   bool detect(int src, vector<int> adj[], int vis[]) {
//       vis[src] = 1; 
//       // store <source node, parent node>
//       queue<pair<int,int>> q; 
//       q.push({src, -1}); 
//       // traverse until queue is not empty
//       while(!q.empty()) {
//           int node = q.front().first; 
//           int parent = q.front().second; 
//           q.pop(); 
          
//           // go to all adjacent nodes
//           for(auto adjacentNode: adj[node]) {
//               // if adjacent node is unvisited
//               if(!vis[adjacentNode]) {
//                   vis[adjacentNode] = 1; 
//                   q.push({adjacentNode, node}); 
//               }
//               // if adjacent node is visited and is not it's own parent node
//               else if(parent != adjacentNode) {
//                   // yes it is a cycle
//                   return true; 
//               }
//           }
//       }
//       // there's no cycle
//       return false; 
//   }
//   public:
//     // Function to detect cycle in an undirected graph.
//     bool isCycle(int V, vector<int> adj[]) {
//         // initialise them as unvisited 
//         int vis[V] = {0};
//         for(int i = 0;i<V;i++) {
//             if(!vis[i]) {
//                 if(detect(i, adj, vis)) return true; 
//             }
//         }
//         return false; 
//     }
// };
// int main() {
//     // V = 4, E = 2
//     vector<int> adj[4] = {{}, {2}, {1, 3}, {2}};
//     Solution obj;
//     bool ans = obj.isCycle(4, adj);
//     if (ans)
//         cout << "1\n";
//     else
//         cout << "0\n";
//     return 0;
// }
// Output:  0

// Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we
// traverse all adjacent nodes. In the case of connected components of a graph, it will take another O(N) time.
// Space Complexity: O(N) + O(N) ~ O(N), Space for queue data structure and visited array.
