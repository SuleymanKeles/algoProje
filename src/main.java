import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class main {
    static ArrayList<Double> avarageInsertionList = new ArrayList<>();
    static ArrayList<Double> avarageMergeList = new ArrayList<>();
    static ArrayList<Double> avarageQuickList = new ArrayList<>();
    static ArrayList<Double> avarageQuickMedianList = new ArrayList<>();

    public static void main(String[] args) {
        InputArrays inputs = new InputArrays();
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


        int repeatNumber = 20;

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
                case 3 -> tempArray = inputs.maxArray();
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

//            System.out.println(Arrays.toString(arr1));
//            System.out.println(estimatedTimeQuickList.toString());
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

            System.out.println(avarageInsertionList.toString());
            System.out.println(avarageMergeList.toString());
            System.out.println(avarageQuickList.toString());
            System.out.println(avarageQuickMedianList.toString());
//            estimatedTimeInsertionList.clear();
//            estimatedTimeMergeList.clear();
//            estimatedTimeQuickList.clear();
//            estimatedTimeQuickMedianList.clear();
        }

        writeExcelAvarage(workbook, sheetAverage, sheetName,
                avarageInsertionList,
                avarageMergeList,
                avarageQuickList,
                avarageQuickMedianList
        );


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

    public static void writeExcel(Workbook workbook,
                                  String[] sheetName,
                                  int rowNum,
                                  int inputArrayType,
                                  ArrayList<Long> estimatedTimeInsertionList,
                                  ArrayList<Long> estimatedTimeMergeList,
                                  ArrayList<Long> estimatedTimeQuickList,
                                  ArrayList<Long> estimatedTimeQuickMedianList
    ) {

        final String[] header = {"First Name", "Last Name", "Email", "DOB"};


        Sheet sheet = workbook.createSheet(sheetName[inputArrayType]);

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("TITLE");


        for (int i = 0; i < header.length; i++) {
            // each column 12 characters wide
            sheet.setColumnWidth(i, 12 * 256);
            Cell cell = row.createCell(i + 1);
            cell.setCellValue(header[i]);
        }
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("AVARAGE");
        row.createCell(1).setCellFormula("AVERAGE(B3:B1003)");
        row.createCell(2).setCellFormula("AVERAGE(C3:C1003)");
        row.createCell(3).setCellFormula("AVERAGE(D3:D1003)");
        row.createCell(4).setCellFormula("AVERAGE(E3:E1003)");


        for (int i = 0; i < rowNum; i++) {
            row = sheet.createRow(i + 2);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(estimatedTimeInsertionList.get(i));
            row.createCell(2).setCellValue(estimatedTimeMergeList.get(i));
            row.createCell(3).setCellValue(estimatedTimeQuickList.get(i));
            row.createCell(4).setCellValue(estimatedTimeQuickMedianList.get(i));
        }

        // Create Excel chart and select data for it.
        // create new row
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(20, 20, 0, 0, 6, 0, 38, 32);

        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("Area-wise Top Seven Countries");
        chart.setTitleOverlay(false);

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("Country");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("Area & Population");

        XDDFNumericalDataSource<Double> orderNumber = XDDFDataSourcesFactory.fromNumericCellRange((XSSFSheet) sheet,
                new CellRangeAddress(2, rowNum, 0, 0));

        XDDFNumericalDataSource<Double> Insert = XDDFDataSourcesFactory.fromNumericCellRange((XSSFSheet) sheet,
                new CellRangeAddress(2, rowNum, 1, 1));
        XDDFNumericalDataSource<Double> merge = XDDFDataSourcesFactory.fromNumericCellRange((XSSFSheet) sheet,
                new CellRangeAddress(2, rowNum, 2, 2));
        XDDFNumericalDataSource<Double> quickFirst = XDDFDataSourcesFactory.fromNumericCellRange((XSSFSheet) sheet,
                new CellRangeAddress(2, rowNum, 3, 3));
        XDDFNumericalDataSource<Double> QuickMedian = XDDFDataSourcesFactory.fromNumericCellRange((XSSFSheet) sheet,
                new CellRangeAddress(2, rowNum, 4, 4));

        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(orderNumber, Insert);
        series1.setTitle("Insert", null);

        XDDFLineChartData.Series series2 = (XDDFLineChartData.Series) data.addSeries(orderNumber, merge);
        series2.setTitle("merge", null);

        XDDFLineChartData.Series series3 = (XDDFLineChartData.Series) data.addSeries(orderNumber, quickFirst);
        series3.setTitle("quickFirst", null);

        XDDFLineChartData.Series series4 = (XDDFLineChartData.Series) data.addSeries(orderNumber, QuickMedian);
        series4.setTitle("QuickMedian", null);

        chart.plot(data);


    }


    public static void writeExcelAvarage(Workbook workbook, Sheet sheet,
                                         String[] headerColumn,
                                         ArrayList<Double> avarageInsertionList,
                                         ArrayList<Double> avarageMergeList,
                                         ArrayList<Double> avarageQuickList,
                                         ArrayList<Double> avarageQuickMedianList
    ) {

        final String[] headerRow = {"Insertion", "Merge", "QuickFirts", "QuickMedian"};

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("TITLE");

        for (int i = 0; i < headerRow.length; i++) {
            // each column 12 characters wide
            sheet.setColumnWidth(i, 12 * 256);
            Cell cell = row.createCell(i + 1);

            cell.setCellValue(headerRow[i]);
        }

        for (int i = 0; i < headerColumn.length; i++) {
            // each column 12 characters wide
            row = sheet.createRow(i + 1);

            sheet.setColumnWidth(0, 12 * 256);

            Cell cell = row.createCell(0);
            cell.setCellValue(headerColumn[i]);
        }


        for (int i = 0; i < headerColumn.length; i++) {
            row = sheet.getRow(i + 1);
            row.createCell(1).setCellValue(avarageInsertionList.get(i));
            row.createCell(2).setCellValue(avarageMergeList.get(i));
            row.createCell(3).setCellValue(avarageQuickList.get(i));
            row.createCell(4).setCellValue(avarageQuickMedianList.get(i));
        }

    }
}