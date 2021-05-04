public class MergeSort {
    public  long time = 0;

    public void mergeSort(int[] intArray) {
        sort(intArray, 0, intArray.length - 1);
    }

    void merge(int arr[], int beg, int mid, int end) {

        int l = mid - beg + 1;
        int r = end - mid;

        int LeftArray[] = new int[l];
        int RightArray[] = new int[r];

        for (int i = 0; i < l; ++i) {
            LeftArray[i] = arr[beg + i];
            time++;
        }

        for (int j = 0; j < r; ++j) {
            RightArray[j] = arr[mid + 1 + j];
            time++;

        }


        int i = 0, j = 0;
        int k = beg;
        while (i < l && j < r) {
            time++;
            if (LeftArray[i] <= RightArray[j]) {
                arr[k] = LeftArray[i];
                i++;
            } else {
                arr[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i < l) {
            time++;
            arr[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j < r) {
            time++;
            arr[k] = RightArray[j];
            j++;
            k++;
        }
    }

    void sort(int arr[], int beg, int end) {
        if (beg < end) {
            time++;
            int mid = (beg + end) / 2;
            sort(arr, beg, mid);
            sort(arr, mid + 1, end);
            merge(arr, beg, mid, end);
        }
    }
}