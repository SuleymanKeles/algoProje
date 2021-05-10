public class BinaryInsertionSort {

//    public static void sort(int [] array){
//        for(int i=1;i< array.length;i++){
//            int keyword=array[i];
//            int position=getPosition(array,0,i-1,keyword);
//
//            for(int j=i-1;j>=position;--j){
//                array[j+1]=array[j];
//            }
//            array[position]=keyword;
//        }
//    }
//    public static int  getPosition(int [] array,int start,int finish,int keyword){
//        while(start<=finish){
//            int mid=start+(finish-start)/2;
//            if(keyword<array[mid]){
//                finish=mid-1;
//            }else{
//                start=mid+1;
//            }
//        }
//        return start;
//    }

    public int binary_search(int a[], int item, int low, int high)
    {
        if (high <= low)
            return (item > a[low])?  (low + 1): low;
        int mid = (low + high)/2;
        if(item == a[mid])
            return mid+1;
        if(item > a[mid])
            return binary_search(a, item, mid+1, high);
        return binary_search(a, item, low, mid-1);
    }
    public void sort(int a[])
    {
        int i, location = 0, j, k, selected;
        int n = a.length;
        for (i = 1; i < n; ++i)
        {
            j = i - 1;
            selected = a[i];
            location = binary_search(a, selected, 0, j);
            while (j >= location)
            {
                a[j+1] = a[j];
                --j;
            }
            a[j+1] = selected;
        }
    }

}
