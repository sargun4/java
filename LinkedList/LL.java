package LinkedList;
public class LL {
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addfirst(int data){//O(1)
        Node newnode=new Node(data);
        size++;
        if(head==null){
            head=tail=newnode;
            return;
        }
        newnode.next=head;
        head=newnode;
    }
    public void addlast(int data){ //O(1)
        Node newnode=new Node(data);
        size++;
        if(head==null){
            head=tail=newnode;
            return;
        }
        tail.next=newnode;
        tail=newnode;
    }
    public void print(){
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp=temp.next;
        }System.out.println("null");
    }    
    public void add(int idx,int data){
        if(idx==0){
            addfirst(data);
            return;
        }
        Node newnode=new Node(data);
        size++;
        Node temp=head;
        int i=0;
        while(i<idx-1){
            temp=temp.next;
            i++;
        }
        //i=idx-1;temp->prev
        newnode.next=temp.next;
        temp.next=newnode;
    }
    public int delfirst(){
        if(size==0) {
            System.out.println("LL empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val=head.data;
            head=tail=null;
            return val;
        }
        int val=head.data;
        head=head.next;
        return val;
    }
    public int dellast(){
        if(size==0) {
            System.out.println("LL empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        //prev- second last node(before tail)
        Node prev=head;
        for(int i=0;i<size-2;i++){
            prev=prev.next;
        }
        int val=prev.next.data; //tail.data
        prev.next=null;
        tail=prev;
        size--;
        return val;
    }
    public int search_iter(int key){ //O(n)
        Node temp=head;
        int i=0;
        while(temp!=null){
            if(temp.data==key){//key found
                return i;
            }temp=temp.next;
            i++;
        }//key not found
        return -1;
    }
    public int helper(Node head,int key){//O(n)
        if(head==null) return -1;
        if(head.data==key) return 0;
        int idx=helper(head.next,key);
        if(idx==-1) return -1;
        return idx+1;
    }
    public int search_rec(int key){

        return helper(head, key);
    }

    public void rev(){
        Node prev=null;
        Node curr=tail=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head=prev;
    }
    public void delNthfrmend(int n){
        int len=0;
        Node temp=head;
        while(temp!=null){
            temp=temp.next;
            len++;
        }
        if(n==len){//del first-head deletion
            head=head.next;
            return;
        }
        //len-n
        int i=1;
        int iTofind=len-n;
        Node prev=head;
        while(i<iTofind){
            prev=prev.next;
            i++;
        }
        prev.next=prev.next.next;
        return;
    }
    
    public Node findmid(Node head){
        Node slow=head;
        Node fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow; //slow now points to midnode
    }
    public boolean checkPalindrome(){
        if(head==null||head.next==null){
            return true;
        }
        //find mid
        Node midnode=findmid(head);
        //rev 2nd half
        Node prev=null;
        Node curr=midnode;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node right=prev; //right part head
        Node left=head;
        //check left n right data
        while(right!=null){
            if(left.data!=right.data) {return false;}
            left=left.next;
            right=right.next;
        }
        return true;
    }

    public static boolean iscycle(){
        Node slow=head;
        Node fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;//cycle exists
            }
        }
        return false;
    }
   
    public static void removecycle(){
        Node slow=head;
        Node fast=head;
        boolean cycle=false;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                cycle=true;
                break;
            }
        }
        if(cycle==false) return;

        //find meeting pt by setting slow ptr at head,and not changing fast ptr's position
        slow=head;
        Node prev=null;
        while(slow!=fast){
            prev=fast; 
            slow=slow.next;
            fast=fast.next;//imp
        }
        //remove cycle- last.next=null
        prev.next=null;
    }

    private Node getmid(Node head){ //returns left mid node
        Node slow=head;
        Node fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    private Node merge(Node head1,Node head2){
        Node mergedLL=new Node(-1);
        Node temp=mergedLL;
        while(head1!=null && head2!=null){
            if(head1.data<=head2.data){
                temp.next=head1;
                head1=head1.next;
                temp=temp.next;
            }else{
                temp.next=head2;
                head2=head2.next;
                temp=temp.next;
            }
            while(head1!=null){
                temp.next=head1;
                head1=head1.next;
                temp=temp.next;
            }
            while(head2!=null){
                temp.next=head2;
                head2=head2.next;
                temp=temp.next;
            }
        }
        return mergedLL.next;
    }
    public Node mergesort(Node head){
        if(head==null || head.next==null) return head; //already sorted;
        //find mid
        Node mid=getmid(head);
        Node righthead=mid.next;
        mid.next=null;
        Node newleft=mergesort(head);
        Node newright=mergesort(righthead);

        //merge
        return merge(newleft,newright);
    }
  
    public Node rotateRight(Node head, int k) {
        Node temp = head;
        if(head==null || head.next==null || k==0) return head;
        int count = 1;
        while(temp.next!=null){
            temp = temp.next;
            count++;
        }
        temp.next = head;
        k = k%count;
        k = count-k;
        while((k--)>0){
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }

    public void zigzag(){
        //find mid (last node of first half)
        Node slow=head;
        Node fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        Node mid=slow;
        //rev 2nd half
        Node prev=null;
        Node next;
        Node curr=mid.next;
        mid.next=null;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node left=head; //head of 1st half
        Node right=prev; //head of 2nd half
        Node nextL,nextR;

        //alternate/zigzag merging
        while(left!=null && right!=null){
            nextL=left.next;
            left.next=right;
            nextR=right.next;
            right.next=nextL;

            left=nextL;
            right=nextR;
        }
    }
    public static void main(String args[]){
        LL ll=new LL();
        ll.addfirst(1);
        ll.addfirst(2);
        ll.addfirst(3);
        ll.addfirst(4);
        ll.addfirst(5);
        // // System.out.println(ll.size);
        ll.print();
        ll.zigzag();
        ll.print();
        // ll.head=ll.mergesort(ll.head);
        // ll.print();
        // ll.head=ll.rotateRight(ll.head,2);
        // ll.print();
        // ll.delfirst();
        // ll.print();
        // System.out.println(ll.search_iter(3));
        // System.out.println(ll.search_rec(3));
        // // ll.dellast();
        // ll.print();
        // ll.rev();
        // ll.delNthfrmend(2);
        // ll.print();
        // System.out.println(ll.checkPalindrome());

        // head=new Node(1);
        // Node temp=new Node(2);
        // head.next=temp;
        // head.next.next=new Node(3);
        // head.next.next.next=temp;
        // System.out.println(iscycle());
        // removecycle();
        // System.out.println(iscycle());
    }
}

