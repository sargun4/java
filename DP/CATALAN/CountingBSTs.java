package DP.CATALAN;

public class CountingBSTs {
    //O(n*n)
    public static int countBST(int n){
        int dp[]=new int[n+1];
        dp[0]=1; //1 bst for 0 nodes
        dp[1]=1; //1 bst for 1 node
        for (int i = 2; i < n+1; i++) {
            //Ci- no of bsts which can be formed frm i nodes
            for (int j = 0; j < i; j++) {
                //Ci=Cj*Ci-j-1
                int left=dp[j];
                int right=dp[i-j-1];
                dp[i]+=left*right;
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        int n=3;
        System.out.println(countBST(n));
    }
}
