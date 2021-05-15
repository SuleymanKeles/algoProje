/*
*                                         MARMARA UNIVERSITY
*                                       FACULTY OF ENGINEERING
*                                    COMPUTER ENGINEERING DEPARTMENT
*                                CSE 2046 Analysis of Algorithms , 2021 (Spring)
*                                             Homework 1
*
*        NAME                                                                         STUDENT NUMBER
*        Süleyman KELEŞ                                                               150118039
*        Emine ÇIĞ                                                                    150118012
*
*        Submitted to                                                                 DUE DATE
*        Ömer KORÇAK                                                                   16.05.2021
*
*
*        SUBJECT
*           This Project Compare Sorting Algorithm by time complexity with different input array type.
*
*        Algorithms :
*           1. Insertion-sort,
*           2. Binary Insertion-sort1,
*           3. Merge-sort,
*           4. Quick-sort (pivot is always selected as the first element),
*           5. Quick-sort with median-of-three pivot selection2.
*           6. Heap-sort,
*           7. Counting-sort3
*
*        Input Array Types :
*           1. randomArray
*           2. repetitiveRandomArray
*           3. minArray
*           4. maxArray
*           5. unique
*           6. flash ChangeArray
*           7. firstMinArray
*           8. firstMaxArray
*           9. halfIncreaseHalfDecrease
*           10. halfOddHalfEvenMix
*
*         Project have 3 loop
*         First loop run  12 times. Input array Size {250, 500, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000}
*         Second loop run  10 times. Input array type (shown above)
*         Last loop run 500 times. Repeat Number for calculate average value.
*
*         After all cycles, the data kept in memory are written to the excel data page.
*
*        This Project use Apache Poi Libraries for excel data sheet.
*        If you want to run the project, you must load the library in the link and set the path variable in line 69.
*        https://www.apache.org/dyn/closer.lua/poi/release/bin/poi-bin-5.0.0-20210120.zip
*/




import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;



public class main {

