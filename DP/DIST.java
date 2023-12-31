package DP;

public class DIST {
    //given 2 strs, return min no of ops to convert str1 to str2
    public static int editDist(String str1,String str2){
        int n=str1.length();
        int m=str2.length();
        int dp[][]=new int[n+1][m+1];
        //initialize
        for(int i=0;i<n+1;i++){
            for (int j = 0; j < m+1; j++) {
                if(i==0){
                    dp[i][j]=j;
                }
                if(j==0){
                    dp[i][j]=i;
                }
            }
        }
        //bottom up
        for(int i=1;i<n+1;i++){
            for (int j = 1; j < m+1; j++) {
                if(str1.charAt(i-1)==str2.charAt(j-1)){//same
                    dp[i][j]=dp[i-1][j-1];
                }else{//diff
                    int add=dp[i][j-1]+1;
                    int del=dp[i-1][j]+1;
                    int repl=dp[i-1][j-1]+1;
                    dp[i][j]=Math.min(add, Math.min(del, repl));
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        String word1="intention";
        String word2="execution";
        //no of changes req to change str1 to str2
        System.out.println(editDist(word1, word2));
    }
}
