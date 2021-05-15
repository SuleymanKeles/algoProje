public class CountingSort {

    public static  void sort(int[] array) {
        int maxValue = findMax(array);
        int[] counts = new int[maxValue + 1];

        // Phase 1: Count
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }

        // Phase 2: Write results back
        int targetPos = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                array[targetPos++] = i;
            }
        }
    }

    public static int findMax(int[] elements) {
        int max = 0;
        for (int i = 0; i < elements.length; i++) {
            int element = elements[i];
            if (element < 0) {
                throw new IllegalArgumentException(
                        "This implementation does not support negative values.");
            }
            if (element > max) {
                max = element;
            }
        }
        return max;
    }

}
