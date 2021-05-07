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

        String[] sheetName = {"randomArray", "repetitiveRandomArray", "minArray", "maxArray", "divisionArray2", "divisionArray4", "divisionArray8", "divisionArray16", "divisionArray32"};

        Workbook workbook = null;

        workbook = new XSSFWorkbook();

        Sheet sheetAverage = workbook.createSheet("Average");
        int arraySize = 10;
        int repeatNumber = 10;
        for (int inputArraySizeCounter = 1; inputArraySizeCounter <= 10; inputArraySizeCounter++) {

            ArrayList<Double> avarageInsertionList = new ArrayList<>();
            ArrayList<Double> avarageMergeList = new ArrayList<>();
            ArrayList<Double> avarageQuickList = new ArrayList<>();
            ArrayList<Double> avarageQuickMedianList = new ArrayList<>();

            InputArrays inputs = new InputArrays();
            inputs.arraySize = inputArraySizeCounter * arraySize;

            System.out.println("SSSS " + inputs.arraySize);

            for (int inputArrayType = 0; inputArrayType < sheetName.length; inputArrayType++) {

                double avarageTimeInsertion = 0;
                double avarageTimeBinaryInsertion = 0;
                double avarageTimeMerge = 0;
                double avarageTimeQuickFirst = 0;
                double avarageTimeHeap = 0;
                double avarageTimeCounting = 0;
                double avarageTimeQuickMedian = 0;
                System.out.println(sheetName[inputArrayType] + "Finished...");
                int[] tempArray = new int[10000];


                switch (inputArrayType) {
                    case 0 -> tempArray = inputs.randomArray();
                    case 1 -> tempArray = inputs.repetitiveRandomArray();
                    case 2 -> tempArray = inputs.minArray();
                    case 3 -> tempArray = inputs.minArray();
                    case 4 -> tempArray = inputs.divisionArray(2);
                    case 5 -> tempArray = inputs.divisionArray(4);
                    case 6 -> tempArray = inputs.divisionArray(8);
                    case 7 -> tempArray = inputs.divisionArray(16);
                    case 8 -> tempArray = inputs.divisionArray(32);
                    default -> System.exit(0);
                }
                int x = 0;
                while (x < repeatNumber) {
//                       InsertionSort
                    int[] arr1 = tempArray;
                    int[] arr2 = tempArray;
                    int[] arr3 = tempArray;
                    int[] arr4 = tempArray;
                    int[] arr5 = tempArray;
                    int[] arr6 = tempArray;
                    int[] arr7 = tempArray;


//                       InsertionSort
                    Insertion insertion = new Insertion();
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
                    quickSortFirstIndex.quickSort(arr4, 0, arr4.length - 1);
                    endTime = System.nanoTime();
                    estimatedTimeQuickFirst = endTime - startTime;
//                          Quick-sort MEDIAN
                    QuickSortMedian quickSortMedian = new QuickSortMedian();
                    startTime = System.nanoTime();
                    quickSortMedian.quickSort(arr5);
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
//            writeExcel(workbook, sheetName, repeatNumber, inputArrayType,
//                    estimatedTimeInsertionList,
//                    estimatedTimeMergeList,
//                    estimatedTimeQuickList,
//                    estimatedTimeQuickMedianList
//            );

                avarageInsertionList.add((avarageTimeInsertion));
                avarageMergeList.add((avarageTimeMerge));
                avarageQuickList.add((avarageTimeQuickFirst));
                avarageQuickMedianList.add((avarageTimeQuickMedian));

            }
            System.out.println(avarageInsertionList.toString());
            System.out.println(avarageMergeList.toString());
            System.out.println(avarageQuickList.toString());
            System.out.println(avarageQuickMedianList.toString());
            writeExcelAvarage(workbook, sheetAverage, sheetName, inputArraySizeCounter,
                    avarageInsertionList,
                    avarageMergeList,
                    avarageQuickList,
                    avarageQuickMedianList
            );
        }


        try {

            // Writing sheet data
            File file = new File("C:\\Users\\suleyman\\Documents\\GitHub\\algoProje\\src\\selam.xlsx");   //creating a new file instance
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
        if (time > 1000000) {
            newValue = newValue / (10 ^ (digit - 5));
        }
        if (newValue > 1000000)
            newValue = anomalyTime(newValue);
        return newValue;
    }


    public static double listAverage(ArrayList<Long> arrayList) {
        long total = 0;
        double avg;
        for (Long aLong : arrayList) total += aLong;
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

        int initalRowindex = arraySizeCounter *  5;

        Row row = sheet.createRow(initalRowindex);
        row.createCell(0).setCellValue("TITLE");

        for (int i = 0; i < headerRow.length; i++) {
            // each column 12 characters wide
            sheet.setColumnWidth(i, 12 * 256);
            Cell cell = row.createCell(i + 1 + initalRowindex);

            cell.setCellValue(headerRow[i]);
        }

        for (int i = 0; i < headerColumn.length; i++) {
            // each column 12 characters wide
            row = sheet.createRow(i + 1 );

            sheet.setColumnWidth(0, 12 * 256);

            Cell cell = row.createCell( initalRowindex);
            cell.setCellValue(headerColumn[i]);
        }


        for (int i = 0; i < headerColumn.length; i += 5) {
            row = sheet.getRow(i + 1 +initalRowindex);
            row.createCell(1).setCellValue(avarageInsertionList.get(i));
            row.createCell(2).setCellValue(avarageMergeList.get(i));
            row.createCell(3).setCellValue(avarageQuickList.get(i));
            row.createCell(4).setCellValue(avarageQuickMedianList.get(i));
        }

    }
}