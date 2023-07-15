package GRAPHS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class b {
    static class edge{
        int src;
        int dest;
        public edge(int s,int d){
            this.src=s;
            this.dest=d;
        }
    }
    static void creategraph(ArrayList<edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new edge(0, 1));
        graph[0].add(new edge(0,2));

        graph[1].add(new edge(1,0));
        graph[1].add(new edge(1,3));

        graph[2].add(new edge(2,0));
        graph[2].add(new edge(2,4));

        graph[3].add(new edge(3,1));
        graph[3].add(new edge(3,4));
        graph[3].add(new edge(3,5));

        graph[4].add(new edge(4,2));
        graph[4].add(new edge(4,5));
        graph[4].add(new edge(4,3));

        graph[5].add(new edge(5,4));
        graph[5].add(new edge(5,6));
        graph[5].add(new edge(4,3));
    }
    public static boolean detectCycle(ArrayList<edge>[] graph){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                detectCycleUtil(graph,vis,i,-1);
                return true; //cycle exist in one of the parts
            }
        }
        return false;
    }
    public static boolean detectCycleUtil(ArrayList<edge>[] graph,boolean vis[],int curr,int parent){
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            edge e=graph[curr].get(i);
            if(!vis[e.dest] && detectCycleUtil(graph,vis,e.dest,curr)){
                return true;
            }else if(vis[e.dest] && e.dest!=parent){
                return true;
            }
        }
        return false;
    }
    public static boolean isBipartite(ArrayList<edge>[] graph){
        int col[]=new int[graph.length];
        for(int i=0;i<col.length;i++){
            col[i]=-1;//no color
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(col[i])
        }
    }
    public static void main(String[] args) {
        int v=6;
        ArrayList<edge> graph[]=new ArrayList[v];
        creategraph(graph);
        System.out.println(detectCycle(graph));
    }   
}