package GRAPHS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class topologicalsort {
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
        graph[0].add(new edge(0, 3));
        graph[2].add(new edge(2,3));

        graph[3].add(new edge(3,1));

        graph[4].add(new edge(4,0));
        graph[4].add(new edge(4,1));

        graph[5].add(new edge(5,0));
        graph[5].add(new edge(5,2));
    }
    public static void calcindeg(ArrayList<edge> graph[],int indegree[]){
        for(int i=0;i<graph.length;i++){
            int v=i;
            for(int j=0;j<graph[v].size();j++){
                edge e=graph[v].get(j);
                indegree[e.dest]++;
            }
        }
    }
    public static void toposort(ArrayList<edge> graph[]){
        int indegree[]=new int[graph.length];
        calcindeg(graph, indegree);
        Queue<Integer> q=new LinkedList<>();

        for(int i=0;i<indegree.length;i++){
            if(indegree[i]==0){//adding those elmnts in queue whose indegree is 0;
                q.add(i);
            }
        }
        //bfs
        while(!q.isEmpty()){
            int curr=q.remove();
            System.out.print(curr+" ");
            
            for(int i=0;i<graph[curr].size();i++){ //for neighbours
                edge e=graph[curr].get(i);
                indegree[e.dest]--;
                if(indegree[e.dest]==0){
                    q.add(e.dest);
                }
            }
        }System.out.println();
    }

    public static void printAllPaths(ArrayList<edge> graph[],int src,int dest,String path){
        if(src==dest){
            System.out.println(path+dest);
            return;
        }
        for(int i=0;i<graph[src].size();i++){
            edge e=graph[src].get(i);
            printAllPaths(graph, e.dest, dest, path+src);
        }
    }
    public static void main(String[] args) {
        int v=6;
        ArrayList<edge> graph[]=new ArrayList[v];
        creategraph(graph);
        toposort(graph);

        int src=5,dest=1;
        printAllPaths(graph, src, dest, "");

    }
}