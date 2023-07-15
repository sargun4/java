package DIVnCONQ;

public class QUICKSORT {
    public static void print(int arr[]){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }System.out.println();
    }
    public static void quicksort(int arr[],int start,int end){
        if(start>=end){
            return;
        }
        //last elmnt
        int pivot=partition(arr,start,end);
        quicksort(arr, start, pivot-1);
        quicksort(arr, pivot+1, end);
    }
    public static int partition(int arr[],int start,int end){
        int pivot=arr[end];
        int i=start-1;
        for(int j=start;j<end;j++){
            if(arr[j]<=pivot){
                i++;
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
            }
        }
        i++;
        int temp=pivot;
        arr[end]=arr[i];
        arr[i]=temp;
        return i;
    }

    public static void main(String[] args) {
        int arr[]={6,3,9,5,2,8};
        quicksort(arr, 0, arr.length-1);
        print(arr);
    }
}
