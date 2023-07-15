package heaps;

import java.util.PriorityQueue;
//SLIDING WINDOWS MAX 
//MAX OF ALL SUBARRAYS OF SIZE K
//O(nlogk) using pq
public class SlidingWindowsMaxQ {
    static class pair implements Comparable<pair>{
        int val;
        int idx;
        public pair(int val,int idx){
            this.val=val;
            this.idx=idx;
        }
        @Override
        public int compareTo(pair p2){
            return p2.val-this.val;
        }
    }
    public static void main(String[] args) {
        int arr[]={1,3,-1,-3,5,3,6,7};
        int k=3;//window size
        int res[]=new int[arr.length-k+1];//n-k+1

        PriorityQueue<pair> pq=new PriorityQueue<>();

        //1st window
        for(int i=0;i<k;i++){
            pq.add(new pair(arr[i], i));
        }
        res[0]=pq.peek().val;

        for(int i=k;i<arr.length;i++){
            while(pq.size()>0 && pq.peek().idx<=(i-k)){//if top elmnt doesnt come in current sliding window
                pq.remove();//remove it frm pq
            }
            pq.add(new pair(arr[i], i));//add curr elmnt
            res[i-k+1]=pq.peek().val;
        }
for(int i=0;i<res.length;i++){
    System.out.println(res[i]+" ");
}
    }
}
