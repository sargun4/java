package heaps;

import java.util.PriorityQueue;

public class WeakestsoldierQ {
    static class row implements Comparable<row>{
        int soldiers;
        int idx;
        public row(int soldiers,int idx){
            this.idx=idx;
            this.soldiers=soldiers;
        }
        @Override
        public int compareTo(row r2){
            if(this.soldiers==r2.soldiers){
                return this.idx-r2.idx;
            }else{
                return this.soldiers-r2.soldiers;
            }
        }
    }
    public static void main(String[] args) {
        int army[][]={{1,0,0,0},
                      {1,1,1,1},
                      {1,0,0,0},
                      {1,0,0,0}};
        int k=2;
        PriorityQueue<row> pq=new PriorityQueue<>();
        for(int i=0;i<army.length;i++){
            int ctr=0;
            for(int j=0;j<army[0].length;j++){
                ctr+=army[i][j]==1?1:0;
            }
            pq.add(new row(ctr,i));
        }
        for(int i=0;i<k;i++){
            System.out.println("R"+pq.remove().idx);
        }
    }
}
