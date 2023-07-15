package DP.CATALAN;

public class Mountranges {
    //O(n*n)
    public static int MTranges(int n){
        int dp[]=new int[n+1];
        dp[0]=1;
        dp[1]=1;

        for(int i=2;i<n+1;i++){
            //for i pairs, we hv Ci mt ranges
            for(int j=0;j<i;j++){
                int inside=dp[j];
                int outside=dp[i-j-1];
                dp[i]+=inside*outside;
            }
        }
        //n pairs;
        return dp[n];
    }
    public static void main(String[] args) {
        int n=4;
        System.out.println(MTranges(n));
    }
}
