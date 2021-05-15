public class QuickSortFirstIndex {

        public static int partition(int array[], int beg, int end)
    {

        int left, right, temp, loc, flag;
        loc = left = beg;
        right = end;
        flag = 0;
        while(flag != 1)
        {
            while((array[loc] <= array[right]) && (loc!=right))
                right--;
            if(loc==right)
                flag =1;
            else if(array[loc]>array[right])
            {
                temp = array[loc];
                array[loc] = array[right];
                array[right] = temp;
                loc = right;
            }
            if(flag!=1)
            {
                while((array[loc] >= array[left]) && (loc!=left))
                    left++;
                if(loc==left)
                    flag =1;
                else if(array[loc] <array[left])
                {
                    temp = array[loc];
                    array[loc] = array[left];
                    array[left] = temp;
                    loc = left;
                }
            }
        }
        return loc;
    }
    static void quickSort(int array[], int beg, int end)
    {

        int loc;
        if(beg<end)
        {
            loc = partition(array, beg, end);
            quickSort(array, beg, loc-1);
            quickSort(array, loc+1, end);
        }
    }

    static void sort (int arr[]){
        quickSort(arr,0,arr.length-1);
    }

}
