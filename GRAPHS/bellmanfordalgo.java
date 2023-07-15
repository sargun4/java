package GRAPHS;

import java.util.ArrayList;

public class bellmanfordalgo {
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

    public static void bellmanford(ArrayList<edge> graph[],int src){//O(V*E)
        int dist[]=new int[graph.length];
        for(int i=0;i<dist.length;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        int V=graph.length;
        for(int i=0;i<V-1;i++){
            for(int j=0;j<graph.length;j++){
                for(int k=0;k<graph[j].size();k++){
                    edge e=graph[j].get(k);
                    //u,v ,wt
                    int u=e.src;
                    int v=e.dest;
                    int wt=e.wt;
                    if(dist[u]+wt<dist[v] && dist[u]!=Integer.MAX_VALUE){
                        dist[v]=dist[u]+wt;
                    }
                }
            }
        }
        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }System.out.println();
    }
    public static void main(String[] args) {
        int V=5;
        ArrayList<edge> graph[]=new ArrayList[V];
        creategraph(graph);
        bellmanford(graph, 0);
    }
}
