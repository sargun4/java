package GRAPHS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class cheapestFlightsQ {
    static class edge{
        int src;
        int dest;
        int wt;
        public edge(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.wt=w;
        }
    }
    public static void creategraph(int flights[][],ArrayList<edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<flights.length;i++){
            int src=flights[i][0];
            int dest=flights[i][1];
            int wt=flights[i][2];
            edge e=new edge(src, dest, wt);
            graph[src].add(e);
        }
    }
    static class info{
        int v;
        int cost;
        int stops;
        public info(int v,int c,int s){
            this.v=v;
            this.cost=c;
            this.stops=s;
        }
    }
    public static int cheapestFlight(int n,int flights[][],int src,int dest,int k){
        ArrayList<edge> graph[]=new ArrayList[n];
        creategraph(flights, graph);

        int dist[]=new int[n];
        for(int i=0;i<n;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        Queue<info> q=new LinkedList<>();
        q.add(new info(src, 0, 0));
        while(!q.isEmpty()){
            info curr=q.remove();
            if(curr.stops>k){
                break;
            }
            for(int i=0;i<graph[curr.v].size();i++){
                edge e=graph[curr.v].get(i);
                int u=e.src;
                int v=e.dest;
                int wt=e.wt;
                if(dist[u]!=Integer.MAX_VALUE && curr.cost+wt<dist[v] && curr.stops<=k){
                    dist[v]=curr.cost+wt;
                    q.add(new info(v, dist[v], curr.stops+1));
                }
            }
        }
        if(dist[dest]==Integer.MAX_VALUE){
            return -1;
        }else{
            return dist[dest];
        }

        
    }

    public static void main(String[] args) {
        int n=4;
        int flights[][]={{0,1,100},{2,0,100},{1,3,600},{2,3,200}};
        int src=0,dest=3,k=1;
        cheapestFlight(n, flights, src, dest, k);
    }
}
