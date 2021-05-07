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
    static ArrayList<ArrayList<Double>> avarageInsertion = new ArrayList<>();
    static ArrayList<ArrayList<Double>> avarageBinaryInsertion = new ArrayList<>();
    static ArrayList<ArrayList<Double>> avarageMerge = new ArrayList<>();
    static ArrayList<ArrayList<Double>> avarageQuickFirst = new ArrayList<>();
    static ArrayList<ArrayList<Double>> avarageQuickMedian = new ArrayList<>();
    static ArrayList<ArrayList<Double>> avarageHeap = new ArrayList<>();
    static ArrayList<ArrayList<Double>> avarageCounting = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;
        long estimatedTimeInsertion = 0;
        long estimatedTimeMerge = 0;
        long estimatedTimeQuickFirst = 0;
        long estimatedTimeQuickMedian = 0;
        long estimatedTimeBinaryInsertion = 0;
        long estimatedTimeHeap = 0;
        long estimatedTimeCounting = 0;

        String[] sheetName = {"randomArray", "repetitiveRandomArray", "minArray", "maxArray", "divisionArray2",};

        Workbook workbook = null;

        workbook = new XSSFWorkbook();

        Sheet sheetAverage = workbook.createSheet("Average");

        int repeatNumber = 100;

        for (int inputArrayType = 0; inputArrayType < sheetName.length; inputArrayType++) {
            InputArrays inputs = new InputArrays();

            System.out.println(sheetName[inputArrayType] + "Finished...");
            int[] array = new int[10000];
            int arraySize = 10;
            switch (inputArrayType) {
                case 0 -> array = inputs.randomArray();
                case 1 -> array = inputs.repetitiveRandomArray();
                case 2 -> array = inputs.minArray();
                case 3 -> array = inputs.maxArray();
                case 4 -> array = inputs.divisionArray(2);
                default -> System.exit(0);
            }
            int[] arr1 = array;
            int[] arr2 = array;
            int[] arr3 = array;
            int[] arr4 = array;
            int[] arr5 = array;
            int[] arr6 = array;
            int[] arr7 = array;

            final String[] header = {"Insertion", "Binary - Insertion ", "Merge", "Quick First", "Quick Median", "Heap", "Counting"};

            Sheet sheet = workbook.createSheet(sheetName[inputArrayType]);

            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("TITLE");

            for (int j = 0; j < 76; j += 8) {
                for (int i = 0; i < header.length; i++) {
                    // each column 12 characters wide
                    sheet.setColumnWidth(i, 12 * 256);
                    Cell cell = row.createCell(i + 1 + j);
                    cell.setCellValue(header[i]);
                }
            }

            row = sheet.createRow(1);
            row.createCell(0).setCellValue("AVARAGE");

            row.createCell(1).setCellFormula(averageText("B"));
            row.createCell(2).setCellFormula(averageText("C"));
            row.createCell(3).setCellFormula(averageText("D"));
            row.createCell(4).setCellFormula(averageText("E"));
            row.createCell(5).setCellFormula(averageText("F"));
            row.createCell(6).setCellFormula(averageText("G"));
            row.createCell(7).setCellFormula(averageText("H"));
            row.createCell(8).setCellFormula(averageText("I"));
            row.createCell(9).setCellFormula(averageText("J"));
            row.createCell(10).setCellFormula(averageText("K"));
            row.createCell(11).setCellFormula(averageText("L"));
            row.createCell(12).setCellFormula(averageText("M"));
            row.createCell(13).setCellFormula(averageText("N"));
            row.createCell(14).setCellFormula(averageText("O"));
            row.createCell(15).setCellFormula(averageText("P"));
            row.createCell(16).setCellFormula(averageText("Q"));
            row.createCell(17).setCellFormula(averageText("R"));
            row.createCell(18).setCellFormula(averageText("S"));
            row.createCell(19).setCellFormula(averageText("T"));
            row.createCell(20).setCellFormula(averageText("U"));
            row.createCell(21).setCellFormula(averageText("V"));
            row.createCell(22).setCellFormula(averageText("W"));
            row.createCell(23).setCellFormula(averageText("X"));
            row.createCell(24).setCellFormula(averageText("Y"));
            row.createCell(25).setCellFormula(averageText("Z"));
            row.createCell(26).setCellFormula(averageText("AA"));
            row.createCell(27).setCellFormula(averageText("AB"));
            row.createCell(28).setCellFormula(averageText("AC"));
            row.createCell(29).setCellFormula(averageText("AD"));
            row.createCell(30).setCellFormula(averageText("AE"));
            row.createCell(31).setCellFormula(averageText("AF"));
            row.createCell(32).setCellFormula(averageText("AG"));
            row.createCell(33).setCellFormula(averageText("AH"));
            row.createCell(34).setCellFormula(averageText("AI"));
            row.createCell(35).setCellFormula(averageText("AJ"));
            row.createCell(36).setCellFormula(averageText("AK"));
            row.createCell(37).setCellFormula(averageText("AL"));
            row.createCell(38).setCellFormula(averageText("AM"));
            row.createCell(39).setCellFormula(averageText("AN"));
            row.createCell(40).setCellFormula(averageText("AO"));
            row.createCell(41).setCellFormula(averageText("AP"));
            row.createCell(42).setCellFormula(averageText("AQ"));
            row.createCell(43).setCellFormula(averageText("AR"));
            row.createCell(44).setCellFormula(averageText("AS"));
            row.createCell(45).setCellFormula(averageText("AT"));
            row.createCell(46).setCellFormula(averageText("AU"));
            row.createCell(47).setCellFormula(averageText("AV"));
            row.createCell(48).setCellFormula(averageText("AW"));
            row.createCell(49).setCellFormula(averageText("AX"));
            row.createCell(50).setCellFormula(averageText("AY"));
            row.createCell(51).setCellFormula(averageText("AZ"));          
            row.createCell(52).setCellFormula(averageText("BB"));
            row.createCell(53).setCellFormula(averageText("BC"));
            row.createCell(54).setCellFormula(averageText("BD"));
            row.createCell(55).setCellFormula(averageText("BE"));
            row.createCell(56).setCellFormula(averageText("BF"));
            row.createCell(57).setCellFormula(averageText("BG"));
            row.createCell(58).setCellFormula(averageText("BH"));
            row.createCell(59).setCellFormula(averageText("BI"));
            row.createCell(60).setCellFormula(averageText("BJ"));
            row.createCell(61).setCellFormula(averageText("BK"));
            row.createCell(62).setCellFormula(averageText("BL"));
            row.createCell(63).setCellFormula(averageText("BM"));
            row.createCell(64).setCellFormula(averageText("BN"));
            row.createCell(65).setCellFormula(averageText("BO"));
            row.createCell(66).setCellFormula(averageText("BP"));
            row.createCell(67).setCellFormula(averageText("BQ"));
            row.createCell(68).setCellFormula(averageText("BR"));
            row.createCell(69).setCellFormula(averageText("BS"));
            row.createCell(70).setCellFormula(averageText("BT"));
            row.createCell(71).setCellFormula(averageText("BU"));
            row.createCell(72).setCellFormula(averageText("BV"));
            row.createCell(73).setCellFormula(averageText("BW"));
            row.createCell(74).setCellFormula(averageText("BX"));
            row.createCell(75).setCellFormula(averageText("BY"));
            row.createCell(76).setCellFormula(averageText("BZ"));
            row.createCell(77).setCellFormula(averageText("CA"));
            row.createCell(78).setCellFormula(averageText("CB"));




            int x = 0;
            while (x < repeatNumber) {
                row = sheet.createRow(x + 2);
                row.createCell(0).setCellValue(x + 1);
                for (int i = 0; i < 10; i++) {
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

                    row.createCell(1 + i * 8).setCellValue(estimatedTimeInsertion);
                    row.createCell(2 + i * 8).setCellValue(estimatedTimeBinaryInsertion);
                    row.createCell(3 + i * 8).setCellValue(estimatedTimeMerge);
                    row.createCell(4 + i * 8).setCellValue(estimatedTimeQuickFirst);
                    row.createCell(5 + i * 8).setCellValue(estimatedTimeQuickMedian);
                    row.createCell(6 + i * 8).setCellValue(estimatedTimeHeap);
                    row.createCell(7 + i * 8).setCellValue(estimatedTimeCounting);

                    inputs.arraySize = arraySize * i;

                    switch (i) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        case 9:
                            break;
                        default:
                            break;
                    }
                }
                x++;
            }
        }
//        System.out.println(avarageInsertionList.toString());
//        System.out.println(avarageMergeList.toString());
//        System.out.println(avarageQuickList.toString());
//        System.out.println(avarageQuickMedianList.toString());

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

    public static String averageText(String letter) {
        return "AVERAGE(" + letter + "3:" + letter + "1003)";
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