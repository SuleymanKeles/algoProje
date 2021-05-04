import java.util.ArrayList;
import java.util.Random;

public class InputArrays {
 int arraySize = 100;
int numberbound = 1000;



     int[] randomArray() {
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



    int[] repetitiveRandomArray() {
        int[] array = new int[arraySize];

        Random rand2 = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = rand2.nextInt(numberbound)+1;
        }
        return array;
    }


    int[] minArray() {

        int[] array = new int[arraySize];

        for (int i = 1; i <= array.length; i++) {
            array[i - 1] = i;
        }
        return array;
    }

    int[] maxArray() {
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

    int[] divisionArray(int division) {
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
}
