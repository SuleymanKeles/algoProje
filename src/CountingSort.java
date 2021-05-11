public class CountingSort {

    public static  void sort(int[] elements) {
        int maxValue = findMax(elements);
        int[] counts = new int[maxValue + 1];

        // Phase 1: Count
        for (int i = 0; i < elements.length; i++) {
            counts[elements[i]]++;
        }

        // Phase 2: Write results back
        int targetPos = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                elements[targetPos++] = i;
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


//    public static void sort(int [] array ){
//
//        int size =array.length;
//
//        // Find the max element of the given array
//        int max =0;
//        int min =array[0];
//
//        for(int i=0;i<size;i++){
//            if(max<array[i]) max=array[i];
//            if(min>array[i]) min=array[i];
//        }
//
//       int range =max-min+1;
//
//        int temp[]=new int[size];
//        int freqArray[]=new int [range];
//
//        for(int i=0;i<range;i++){
//            freqArray[i]=0;
//        }
//
//        // calculate the reputation of elements
//        for(int i=0;i<size;i++){
//            ++freqArray[array[i]-min];
//        }
//
//        for(int i=1;i<range;i++){
//            freqArray[i]+=freqArray[i-1];
//        }
//        for(int i=size-1;i>=0;i--){
//
//            temp[freqArray[array[i]-min]-1]=array[i];
//            --freqArray[array[i]-min];
//        }
//        for(int i=0;i<size;i++){
//           array[i]=temp[i];
//
//        }
//
//    }
}
