import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
                // Check for null data
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        // Resize the backing array if it's full
        if (size == backingArray.length - 1) {
            resizeBackingArray(backingArray.length * 2);
        }
        
        // Add the data at the next available index
        size++;
        backingArray[size] = data;
        
        // Heapify up
        heapifyUp(size);
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
                // Check if the heap is empty
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        
        // Remove the min item from the root
        T minItem = backingArray[1];
        
        // Replace the root with the last item in the heap
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        
        // Heapify down
        heapifyDown(1);
        
        return minItem;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
        // Helper method to resize the backing array
    private void resizeBackingArray(int newCapacity) {
        T[] newBackingArray = (T[]) new Comparable[newCapacity];
        for (int i = 1; i <= size; i++) {
            newBackingArray[i] = backingArray[i];
        }
        backingArray = newBackingArray;
    }
    
    // Helper method to heapify up
    private void heapifyUp(int index) {
        while (index > 1 && backingArray[index].compareTo(backingArray[index / 2]) < 0) {
            swap(index, index / 2);
            index /= 2;
        }
    }
    
    // Helper method to heapify down
    private void heapifyDown(int index) {
        while (index * 2 <= size) {
            int minChildIndex = getMinChildIndex(index);
            if (backingArray[index].compareTo(backingArray[minChildIndex]) > 0) {
                swap(index, minChildIndex);
                index = minChildIndex;
            } else {
                break;
            }
        }
    }
    
    // Helper method to get the index of the minimum child
    private int getMinChildIndex(int index) {
        int leftChildIndex = index * 2;
        int rightChildIndex = index * 2 + 1;
        if (rightChildIndex <= size && backingArray[rightChildIndex].compareTo(backingArray[leftChildIndex]) < 0) {
            return rightChildIndex;
        } else {
            return leftChildIndex;
        }
    }
    
    // Helper method to swap elements in the backing array
    private void swap(int index1, int index2) {
        T temp = backingArray[index1];
        backingArray[index1] = backingArray[index2];
        backingArray[index2] = temp;
    }
}
