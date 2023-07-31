import java.util.*;
class Solution {
    int numberOfEnclaves(int[][] grid) {
        Queue<Pair> q = new LinkedList<Pair>();
        int n = grid.length; 
        int m = grid[0].length; 
        int vis[][] = new int[n][m];
        // traverse boundary elements
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                // first row, first col, last row, last col 
                if(i == 0 || j == 0 || i == n-1 || j == m-1) {
                    // if it is a land then store it in queue
                    if(grid[i][j] == 1) {
                        q.add(new Pair(i, j)); 
                        vis[i][j] = 1; 
                    }
                }
            }
        }
        int delrow[] = {-1, 0, +1, 0};
        int delcol[] = {0, +1, +0, -1}; 
        while(!q.isEmpty()) {
            int row = q.peek().first; 
            int col = q.peek().second; 
            q.remove(); 
            // traverses all 4 directions
            for(int i = 0;i<4;i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i]; 
                // check for valid coordinates and for land cell
                if(nrow >=0 && nrow <n && ncol >=0 && ncol < m 
                && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                    q.add(new Pair(nrow, ncol));
                    vis[nrow][ncol] = 1; 
                }
            }
        }
        int ctr = 0;
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                // check for unvisited land cell
                if(grid[i][j] == 1 & vis[i][j] == 0) 
                    ctr++; 
            }
        }
        return ctr;     
    }
    public static void main(String[] args){
        int grid[][] = {
        {0, 0, 0, 0},
        {1, 0, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}};
        Solution ob = new Solution();
        int ans = ob.numberOfEnclaves(grid);
        System.out.println(ans);
    }
}
class Pair {
    int first;
    int second; 
    public Pair(int first, int second) {
        this.first = first; 
        this.second = second; 
    }
}
// #include <bits/stdc++.h>
// using namespace std;
// class Solution {
// public:
//     int numEnclaves(vector<vector<int>>& grid) {
//         int n = grid.size();
//         int m = grid[0].size();
//         queue<pair<int, int>> q;
//         vector<vector<int>> vis(n, vector<int>(m, 0)); // Initializing a 2D vector with zeros
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 // First row, first col, last row, last col
//                 if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
//                     if (grid[i][j] == 1) { // That boundary cell is a land cell
//                         q.push({i, j});
//                         vis[i][j] = 1;
//                     }
//                 }
//             }
//         }
//         int drow[] = {-1, 0, 1, 0};
//         int dcol[] = {0, 1, 0, -1};
//         while (!q.empty()) {
//             int row = q.front().first;
//             int col = q.front().second;
//             q.pop();
//             // Traverse all neighbors
//             for (int i = 0; i < 4; i++) {
//                 int nrow = row + drow[i];
//                 int ncol = col + dcol[i];
//                 if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
//                     q.push({nrow, ncol});
//                     vis[nrow][ncol] = 1;
//                 }
//             }
//         }
//         int ctr = 0;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (grid[i][j] == 1 && vis[i][j] == 0) {
//                     ctr++;
//                 }
//             }
//         }
//         return ctr;
//     }
// };
// int main() {
//     vector<vector<int>> grid{
//         {0, 0, 0, 0},
//         {1, 0, 1, 0},
//         {0, 1, 1, 0},
//         {0, 0, 0, 0}};
//     Solution obj;
//     cout << obj.numberOfEnclaves(grid) << endl;
// }
