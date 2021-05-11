import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class main {


    static int anomalyTimeCounter = 0;

    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;
        long estimatedTimeInsertion = 0;
        long estimatedTimeBinaryInsertion = 0;
        long estimatedTimeMerge = 0;
        long estimatedTimeQuickFirst = 0;
        long estimatedTimeHeap = 0;
        long estimatedTimeCounting = 0;
        long estimatedTimeQuickMedian = 0;

        String[] sheetName = {"randomArray", "repetitiveRandomArray", "minArray", "maxArray", "notDistinctArray", "flash ChangeArray", "firstMinArray", "firstMaxArray", "Mountain"};

        Workbook workbook = null;

        workbook = new XSSFWorkbook();

        Sheet sheetAverage = workbook.createSheet("Average");
        for ( int i = 0; i < 50; i++ ) {
            Row row = sheetAverage.createRow(i);
        }


//        int arraySize = 1000;
        int[] arraySizeArray = {250, 500, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        int repeatNumber = 50;
        for ( int inputArraySizeCounter = 1; inputArraySizeCounter <= 12; inputArraySizeCounter++ ) {
            Row row = sheetAverage.getRow(0);
            row.createCell(0).setCellValue("TITLE");
            for ( int i = 1; i <= sheetName.length; i++ ) {
                // each column 12 characters wide
                row = sheetAverage.getRow(i);
                sheetAverage.setColumnWidth(i, 12 * 256);
                Cell cell = row.createCell(0);
                cell.setCellValue(sheetName[i - 1]);
            }
            final String[] headerRow = {"Insertion", "BinaryInsertion ", "Merge", "QuickFirst", "QuickMedian", "Heap", "Counting"};

            row = sheetAverage.getRow(0);
            for ( int i = 0; i < headerRow.length; i++ ) {
                // each column 12 characters wide
                sheetAverage.setColumnWidth(i, 12 * 256);
                Cell cell = row.createCell(i + 1 + ((inputArraySizeCounter - 1) * 8));
                cell.setCellValue(headerRow[i]);
            }

            ArrayList<String> avarageInsertionList = new ArrayList<>();
            ArrayList<String> averageBinaryInsertionList = new ArrayList<>();
            ArrayList<String> avarageMergeList = new ArrayList<>();
            ArrayList<String> avarageQuickList = new ArrayList<>();
            ArrayList<String> avarageQuickMedianList = new ArrayList<>();
            ArrayList<String> avarageHeapList = new ArrayList<>();
            ArrayList<String> avarageCountingList = new ArrayList<>();

            InputArrays inputs = new InputArrays();
            inputs.arraySize = arraySizeArray[inputArraySizeCounter - 1];

            int size = inputs.arraySize;
            int[] arr1 = new int[inputs.arraySize];
            int[] arr2 = new int[inputs.arraySize];
            int[] arr3 = new int[inputs.arraySize];
            int[] arr4 = new int[inputs.arraySize];
            int[] arr5 = new int[inputs.arraySize];
            int[] arr6 = new int[inputs.arraySize];
            int[] arr7 = new int[inputs.arraySize];

            System.out.println("SSSS " + inputs.arraySize);

            for ( int inputArrayType = 0; inputArrayType < sheetName.length; inputArrayType++ ) {

                row = sheetAverage.getRow(inputArrayType + 1);

                long avarageTimeInsertion = 0;
                long avarageTimeBinaryInsertion = 0;
                long avarageTimeMerge = 0;
                long avarageTimeQuickFirst = 0;
                long avarageTimeHeap = 0;
                long avarageTimeCounting = 0;
                long avarageTimeQuickMedian = 0;
                System.out.println(sheetName[inputArrayType] + "Finished...");
                int[] tempArray = new int[size];


                /*case 8 : tempArray = inputs.divisionArray(32);
                        break;*/
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
                    default -> System.exit(0);
                }

                int x = 0;
//                System.out.println("___TempArry____ "+inputArrayType +" "+Arrays.toString(tempArray) + "__________");
                while (x < repeatNumber) {
                    System.arraycopy(tempArray, 0, arr1, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr2, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr3, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr4, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr5, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr6, 0, tempArray.length);
                    System.arraycopy(tempArray, 0, arr7, 0, tempArray.length);

//                System.out.println(Arrays.toString(arr1) + "arr1");

//                       // for each sorting algorithm temporary arrays which will hold sorted array

//                       InsertionSort
                    Insertion insertion = new Insertion();
                    startTime = System.nanoTime();
                    Insertion.insertionSort(arr1);
                    endTime = System.nanoTime();
                    estimatedTimeInsertion = endTime - startTime;

//                    System.out.println("*"+Arrays.toString(arr1) );
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
                    quickSortFirstIndex.sort(arr4);
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
                    countingSort.sort(arr7);
                    endTime = System.nanoTime();
                    estimatedTimeCounting = endTime - startTime;

                    avarageTimeInsertion += estimatedTimeInsertion;
                    avarageTimeBinaryInsertion += estimatedTimeBinaryInsertion;
                    avarageTimeMerge += estimatedTimeMerge;
                    avarageTimeQuickFirst += estimatedTimeQuickFirst;
                    avarageTimeQuickMedian += estimatedTimeQuickMedian;
                    avarageTimeHeap += estimatedTimeHeap;
                    avarageTimeCounting += estimatedTimeCounting;

                    x++;
                }

                avarageTimeInsertion /= repeatNumber;
                avarageTimeBinaryInsertion /= repeatNumber;
                avarageTimeMerge /= repeatNumber;
                avarageTimeQuickFirst /= repeatNumber;
                avarageTimeQuickMedian /= repeatNumber;
                avarageTimeHeap /= repeatNumber;
                avarageTimeCounting /= repeatNumber;

                avarageInsertionList.add(String.valueOf((avarageTimeInsertion)));
                averageBinaryInsertionList.add(String.valueOf((avarageTimeBinaryInsertion)));
                avarageMergeList.add(String.valueOf((avarageTimeMerge)));
                avarageQuickList.add(String.valueOf((avarageTimeQuickFirst)));
                avarageQuickMedianList.add(String.valueOf((avarageTimeQuickMedian)));
                avarageHeapList.add(String.valueOf((avarageTimeHeap)));
                avarageCountingList.add(String.valueOf((avarageTimeCounting)));

                row.createCell(1 + ((inputArraySizeCounter - 1) * 8)).setCellValue(avarageTimeInsertion);
                row.createCell(2 + ((inputArraySizeCounter - 1) * 8)).setCellValue(avarageTimeBinaryInsertion);
                row.createCell(3 + ((inputArraySizeCounter - 1) * 8)).setCellValue(avarageTimeMerge);
                row.createCell(4 + ((inputArraySizeCounter - 1) * 8)).setCellValue(avarageTimeQuickFirst);
                row.createCell(5 + ((inputArraySizeCounter - 1) * 8)).setCellValue(avarageTimeQuickMedian);
                row.createCell(6 + ((inputArraySizeCounter - 1) * 8)).setCellValue(avarageTimeHeap);
                row.createCell(7 + ((inputArraySizeCounter - 1) * 8)).setCellValue(avarageTimeCounting);

                avarageTimeInsertion = 0;
                avarageTimeBinaryInsertion = 0;
                avarageTimeMerge = 0;
                avarageTimeQuickFirst = 0;
                avarageTimeQuickMedian = 0;
                avarageTimeHeap = 0;
                avarageTimeCounting = 0;
                System.out.println();
            }


            System.out.println();
            System.out.println(avarageInsertionList.toString());
            System.out.println(averageBinaryInsertionList.toString());
            System.out.println(avarageMergeList.toString());
            System.out.println(avarageQuickList.toString());
            System.out.println(avarageQuickMedianList.toString());
            System.out.println(avarageHeapList.toString());
            System.out.println(avarageCountingList.toString());

        }


        try {

            // Writing sheet data
            File file = new File("C:\\Users\\emine\\OneDrive\\Belgeler\\GitHub\\algoProje\\src\\selam.xlsx");   //creating a new file instance
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
        System.out.println("anomalyTimeCounter ? " + anomalyTimeCounter);

    }

    public static long anomalyTime(long time) {
        long newValue = 0;
        int digit = 0;
        newValue = time;
        while (newValue != 0) {
            // num = num/10
            newValue /= 10;
            ++digit;
        }
        newValue = time;
        if (time > 500000) {
            anomalyTimeCounter++;
            newValue = newValue / (10 ^ (digit - 5));
        }
        if (newValue > 500000)
            newValue = anomalyTime(newValue);
        return newValue;
    }


    public static double listAverage(ArrayList<Long> arrayList) {
        long total = 0;
        double avg;
        for ( Long aLong : arrayList ) total += aLong;
        avg = total / arrayList.size(); // finding ther average value
        return avg;
    }

    //method defined for reading a cell
    public static double ReadCellData(int vRow, int vColumn, File file) {
        double value;          //variable for storing the cell value
        Workbook wb = null;           //initialize Workbook null
        try {
//reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream(file);
//constructs an XSSFWorkbook object, by buffering the whole stream into the memory
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(1);   //getting the XSSFSheet object at given index
        Row row = sheet.getRow(vRow); //returns the logical row
        Cell cell = row.getCell(vColumn); //getting the cell representing the given column
        value = cell.getNumericCellValue();    //getting cell value
        return value;               //returns the cell value
    }

    public static void writeExcelAvarage(Workbook workbook, Sheet sheet,
                                         String[] headerColumn,
                                         int arraySizeCounter,
                                         ArrayList<Double> avarageInsertionList,
                                         ArrayList<Double> avarageMergeList,
                                         ArrayList<Double> avarageQuickList,
                                         ArrayList<Double> avarageQuickMedianList
    ) {

        final String[] headerRow = {"Insertion", "Merge", "QuickFirts", "QuickMedian"};

        int initalRowindex = (arraySizeCounter - 1) * 5;

        Row row = sheet.createRow(initalRowindex);
        row.createCell(0).setCellValue("TITLE");

        for ( int i = 0; i < headerRow.length; i++ ) {
            // each column 12 characters wide
            sheet.setColumnWidth(i, 12 * 256);
            Cell cell = row.createCell(i + 1 + initalRowindex);

            cell.setCellValue(headerRow[i]);
        }

        for ( int i = 0; i < headerColumn.length; i++ ) {
            // each column 12 characters wide
            row = sheet.createRow(i + 1);

            sheet.setColumnWidth(0, 12 * 256);

            Cell cell = row.createCell(initalRowindex);
            cell.setCellValue(headerColumn[i]);
        }


        for ( int i = 0; i < headerColumn.length; i++ ) {
            row = sheet.getRow(i + 1 + initalRowindex);
            row.createCell(1).setCellValue(avarageInsertionList.get(i));
            row.createCell(2).setCellValue(avarageMergeList.get(i));
            row.createCell(3).setCellValue(avarageQuickList.get(i));
            row.createCell(4).setCellValue(avarageQuickMedianList.get(i));
        }

    }
}