package stacks;

import java.util.Stack;

public class MaxAreainHistogram {
    public static void maxarea(int arr[]){
        int maxarea=0;
        int nsr[]=new int[arr.length];
        int nsl[]=new int[arr.length];
        //next smaller right (nsr)
        Stack<Integer> s=new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            while(!s.isEmpty() && arr[s.peek()] >=arr[i]){
                s.pop();
            }
            if(!s.isEmpty()){
                nsr[i]=arr.length;
            }else{
                nsr[i]=s.peek();
            }
            s.push(i);
        }
        //next smaller left (nsl)
        s=new Stack<>();
        for(int i=0;i<arr.length-1;i++){
            while(!s.isEmpty() && arr[s.peek()] >=arr[i]){
                s.pop();
            }
            if(!s.isEmpty()){
                nsl[i]=-1;
            }else{
                nsl[i]=s.peek();
            }
            s.push(i);
        }
            //current area: width=j-i-1=nsr[x]-nsl[x]-1
            for(int i=0;i<arr.length;i++){
                int ht=arr[i];
                int width=nsr[i]-nsl[i]-1;
                int currarea=ht*width;
                maxarea=Math.max(currarea,maxarea);
            }
            System.out.println("Max area in histogram"+maxarea);
        }
    public static void main(String[] args) {
        int arr[]={2,1,5,6,2,3};//hts in histogram
        maxarea(arr);
    }
}
