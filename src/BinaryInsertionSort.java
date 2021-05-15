public class BinaryInsertionSort {

    public int binary_search(int array[], int item, int low, int high)
    {
        if (high <= low)
            return (item > array[low])?  (low + 1): low;
        int mid = (low + high)/2;
        if(item == array[mid])
            return mid+1;
        if(item > array[mid])
            return binary_search(array, item, mid+1, high);
        return binary_search(array, item, low, mid-1);
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