    public static void Main() {
        String path = "C:\\Users\\suleyman\\Documents\\GitHub\\algoProje\\src\\selam.xlsx";
        // These variables are for calculating the times of Algorithms.
        long startTime;
        long endTime;
        long estimatedTimeInsertion;
        long estimatedTimeBinaryInsertion;
        long estimatedTimeMerge;
        long estimatedTimeQuickFirst;
        long estimatedTimeHeap;
        long estimatedTimeCounting;
        long estimatedTimeQuickMedian;

        // Input Array Types
        String[] inputArrayTypeList = {"randomArray", "repetitiveRandomArray", "minArray", "maxArray", "unique", "flash ChangeArray", "firstMinArray", "firstMaxArray", "halfIncreaseHalfDecrease", "halfOddHalfEvenMix"};

        Workbook workbook;

        workbook = new XSSFWorkbook();
        // Excel Sayfasında Satır oluşturma
        Sheet sheetAverage = workbook.createSheet("Average");
        for (int i = 0; i < 50; i++) {
            Row row = sheetAverage.createRow(i);
        }
        // array sizes to be used in the experiment
        int[] arraySizeArray = {250, 500, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        // The number of repetitions made for the accuracy of the experiment
        int repeatNumber = 500;
        for (int inputArraySizeCounter = 1; inputArraySizeCounter <= 12; inputArraySizeCounter++) {
            Row row = sheetAverage.getRow(0);
            row.createCell(0).setCellValue("TITLE");
            // Writing the table headings
            for (int i = 1; i <= inputArrayTypeList.length; i++) {
                // each column 12 characters wide
                row = sheetAverage.getRow(i);
                sheetAverage.setColumnWidth(i, 12 * 256);
                Cell cell = row.createCell(0);
                cell.setCellValue(inputArrayTypeList[i - 1]);
            }
            final String[] headerRow = {"Insertion", "BinaryInsertion ", "Merge", "QuickFirst", "QuickMedian", "Heap", "Counting"};

            row = sheetAverage.getRow(0);
            for (int i = 0; i < headerRow.length; i++) {
                // each column 12 characters wide
                sheetAverage.setColumnWidth(i, 12 * 256);
                Cell cell = row.createCell(i + 1 + ((inputArraySizeCounter - 1) * 8));
                cell.setCellValue(headerRow[i]);
            }

            // Arrays where the averages of the remaining time are kept
            ArrayList<String> averageInsertionList = new ArrayList<>();
            ArrayList<String> averageBinaryInsertionList = new ArrayList<>();
            ArrayList<String> averageMergeList = new ArrayList<>();
            ArrayList<String> averageQuickList = new ArrayList<>();
            ArrayList<String> averageQuickMedianList = new ArrayList<>();
            ArrayList<String> averageHeapList = new ArrayList<>();
            ArrayList<String> averageCountingList = new ArrayList<>();

            InputArrays inputs = new InputArrays();
            inputs.arraySize = arraySizeArray[inputArraySizeCounter - 1];
            int size = inputs.arraySize;
            // Preparing Arrays Created for Each Algorithm
            int[] arr1 = new int[inputs.arraySize];
            int[] arr2 = new int[inputs.arraySize];
            int[] arr3 = new int[inputs.arraySize];
            int[] arr4 = new int[inputs.arraySize];
            int[] arr5 = new int[inputs.arraySize];
            int[] arr6 = new int[inputs.arraySize];
            int[] arr7 = new int[inputs.arraySize];

            System.out.println("Array Size " + inputs.arraySize);
            // printing by array types
            for (int inputArrayType = 0; inputArrayType < inputArrayTypeList.length; inputArrayType++) {

                row = sheetAverage.getRow(inputArrayType + 1);
                // variables that keep the average time
                long averageTimeInsertion = 0;
                long averageTimeBinaryInsertion = 0;
                long averageTimeMerge = 0;
                long averageTimeQuickFirst = 0;
                long averageTimeHeap = 0;
                long averageTimeCounting = 0;
                long averageTimeQuickMedian = 0;
                System.out.println(inputArrayTypeList[inputArrayType] + " Started...");
                int[] tempArray = new int[size];

                switch (inputArrayType) {
                    case 0 -> tempArray = inputs.randomUniqueArray();
                    case 1 -> tempArray = inputs.repetitiveRandomArray();
                    case 2 -> tempArray = inputs.minArray();
                    case 3 -> tempArray = inputs.maxArray();
                    case 4 -> tempArray = inputs.notDistinctArray();
                    case 5 -> tempArray = inputs.flashChangeArray();
                    case 6 -> tempArray = inputs.firstMinArray();
                    case 7 -> tempArray = inputs.firstMaxArray();
                    case 8 -> tempArray = inputs.halfIncreaseDecrease();
                    case 9 -> tempArray = inputs.halfOddHalfEvenMix();
                    default -> System.exit(0);
                }

                int x = 0;
                while (x < repeatNumber) {
                    System.arraycopy(tempArray, 0, arr1, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr2, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr3, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr4, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr5, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr6, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr7, 0, tempArray.length);

//                       for each sorting algorithm temporary arrays which will hold sorted array

//                       InsertionSort
                    startTime = System.nanoTime();
                    Insertion.insertionSort(arr1);
                    endTime = System.nanoTime();
                    estimatedTimeInsertion = endTime - startTime;

//                      BinaryInsertionSort
                    BinaryInsertionSort binaryInsertionSort = new BinaryInsertionSort();
                    startTime = System.nanoTime();
                    binaryInsertionSort.sort(arr2);
                    endTime = System.nanoTime();
                    estimatedTimeBinaryInsertion = endTime - startTime;

                    //                       MERGE SORT
                    MergeSort mergeSortObject = new MergeSort();
                    startTime = System.nanoTime();
                    mergeSortObject.mergeSort(arr3);
                    endTime = System.nanoTime();
                    estimatedTimeMerge = endTime - startTime;

//                         Quick-sort (pivot is always selected as the first element),
                    QuickSortFirstIndex quickSortFirstIndex = new QuickSortFirstIndex();
                    startTime = System.nanoTime();
                    QuickSortFirstIndex.sort(arr4);
                    endTime = System.nanoTime();
                    estimatedTimeQuickFirst = endTime - startTime;

//                          Quick-sort MEDIAN
                    QuickSortMedian quickSortMedian = new QuickSortMedian();
                    startTime = System.nanoTime();
                    quickSortMedian.sort(arr5);
                    endTime = System.nanoTime();
                    estimatedTimeQuickMedian = endTime - startTime;

//                    HEAP SORT
                    HeapSort heapSort = new HeapSort();
                    startTime = System.nanoTime();
                    heapSort.sort(arr6);
                    endTime = System.nanoTime();
                    estimatedTimeHeap = endTime - startTime;

//                                        COUNTING SORT
                    CountingSort countingSort = new CountingSort();
                    startTime = System.nanoTime();
                    CountingSort.sort(arr7);
                    endTime = System.nanoTime();
                    estimatedTimeCounting = endTime - startTime;

                    // ortalama zaman hesaplama
                    averageTimeInsertion += estimatedTimeInsertion;
                    averageTimeBinaryInsertion += estimatedTimeBinaryInsertion;
                    averageTimeMerge += estimatedTimeMerge;
                    averageTimeQuickFirst += estimatedTimeQuickFirst;
                    averageTimeQuickMedian += estimatedTimeQuickMedian;
                    averageTimeHeap += estimatedTimeHeap;
                    averageTimeCounting += estimatedTimeCounting;

                    x++;
                }
                // calculate average time
                averageTimeInsertion /= repeatNumber;
                averageTimeBinaryInsertion /= repeatNumber;
                averageTimeMerge /= repeatNumber;
                averageTimeQuickFirst /= repeatNumber;
                averageTimeQuickMedian /= repeatNumber;
                averageTimeHeap /= repeatNumber;
                averageTimeCounting /= repeatNumber;

                averageInsertionList.add(String.valueOf((averageTimeInsertion)));
                averageBinaryInsertionList.add(String.valueOf((averageTimeBinaryInsertion)));
                averageMergeList.add(String.valueOf((averageTimeMerge)));
                averageQuickList.add(String.valueOf((averageTimeQuickFirst)));
                averageQuickMedianList.add(String.valueOf((averageTimeQuickMedian)));
                averageHeapList.add(String.valueOf((averageTimeHeap)));
                averageCountingList.add(String.valueOf((averageTimeCounting)));

                // Writing the times in the table
                row.createCell(1 + ((inputArraySizeCounter - 1) * 8)).setCellValue(averageTimeInsertion);
                row.createCell(2 + ((inputArraySizeCounter - 1) * 8)).setCellValue(averageTimeBinaryInsertion);
                row.createCell(3 + ((inputArraySizeCounter - 1) * 8)).setCellValue(averageTimeMerge);
                row.createCell(4 + ((inputArraySizeCounter - 1) * 8)).setCellValue(averageTimeQuickFirst);
                row.createCell(5 + ((inputArraySizeCounter - 1) * 8)).setCellValue(averageTimeQuickMedian);
                row.createCell(6 + ((inputArraySizeCounter - 1) * 8)).setCellValue(averageTimeHeap);
                row.createCell(7 + ((inputArraySizeCounter - 1) * 8)).setCellValue(averageTimeCounting);

                System.out.println(inputArrayTypeList[inputArrayType] + " Finished.");
                System.out.println();
            }


            System.out.println();

//            System.out.println(averageInsertionList.toString());
//            System.out.println(averageBinaryInsertionList.toString());
//            System.out.println(averageMergeList.toString());
//            System.out.println(averageQuickList.toString());
//            System.out.println(averageQuickMedianList.toString());
//            System.out.println(averageHeapList.toString());
//            System.out.println(averageCountingList.toString());

        }


        try {

            // Writing sheet data
            File file = new File(path);   //creating a new file instance
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);


        } catch (EncryptedDocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null)
                    workbook.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("*******END*******");

    }
}