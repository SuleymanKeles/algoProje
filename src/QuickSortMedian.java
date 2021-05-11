import java.util.Arrays;

public class QuickSortMedian {


    public long sort(int[] array) {
        long start = System.nanoTime();
        sort(array, 0, array.length - 1);
        long elapsedTime = System.nanoTime() - start;
        return elapsedTime;
    }


    public void sort(int[] array, int l, int r) {
        if (l < r) {
            // select pivot element (left-most)

            int first = array[l];
            int last = array[r];
            int middle = array[(r+l)/2];

            int flm[] = {first,middle,last}; // 7, 19, 12
            Arrays.sort(flm); // 7 ,12, 19

            array[l] = flm[0]; // 7 - 7
            array[(r+l)/2] = flm[1]; // 12 - 13
            array[r] = flm[2]; // 19 -19

            swap(array,middle,r-1);
            int pivot = array[r-1];

            // partition and shuffle around pivot
            int i = l;
            int j = r;
            while (i < j) {
                // move right to avoid pivot element
                i += 1;
                // scan right: find elements greater than pivot
                while (i <= r && array[i] < pivot) {
                    i += 1;
                }
                // scan left: find elements smaller than pivot
                while (j >= l && array[j] > pivot) {
                    j -= 1;
                }
                if (i <= r && i < j) {
                    // swap around pivot
                    swap(array, i, j);
                }
            }
            // put pivot in correct place
            swap(array, l, j);
            // sort partitions
            Arrays.sort(array);
            sort(array, j + 1, r);
        }
    }


    public void swap(int[] array, int i, int j) {
        if (i >= 0 && j >= 0 && i < array.length && j < array.length) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }



}