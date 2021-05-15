public class MergeSort {
    public  long time = 0;

    public void mergeSort(int[] intputArray) {
        sort(intputArray, 0, intputArray.length - 1);
    }

    void merge(int array[], int beg, int mid, int end) {

        int l = mid - beg + 1;
        int r = end - mid;

        int LeftArray[] = new int[l];
        int RightArray[] = new int[r];

        for (int i = 0; i < l; ++i) {
            LeftArray[i] = array[beg + i];
            time++;
        }

        for (int j = 0; j < r; ++j) {
            RightArray[j] = array[mid + 1 + j];
            time++;

        }


        int i = 0, j = 0;
        int k = beg;
        while (i < l && j < r) {
            time++;
            if (LeftArray[i] <= RightArray[j]) {
                array[k] = LeftArray[i];
                i++;
            } else {
                array[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i < l) {
            time++;
            array[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j < r) {
            time++;
            array[k] = RightArray[j];
            j++;
            k++;
        }
    }

    void sort(int array[], int beg, int end) {
        if (beg < end) {
            time++;
            int mid = (beg + end) / 2;
            sort(array, beg, mid);
            sort(array, mid + 1, end);
            merge(array, beg, mid, end);
        }
    }
}