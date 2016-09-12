package project01;

import java.lang.reflect.Array;

public class Sort {

    /**
     * This method performs an insertion sort on the array given as long as the objects in the array extend the
     * Comparable interface.
     * @link https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
     * @param array An array of objects that is to be sorted.
     * @param <T> Object's class must implement the Comparable interface.
     */
    public static <T extends Comparable<T>> void insertionSort(T[] array){
        int j;
        for(int i = 1; i < array.length; i++){
            T key = array[i];
            // Insert array[i] into the sorted sequence A[1...i-1]
            j = i - 1;
            while(j >= 0 && array[j].compareTo(key) > 0){
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    /**
     * This method performs a merge sort on the array given as long as the objects in the array extend the
     * Comparable interface.
     * @param elementType The class type of the elements in the array that is to be sorted.
     * @param array The array that is to be sorted.
     * @param firstIndex The first index in the array that is to be sorted.
     * @param lastIndex The last index in the array that is to be sorted.
     * @param <T> The object type of the array. The object type must implement the Comparable interface.
     */
    public static <T extends Comparable<T>> void mergeSort(Class<T> elementType, T[] array, int firstIndex, int lastIndex){
        if(firstIndex < lastIndex){ // If the first index is less than the last index
            int midIndex = (firstIndex + lastIndex) / 2; // Floor function
            mergeSort(elementType, array, firstIndex, midIndex); // Perform mergeSort on the first half of the array
            mergeSort(elementType, array, midIndex + 1, lastIndex); // Perform mergeSort on the second half of the array
            merge(elementType, array, firstIndex, midIndex, lastIndex); // Merge the array
        }
    }

    /**
     * This method performs the merge portion of the mergeSort() method.
     * @param elementType The class type of the elements in the arrays that are to be merged.
     * @param array The array that is to be merged.
     * @param firstIndex The first index in the array that is to be merged.
     * @param midIndex The middle index in the array that is to be merged.
     * @param lastIndex The last index in the array that is to be merged.
     * @param <T> The object type of the arrays. The object type must implement the Comparable interface
     */
    private static <T extends Comparable<T>> void merge(Class<T> elementType, T[] array, int firstIndex, int midIndex, int lastIndex){
        int m = midIndex - firstIndex + 1; // The size of the left array
        int n = lastIndex - midIndex; // The size of the right array
        T[] leftArray = createGenericArray(elementType, m); // Create a new generic array for the left sub-array
        T[] rightArray = createGenericArray(elementType, n); // Create a new generic array for the right sub-array
        // from i = 0 to as long as i is less than the size of the left array
        for(int i = 0; i < m; i++){
            leftArray[i] = array[firstIndex + i]; // Store the left-half of the original array into the left sub-array
        }
        for(int j = 0; j < n; j++){
            rightArray[j] = array[midIndex + j + 1]; // Store the right-half of the original array into the right sub-array
        }
        int i = 0; // left array counter
        int j = 0; // right array counter
        // from k = the first index of the array as long as k is less than the last index
        for(int k = firstIndex; k <= lastIndex; k++){
            if(i < leftArray.length && j < rightArray.length){ // If the left and right counters are at less than the length of their respective arrays
                if(leftArray[i].compareTo(rightArray[j]) <= 0){ // If the left array at index i is less than or equal to the right array
                    array[k] = leftArray[i]; // Store the element from the original array in the left array at index i
                    i++; // increment i
                } else { // Else the left array at index i is not less than or equal to the right array
                    array[k] = rightArray[j]; // Store in the element from the original array in the right array at index j
                    j++; // increment j
                }
            } else if(i < leftArray.length){ // If i is less than the left array's length
                array[k] = leftArray[i]; // Store the element from the original array in the left array at index i
                i++; // increment i
            } else if(j < rightArray.length){ // If j is less than the right array's length
                array[k] = rightArray[j]; // Store in the element from the original array in the right array at index j
                j++; // increment j
            }
        }
    }

    /**
     * This method creates a generic array.
     * @param elementType The class type of the elements in the array that is to be created.
     * @param size The size of the array that is to be created.
     * @param <T> The object type of the array. The object type must implement the Comparable interface.
     * @return An array of the a generic type.
     */
    private static <T extends Comparable<T>> T[] createGenericArray(Class<T> elementType , int size){
        @SuppressWarnings("unchecked")
        T[] genericArray = (T[]) Array.newInstance(elementType, size);
        return  genericArray;
    }

    /**
     * This method simply takes two objects and swaps there positions in the given array.
     * @param array An array of objects with a type that implements the Comparable interface.
     * @param i Index of the first object to be swapped.
     * @param j Index of the second object to be swapped.
     * @param <T> Object's class must implement the Comparable interface.
     */
    private static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
        if (i != j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * This method performs an heap sort on the array given as long as the objects in the array extend the
     * Comparable interface.
     * @link https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
     * @param array An array of objects that is to be sorted.
     * @param <T> Object's class must implement the Comparable interface.
     */
    public static <T extends Comparable<T>> void heapSort(T[] array){
        buildMaxHeap(array); // Build the max heap from the given array
        int heapSize = array.length; // Initialize the heapSize to the original size of the array
        for(int i = array.length - 1; i > 0; i--){
            swap(array, 0, i); // Swap the two elements using a temp
            heapSize = heapSize - 1; // Decrement heapSize
            maxHeapify(array, 0, heapSize); // Percolate
        }
    }

    /**
     * Create a max heap from the given parent node in the array.
     * @param array Array to be made into a max heap.
     * @param i The index of the parent.
     * @param heapSize The current size of the heap.
     * @param <T> Object's class must implement the Comparable interface.
     */
    public static <T extends Comparable<T>> void maxHeapify(T[] array, int i, int heapSize){
        int leftChildIndex = (2 * i) + 1; // Left child's index
        int rightChildIndex = (2 * i) + 2; // Right child's index
        T parent = array[i]; // The parent
        T largest = parent; // The largest of the three. Set equal to parent to get rid of else statements
        T leftChild = null; // The left child is null
        T rightChild = null; // The right child is null
        if(leftChildIndex < heapSize){ // If the leftChildIndex exists
            leftChild = array[leftChildIndex]; // The leftChild is the element at leftChildIndex
        }
        if(rightChildIndex < heapSize){ // If the rightChildIndex exists
            rightChild = array[rightChildIndex]; // The rightChild is the element at rightChildIndex
        }
        // If the leftChild exists and if the leftChild is larger than the largest(parent)
        if(leftChild != null && leftChild.compareTo(largest) > 0){
            largest = leftChild; // Largest is the left child
        }
        // If the rightChild exists and if the rightChild is larger than the largest
        if(rightChild != null && rightChild.compareTo(largest) > 0){
            largest = rightChild; // Largest is the right child
        }
        if(largest.compareTo(parent) != 0){ // If the parent is not the largest
            if(leftChild != null && largest.compareTo(leftChild) == 0){ // If the leftChild is not null and is the largest
                swap(array, i, leftChildIndex); // Swap the parent's position in the array with the leftChild
                maxHeapify(array, leftChildIndex, heapSize); // Run maxHeapify on the new parent's leftChild
            }
            if(rightChild != null && largest.compareTo(rightChild) == 0){ // If the rightChild is not null and is the largest
                swap(array, i, rightChildIndex); // Swap the parent's position in the array with the rightChild
                maxHeapify(array, rightChildIndex, heapSize); // Run maxHeapify on the new parent's rightChild
            }
        }
    }

    /**
     * Create a max heap from the given array.
     * @param array The array to be made into a max heap.
     * @param <T> Object's class must implement the Comparable interface.
     */
    public static <T extends Comparable<T>> void buildMaxHeap(T[] array){
        int heapSize = array.length;
        for(int i = (array.length / 2) - 1; i >= 0; i--){
            maxHeapify(array, i, heapSize);
        }
    }
}
