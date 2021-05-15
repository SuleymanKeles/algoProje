
                                      MARMARA UNIVERSITY
                                    FACULTY OF ENGINEERING
                                 COMPUTER ENGINEERING DEPARTMENT
                             CSE 2046 Analysis of Algorithms , 2021 (Spring)
                                          Homework 1

     NAME                                                                         STUDENT NUMBER
     Süleyman KELEŞ                                                               150118039
     Emine ÇIĞ                                                                    150118012

     Submitted to                                                                 DUE DATE
     Ömer KORÇAK                                                                   16.05.2021


     SUBJECT
        This Project Compare Sorting Algorithm by time complexity with different input array type.

     Algorithms :
        1. Insertion-sort,
        2. Binary Insertion-sort1,
        3. Merge-sort,
        4. Quick-sort (pivot is always selected as the first element),
        5. Quick-sort with median-of-three pivot selection2.
        6. Heap-sort,
        7. Counting-sort3

     Input Array Types :
        1. randomArray
        2. repetitiveRandomArray
        3. minArray
        4. maxArray
        5. unique
        6. flash ChangeArray
        7. firstMinArray
        8. firstMaxArray
        9. halfIncreaseHalfDecrease
        10. halfOddHalfEvenMix

      Project have 3 loop
      First loop run  12 times. Input array Size {250, 500, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000}
      Second loop run  10 times. Input array type (shown above)
      Last loop run 500 times. Repeat Number for calculate average value.

      After all cycles, the data kept in memory are written to the excel data page.

     This Project use Apache Poi Libraries for excel data sheet.
     If you want to run the project, you must load the library in the link and set the path variable in line 69.
     https://www.apache.org/dyn/closer.lua/poi/release/bin/poi-bin-5.0.0-20210120.zip

