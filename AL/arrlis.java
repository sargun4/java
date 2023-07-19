package AL;
import java.util.ArrayList;
public class arrlis {
    public static void swap(ArrayList<Integer> list,int idx1,int idx2){
        int temp=list.get(idx1);
        list.set(idx1,list.get(idx2));
        list.set(idx2,temp);
    }
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        int idx1=1,idx2=3;
        int n=5; 
        for (int i = 0; i <n; i++) {
            list.add(i);
        }
        // list.add(2);
        // list.add(3);
        System.out.println(list);
        swap(list,idx1,idx2);
        System.out.println(list);
    }
}
