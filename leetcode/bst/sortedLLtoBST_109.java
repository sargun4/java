class Solution {
    public TreeNode helper(int[]nums,int lo,int hi){
        if(lo>hi) return null;
        int mid=(lo+hi)/2;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=helper(nums,lo,mid-1);
        root.right=helper(nums,mid+1,hi);
        return root;
    }
    public TreeNode sortedListToBST(ListNode head) {
        // List<Integer> arr=new ArrayList<>();
        //calc len of LL
        ListNode temp=head;
        int n=0;
        while(temp!=null){
            temp=temp.next;
            n++;
        }
        int[] nums=new int[n];
        int i=0;
        temp=head;
        while(temp!=null){
            nums[i++]=temp.val;
            temp=temp.next;
        }
        return helper(nums,0,n-1);
    }
}
