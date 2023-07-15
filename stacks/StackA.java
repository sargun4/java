package stacks;
import java.util.*;

public class StackA {
    public static void pushatbottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        pushatbottom(s, data);
        s.push(top);
    }
    public static String revstr(String str){
        Stack<Character> s=new Stack<>();
        int idx=0;
        while(idx<str.length()){
            s.push(str.charAt(idx));
            idx++;
        }
        StringBuilder res=new StringBuilder("");
        while(!s.isEmpty()){
            char curr=s.pop();
            res.append(curr);
        }
        return res.toString();
    }
    
    public static void revstack(Stack<Integer>s){
        if(s.isEmpty()) return;

        int top=s.pop();
        revstack(s);
        pushatbottom(s, top);
    }
    public static void print(Stack<Integer>s){
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }
    public static void main(String[] args) {
        // String str="abc";
        // String res=revstr(str);
        // System.out.println(res);
        Stack<Integer> s=new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        print(s);
        revstack(s);
        print(s);
    }
}


// import java.util.*;

// public class StackA {
//     public static void pushatbottom(LinkedList<Integer> stack, int data) {
//         if (stack.isEmpty()) {
//             stack.push(data);
//             return;
//         }
//         int top = stack.pop();
//         pushatbottom(stack, data);
//         stack.push(top);
//     }

//     public static void main(String[] args) {
//         LinkedList<Integer> stack = new LinkedList<>();
//         stack.push(1);
//         stack.push(2);
//         stack.push(3);
//         pushatbottom(stack, 4);
//         LinkedList<Integer> tempStack = new LinkedList<>();
//         while (!stack.isEmpty()) {
//             tempStack.push(stack.pop());
//         }
//         while (!tempStack.isEmpty()) {
//             System.out.println(tempStack.pop());
//         }
//     }
// }
