/*
    Matthew Draper
    CS 340 Project I: Sorting (and Heaps)
    Last edited on 08/24/2016
 */

package project01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Project01 {
    // Main method
    public static void main(String[] args) {
        System.out.println("PERMUTED TESTS============================================================================");
        runPermutedTests();
        System.out.println("PERMUTED TESTS END========================================================================\n\n");
        System.out.println("==========================================================================================");
        System.out.println("==========================================================================================");
        System.out.println("==========================================================================================\n\n");
        System.out.println("SORTED TESTS==============================================================================");
        runSortedTests();
        System.out.println("SORTED TESTS END==========================================================================");
    }

    private static void runPermutedTests(){
        int increment = 15;
        int size = increment;
        String[] array;
        String permPath = "perm-small/perm";
        String fileEnd = "K.txt";
        for (int i = 0; i < 10; i++){
            array = loadFileToArray(new File(permPath + size + fileEnd), size);

            System.out.println("Algorithm Run Times for perm" + size + fileEnd +": \n");

            /* Run Insertion Sort */
            long startTime = System.currentTimeMillis();
            Sort.insertionSort(array);
            long endTime = System.currentTimeMillis();
            System.out.println("\tInsertion Sort Run Time:    " + (endTime - startTime) + " ms");
            saveArrayToFile("sorted/perm/IS" + size + fileEnd, array);
            /* End Insertion Sort */

            array = loadFileToArray(new File(permPath + size + fileEnd), size);

            /* Run Merge Sort */
            startTime = System.currentTimeMillis();
            Sort.mergeSort(String.class, array, 0, array.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("\tMerge Sort Run Time:        " + (endTime - startTime) + " ms");
            saveArrayToFile("sorted/perm/MS" + size + fileEnd, array);
            /* End Merge Sort */

            array = loadFileToArray(new File(permPath + size + fileEnd), size);

            /* Run Heap Sort */
            startTime = System.currentTimeMillis();
            Sort.heapSort(array);
            endTime = System.currentTimeMillis();
            System.out.println("\tHeap Sort Run Time:         " + (endTime - startTime) + " ms");
            saveArrayToFile("sorted/perm/HS" + size + fileEnd, array);
            /* End Heap Sort */

            array = loadFileToArray(new File(permPath + size + fileEnd), size);

            /* Run Build Heap */
            startTime = System.currentTimeMillis();
            Sort.buildMaxHeap(array);
            endTime = System.currentTimeMillis();
            System.out.println("\tBuild Heap Run Time:        " + (endTime - startTime) + " ms\n\n\n");
            saveArrayToFile("sorted/perm/BH" + size + fileEnd, array);
            /* End Build Heap */

            size = size + increment;
        }

    }

    private static void runSortedTests(){
        int increment = 15;
        int size = increment;
        String[] array;
        String sortedPath = "sort-small/sorted";
        String fileEnd = "K.txt";
        for (int i = 0; i < 10; i++){
            array = loadFileToArray(new File(sortedPath + size + fileEnd), size);

            System.out.println("Algorithm Run Times for sorted" + size + "K.txt: \n");

            /* Run Insertion Sort */
            long startTime = System.currentTimeMillis();
            Sort.insertionSort(array);
            long endTime = System.currentTimeMillis();
            System.out.println("\tInsertion Sort Run Time:    " + (endTime - startTime) + " ms");
            saveArrayToFile("sorted/sorted/IS" + size + fileEnd, array);
            /* End Insertion Sort */

            array = loadFileToArray(new File(sortedPath + size + fileEnd), size);

            /* Run Merge Sort */
            startTime = System.currentTimeMillis();
            Sort.mergeSort(String.class, array, 0, array.length - 1);
            endTime = System.currentTimeMillis();
            System.out.println("\tMerge Sort Run Time:        " + (endTime - startTime) + " ms");
            saveArrayToFile("sorted/sorted/MS" + size + fileEnd, array);
            /* End Merge Sort */

            array = loadFileToArray(new File(sortedPath + size + fileEnd), size);

            /* Run Heap Sort */
            startTime = System.currentTimeMillis();
            Sort.heapSort(array);
            endTime = System.currentTimeMillis();
            System.out.println("\tHeap Sort Run Time:         " + (endTime - startTime) + " ms");
            saveArrayToFile("sorted/sorted/HS" + size + fileEnd, array);
            /* End Heap Sort */

            array = loadFileToArray(new File(sortedPath + size + fileEnd), size);

            /* Run Build Heap */
            startTime = System.currentTimeMillis();
            Sort.buildMaxHeap(array);
            endTime = System.currentTimeMillis();
            System.out.println("\tBuild Heap Run Time:        " + (endTime - startTime) + " ms\n\n\n");
            saveArrayToFile("sorted/sorted/BH" + size + fileEnd, array);
            /* End Build Heap */

            size = size + increment;
        }

    }

    private static String[] loadFileToArray(File file, int size){
        int actualSize = size * 1000;
        String[] array = new String[actualSize];
        try {
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine() && i < actualSize) {
                array[i] = scanner.nextLine();
                i++;
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }

    private static void saveArrayToFile(String filePath, String[] array){
        try {
            File output = new File(filePath);
            PrintWriter printer = new PrintWriter(output);
            for(int i = 0; i < array.length; i++){
                printer.println(array[i]);
            }
            printer.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found. Please scan in new file.");
        }
    }
}
