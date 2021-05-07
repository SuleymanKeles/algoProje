import java.util.ArrayList;
import java.util.Random;

public class InputArrays {
    public  int arraySize = 10;
    public  int numberbound = 200;

/*
    public InputArrays(int size) {
        this.arraySize=size;
    }*/

    public  int[] randomArray() {
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

//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        System.out.println();
        return array;
    }


    public  int[] repetitiveRandomArray() {
        int[] array = new int[arraySize];

        Random rand2 = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = rand2.nextInt(numberbound) + 1;
        }

//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        System.out.println();
        return array;
    }


    public  int[] minArray() {

        int[] array = new int[arraySize];

        for (int i = 1; i <= array.length; i++) {
            array[i - 1] = i;
        }


        return array;
    }

    public  int[] maxArray() {
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

    public  int[] divisionArray(int division) {
        int[] array = new int[arraySize];

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < division; i++) {
            for (int j = numberbound - i; j >= 1; j = j - division) {
                arrayList.add(j);
            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }

        return array;
    }

    public  int[] notDistinctArray() {
        int[] array = new int[arraySize];

        Random rand = new Random();
        int element = rand.nextInt(numberbound + 1);
        for (int i = 0; i < array.length; i++) {
            array[i] = element;
        }

        return array;
    }

    public  int[] flashChangeArray() {
        int[] array = randomNotSequencedArray();

        int max = array[0];
        int min = 0;
        int mean = 0;
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
            mean = mean + array[i];
        }
        mean = (mean / array.length);

        if ((max - mean) > (mean - min)) {
            array[maxIndex] = numberbound;
        } else
            array[minIndex] = 0;

        return array;
    }

    public  int[] randomNotSequencedArray() {
        int[] array = new int[arraySize];

        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomValue = rand.nextInt(numberbound + 1);
            array[i] = randomValue;
        }

        return array;
    }

    //First Element Max for pivot selection
    public  int[] firstMinArray() {
        int[] array = randomNotSequencedArray();
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

    public  int[] firstMaxArray() {
        int[] array = randomNotSequencedArray();
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

    public  int[] firstMeanArray() {
        int[] array = randomNotSequencedArray();
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
}
