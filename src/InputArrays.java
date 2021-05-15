import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// 11 Input Type Array Generation

public class InputArrays {

    public int arraySize = 0;
    public int numberbound = 10000;


    // Generates random value arrays in range  whether checking repetitive elements are exist or not
    // {1,2,3,4,5,6,7,8.....1000} and shuffle
    public int[] randomUniqueArray() {
        int[] array = new int[arraySize];
        for (int i = 1; i <= array.length; i++) {
            array[i - 1] = i;
        }
        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        return array;
    }

    //Generates repetitive values arrays in range
    // {1,1,1,4,5,5,5,5.....1000}
    public int[] repetitiveRandomArray() {
        int[] array = randomNotUniqueArray();
        int[] indexArray = new int[arraySize];

        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            indexArray[i] = random.nextInt(arraySize / 2) + 1;
        }

        int randomValue1 = random.nextInt(numberbound) + 1;
        int randomValue2 = random.nextInt(numberbound) + 1;

        for (int i = 0; i < indexArray.length; ) {
            array[indexArray[i]] = randomValue1;
            i = i + 10;
        }

        for (int i = 0; i < indexArray.length; ) {
            array[indexArray[i]] = randomValue2;
            i = i + 11;
        }

        return array;
    }

    //Generates array in ascending order .
    // {1,2,3,4,5,6,7,8.....1000}
    public int[] minArray() {

        int[] array = new int[arraySize];

        for (int i = 1; i <= array.length; i++) {
            array[i - 1] = i;
        }


        return array;
    }
    // Generates Increase after Decrease array
    // {1,2,3,4,5,.....500,501,500,499,498,497,496....}
    public int[] halfIncreaseDecrease() {
        int array[] = maxArray();

        int index = 0;
        for (int i = (array.length) - 1; i >= (arraySize / 2) + 1; i--) {
            array[i] = array[index];
            index++;
        }

        return array;

    }

    //Generates array in descending order .
    // {1000,999,998,997,996....}
    public int[] maxArray() {
        int[] array = new int[arraySize];

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = numberbound; i >= 1; i--) {
            arrayList.add(i);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }

    //Generates array which has elements all are same
    //     // {99,999,99,99,99....}
    public int[] notDistinctArray() {
        int[] array = new int[arraySize];

        Random rand = new Random();
        int element = rand.nextInt(numberbound) + 1;
        for (int i = 0; i < array.length; i++) {
            array[i] = element;
        }

        return array;
    }

    //Array has one outlying min or max element depending on the average value  difference from min and max values
    //     // {1,999....}
    public int[] flashChangeArray() {
        int[] array = randomNotUniqueArray();

        int max = array[0];
        int min = 0;
        int maxIndex = 0;
        int minIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
            if (min > array[i]) {
                min = array[i];
                minIndex = i;
            }
        }
        array[maxIndex] = numberbound - 1;
        array[minIndex] = 0;
        return array;
    }

    // Array created in a given range that is not sequential.
    //     // {1,999....}
    public int[] randomNotUniqueArray() {
        int[] array = new int[arraySize];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomValue = rand.nextInt(numberbound) + 1;
            array[i] = randomValue;
        }
        return array;
    }

    //Generates an array which has min element at first index for pivot selection
    // {1,56,78,45...}
    public int[] firstMinArray() {
        int[] array = randomNotUniqueArray();
        Random random = new Random();

        int min = array[0];
        int minIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
                minIndex = i;
            }

        }
        array[0] = array[minIndex];
        array[minIndex] = random.nextInt(numberbound) + min; // element smaller than max value

        return array;
    }

    //Generates an array which has max element at first index for pivot selection
    // {10000,56,78,45...}
    public int[] firstMaxArray() {
        int[] array = randomNotUniqueArray();
        Random random = new Random();
        int max = array[0];
        int maxIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }

        }
        array[0] = array[maxIndex];
        array[maxIndex] = random.nextInt(max); // element smaller than max value


        return array;
    }

    //Generates an array which has mean value element at first index for pivot selection

    public int[] firstMeanArray() {
        int[] array = randomNotUniqueArray();
        int mean = 0;
        int firstElement;


        for (int i = 0; i < array.length; i++) {
            mean = mean + array[i];
        }
        mean = (mean / array.length);

        firstElement = array[0];
        array[0] = mean;

        return array;
    }

    // Merge Case Worst Case Array Input Generator functions ---> halfOddHalfEvenMix

    public int[] halfOddHalfEvenMix() {
        int array[] = minArray();
        System.out.println(Arrays.toString(array));
        generateArray(array, 0, array.length - 1);
        return array;
    }

    void generateArray(int array[], int leftElement, int rightElement) {

        if (leftElement < rightElement) {
            int middle = ((rightElement - leftElement) / 2) + leftElement;

            int[] right = new int[rightElement - middle];
            int[] left = new int[middle - leftElement + 1];

            divide(array, left, right, leftElement, middle, rightElement);

            generateArray(left, leftElement, middle);
            generateArray(right, middle + 1, rightElement);

            merge(array, left, right, leftElement, middle, rightElement);
        }
    }

    //Updates left and right subArray with serial elements which have odd and even values of ordered array .
    void divide(int array[], int leftSub[], int rightSub[], int left, int middle, int right) {
        for (int i = 0; i <= middle - left; i++)
            leftSub[i] = array[i * 2];

        for (int i = 0; i < right - middle; i++)
            rightSub[i] = array[i * 2 + 1];
    }

    // Merge left and right subArray
    void merge(int array[], int leftSub[], int rightSub[], int left, int middle, int right) {
        int i;
        for (i = 0; i <= middle - left; i++)
            array[i] = leftSub[i];

        for (int j = 0; j < right - middle; j++)
            array[i + j] = rightSub[j];
    }

}
