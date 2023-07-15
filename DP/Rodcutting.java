package DP;

public class Rodcutting {
    //O(n*total_rod_length)
     public static int RodCut(int price[],int length[],int tot_rod_len){
        int n=price.length;
        int dp[][]=new int[n+1][tot_rod_len+1];
        for(int i=0;i<n+1;i++){
            dp[i][0]=0;
        }
        for (int j = 0; j < tot_rod_len+1; j++) {
            dp[0][j]=0;
        }
        //unbounded knapsack logic
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < tot_rod_len+1; j++) {
                if(length[i-1]<=j){//valid
                    dp[i][j]=Math.max(price[i-1]+dp[i][j-length[i-1]],dp[i-1][j]);
                }else{//invalid
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        print(dp);
        return dp[n][tot_rod_len];
    }
    public static void print(int dp[][]){
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }
    public static void main(String[] args) {
        int length[]={1,2,3,4,5,6,7,8}; //pieces lengths
        int price[]={1,5,8,9,10,17,17,20};
        int tot_rod_len=8;
        System.out.println(RodCut(price, length, tot_rod_len));
    }
}
