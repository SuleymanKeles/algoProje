public class BinaryInsertionSort {

    public static void sort(int [] array){
        for(int i=1;i< array.length;i++){
            int keyword=array[i];
            int position=getPosition(array,0,i-1,keyword);

            for(int j=i-1;j>=position;--j){
                array[j+1]=array[j];
            }
            array[position]=keyword;
        }
    }
    public static int  getPosition(int [] array,int start,int finish,int keyword){
        while(start<=finish){
            int mid=start+(finish-start)/2;
            if(keyword<array[mid]){
                finish=mid-1;
            }else{
                start=mid+1;
            }
        }
        return start;
    }

}
