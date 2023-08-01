import java.util.*;
class Solution{
    private boolean dfs(int node, int col, int color[], 
    ArrayList<ArrayList<Integer>>adj) {
        color[node] = col; 
        // traverse adjacent nodes
        for(int it : adj.get(node)) {
            // if uncoloured
            if(color[it] == -1) {
                if(dfs(it, 1 - col, color, adj) == false) return false; 
            }
            // if previously coloured and have the same colour
            else if(color[it] == col) {
                return false; 
            }
        }
        return true; 
    }
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj){
        int color[] = new int[V];
	    for(int i = 0;i<V;i++) color[i] = -1; 
	    // for connected components
	    for(int i = 0;i<V;i++) {
	        if(color[i] == -1) {
	            if(dfs(i, 0, color, adj) == false) return false; 
	        }
	    }
	    return true; 
    }
     public static void main(String[] args){
        // V = 4, E = 4
        ArrayList < ArrayList < Integer >> adj = new ArrayList < > ();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        Solution obj = new Solution();
        boolean ans = obj.isBipartite(4, adj);
        if(ans)
            System.out.println("1");
        else System.out.println("0");
    }
}
//cpp
// #include<bits/stdc++.h>
// using namespace std;
// class Solution {
// private: 
//     bool dfs(int node, int col, int color[], vector<int> adj[]) {
//         color[node] = col; 
//         // traverse adjacent nodes
//         for(auto it : adj[node]) {
//             // if uncoloured
//             if(color[it] == -1) {
//                 if(dfs(it, !col, color, adj) == false) return false; 
//             }
//             // if previously coloured and have the same colour
//             else if(color[it] == col) {
//                 return false; 
//             }
//         }
//         return true; 
//     }
// public:
// 	bool isBipartite(int V, vector<int>adj[]){
// 	    int color[V];
// 	    for(int i = 0;i<V;i++) color[i] = -1; 
// 	    // for connected components
// 	    for(int i = 0;i<V;i++) {
// 	        if(color[i] == -1) {
// 	            if(dfs(i, 0, color, adj) == false) 
// 	                return false; 
// 	        }
// 	    }
// 	    return true; 
// 	}
// };
// void addEdge(vector <int> adj[], int u, int v) {
//     adj[u].push_back(v);
//     adj[v].push_back(u);
// }
// int main(){
// 	// V = 4, E = 4
// 	vector<int>adj[4];
// 	addEdge(adj, 0, 2);
//    	addEdge(adj, 0, 3);
// 	addEdge(adj, 2, 3);
// 	addEdge(adj, 3, 1);
// 	Solution obj;
// 	bool ans = obj.isBipartite(4, adj);    
// 	if(ans)cout << "1\n";
// 	else cout << "0\n";  	
// 	return 0;
// }
// // Output:  0
// // Time Complexity: O(V + 2E), Where V = Vertices, 2E is for total degrees as we traverse all adjacent nodes.
// // Space Complexity: O(3V) ~ O(V), Space for DFS stack space, colour array and an adjacency list.
