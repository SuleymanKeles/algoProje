public class QuickSortFirstIndex {

//    public static int partition(int a[], int beg, int end)
//    {
//
//        int left, right, temp, loc, flag;
//        loc = left = beg;
//        right = end;
//        flag = 0;
//        while(flag != 1)
//        {
//            while((a[loc] <= a[right]) && (loc!=right))
//                right--;
//            if(loc==right)
//                flag =1;
//            else if(a[loc]>a[right])
//            {
//                temp = a[loc];
//                a[loc] = a[right];
//                a[right] = temp;
//                loc = right;
//            }
//            if(flag!=1)
//            {
//                while((a[loc] >= a[left]) && (loc!=left))
//                    left++;
//                if(loc==left)
//                    flag =1;
//                else if(a[loc] <a[left])
//                {
//                    temp = a[loc];
//                    a[loc] = a[left];
//                    a[left] = temp;
//                    loc = left;
//                }
//            }
//        }
//        return loc;
//    }
//    static void quickSort(int a[], int beg, int end)
//    {
//
//        int loc;
//        if(beg<end)
//        {
//            loc = partition(a, beg, end);
//            quickSort(a, beg, loc-1);
//            quickSort(a, loc+1, end);
//        }
    //}

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    static int partition(int[] arr, int low, int high)
    {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot)
            {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /* The main function that implements QuickSort
              arr[] --> Array to be sorted,
              low --> Starting index,
              high --> Ending index
     */
    static void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static void sort (int arr[]){
        quickSort(arr,0,arr.length-1);
    }

}
