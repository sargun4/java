package GRAPHS;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Primsalgo {
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
    public static void creategraph(ArrayList<edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new edge(0,1,2));
        graph[0].add(new edge(0,2,4));

        graph[1].add(new edge(1,2,-4));

        graph[2].add(new edge(2,3,2));

        graph[3].add(new edge(3,4,4));

        graph[4].add(new edge(4,1,-1));
    }
    static class pair implements Comparable<pair>{
        int v;
        int cost;
        public pair(int v,int c){
            this.v=v;
            this.cost=c;
        }
        @Override
        public int compareTo(pair p2){
            return this.cost-p2.cost; //ascending order sorted
        }
    }
    //O(ElogE)
    public static void prims(ArrayList<edge> graph[]){
        boolean vis[]=new boolean[graph.length];
        PriorityQueue<pair> pq=new PriorityQueue<>();

        pq.add(new pair(0, 0));
        int finalcost=0;
        while(!pq.isEmpty()){
            pair curr=pq.remove();
            if(!vis[curr.v]){
                vis[curr.v]=true;
                finalcost+=curr.cost;
                for(int i=0;i<graph[curr.v].size();i++){
                    edge e=graph[curr.v].get(i);
                    pq.add(new pair(e.dest, e.wt));
                }
            }
        }
        System.out.println("final(min) cost of MST = "+finalcost);
    }

    public static void main(String[] args) {
        int V=5;
        ArrayList<edge> graph[]=new ArrayList[V];
        creategraph(graph);
        prims(graph);
    }
}
