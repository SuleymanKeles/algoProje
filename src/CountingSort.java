public class CountingSort {


    public static void sort(int [] array ){

        int size =array.length;

        // Find the max element of the given array
        int max =0;
        int min =array[0];

        for(int i=0;i<size;i++){
            if(max<array[i]) max=array[i];
            if(min>array[i]) min=array[i];
        }

       int range =max-min+1;

        int temp[]=new int[size];
        int freqArray[]=new int [range];

        for(int i=0;i<range;i++){
            freqArray[i]=0;
        }

        // calculate the reputation of elements
        for(int i=0;i<size;i++){
            ++freqArray[array[i]-min];
        }

        for(int i=1;i<range;i++){
            freqArray[i]+=freqArray[i-1];
        }
        for(int i=size-1;i>=0;i--){

            temp[freqArray[array[i]-min]-1]=array[i];
            --freqArray[array[i]-min];
        }
        for(int i=0;i<size;i++){
           array[i]=temp[i];

        }

    }
}
