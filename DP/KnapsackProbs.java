package DP;

public class KnapsackProbs {
    // 0-1 Knapsack

    //memoization
    public static int knapsack(int val[],int wt[],int maxwt,int n){
        if(maxwt==0||n==0){//if capacity(maxwt) is 0 or no of items is 0
            return 0;
        }
        if(wt[n-1]<=maxwt){//valid 
            //include that wt
            int ans1=val[n-1]+knapsack(val, wt, maxwt-wt[n-1], n-1);
            //exclude it
            int ans2=knapsack(val, wt, maxwt, n-1);
            return Math.max(ans1,ans2);
        }else{//not valid
            return knapsack(val, wt, maxwt, n-1);
        }
    }
    //tabulation- O(n*maxwt)
     public static int knapsack(int val[],int wt[],int maxwt,int n,int dp[][]){
        if(maxwt==0||n==0){
            return 0;
        }
        if(dp[n][maxwt]!=-1){
            return dp[n][maxwt];
        }
        if(wt[n-1]<=maxwt){//valid 
            //include that wt
            int ans1=val[n-1]+knapsack(val, wt, maxwt-wt[n-1], n-1);
            //exclude it
            int ans2=knapsack(val, wt, maxwt, n-1);
            dp[n][maxwt]=Math.max(ans1,ans2);
            return dp[n][maxwt];
        }else{//not valid
            dp[n][maxwt]=knapsack(val, wt, maxwt, n-1);
            return dp[n][maxwt];
        }
    }
    // tabulation
    public static void print(int dp[][]){
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }
    public static int knapsackTab(int val[],int wt[],int maxwt){
        int n=val.length;
        int dp[][]=new int[n+1][maxwt+1];
        for(int i=0;i<dp.length;i++){//0th col
            dp[i][0]=0;
        }
        for(int j=0;j<dp[0].length;j++){//0th row
            dp[0][j]=0;
        }
        // i- no of items in knapsack, j- current capacity of variable knapsack
        for(int i=1;i<n+1;i++){
            for(int j=1;j<maxwt+1;j++){
                int v=val[i-1]; //ith item val
                int w=wt[i-1]; //ith item wt
                if(w<=j){//valid
                    int incProfit=v+dp[i-1][j-w];
                    int excProfit=dp[i-1][j];
                    dp[i][j]=Math.max(incProfit, excProfit);
                }else{//invalid
                    int excProfit=dp[i-1][j];
                    dp[i][j]=excProfit;
                }
            }
        }
        print(dp);
        return dp[n][maxwt];
    }
    
    //Unbounded Knapsack- O(n*maxwt)
    public static int UnboundedKnapsack(int val[],int wt[],int maxwt){
        int n=val.length;
        int dp[][]=new int[n+1][maxwt+1];
        for(int i=0;i<n+1;i++){
            dp[i][0]=0;
        }
        for (int j = 0; j < maxwt+1; j++) {
            dp[0][j]=0;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < maxwt+1; j++) {
                if(wt[i-1]<=j){//valid
                    dp[i][j]=Math.max(val[i-1]+dp[i][j-wt[i-1]],dp[i-1][j]);
                }else{//invalid
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        print(dp);
        return dp[n][maxwt];
    }
    public static void main(String[] args) {
        int val[]={15,14,10,45,30};
        int wt[]={2,5,1,3,4};
        int maxwt=7;
        System.out.println(UnboundedKnapsack(val, wt, maxwt));
        // System.out.println(knapsackTab(val, wt, maxwt));
        // //memoization
        // System.out.println(knapsack(val, wt, maxwt, val.length));
        // //tabulation
        // int dp[][]=new int[val.length+1][maxwt+1];
        // for(int i=0;i<dp.length;i++){
        //     for(int j=0;j<dp[0].length;j++){
        //         dp[i][j]=-1;
        //     }
        // }
        // System.out.println(knapsack(val, wt, maxwt, val.length,dp));

    }
}
