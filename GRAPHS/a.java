package GRAPHS;

import java.util.ArrayList;
// import java.util.LinkedList;
import java.util.Queue;

public class a {
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
    static void creategraph(ArrayList<edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new edge(0, 1, 1));
        graph[0].add(new edge(0,2,1));

        graph[1].add(new edge(1,0,1));
        graph[1].add(new edge(1,3,1));

        graph[2].add(new edge(2,0,1));
        graph[2].add(new edge(2,4,1));

        graph[3].add(new edge(3,1,1));
        graph[3].add(new edge(3,4,1));
        graph[3].add(new edge(3,5,1));

        graph[4].add(new edge(4,2,1));
        graph[4].add(new edge(4,5,1));
        graph[4].add(new edge(4,3,1));

        graph[5].add(new edge(5,4,1));
        graph[5].add(new edge(5,6,1));
        graph[5].add(new edge(4,3,1));
    }
    public static void bfs(ArrayList<edge>[] graph) {
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                bfsUtil(graph, vis);
            }
        }
    }
    public static void bfsUtil(ArrayList<edge>[] graph,boolean vis[]) {//O(V+E)
        Queue<Integer> q=new LinkedList<>();
        q.add(0); //source=0
        while(!q.isEmpty()){
            int curr=q.remove();
            if(!vis[curr]){// if vis is false for that vertex
                System.out.println(curr+" ");
                vis[curr]=true;
                for(int i=0;i<graph[curr].size();i++){
                    edge e=graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    public static void dfs(ArrayList<edge>[] graph){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            dfsUtil(graph, i, vis);
        }
    }
    public static void dfsUtil(ArrayList<edge>[] graph,int curr,boolean[] vis){
        System.out.print(curr+" ");
        vis[curr]=true;

        for(int i=0;i<graph[curr].size();i++){ //visit all neighbours
            edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                dfsUtil(graph,e.dest,vis);
            }
        }
    }
    //O(V+E)
    public static boolean haspath(ArrayList<edge>[] graph,int src,int dest,boolean vis[]){
        if(src==dest){
            return true;
        }
        vis[src]=true;
        for(int i=0;i<graph[src].size();i++){
            edge e=graph[src].get(i);
            //e.dest=neighbour
            if(!vis[e.dest] && haspath(graph, e.dest, dest, vis)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int v=7;
        ArrayList<edge>[] graph=new ArrayList[v];
        creategraph(graph);
        // bfs(graph);
        dfs(graph,0,new boolean[v]);
        System.out.println(haspath(graph, 0, 5,new boolean[7]));
    }
}
