package ru.chentsov.algorithms;

/*
+Дописать методы удаления в классе массива
+улучшить пузырьковую сортировку
+Подсчитать количество операций в сортировка и сравнить их с О - большое
+Реализовать сортировку подсчетом
*/

import java.util.function.Consumer;

public class Array {
    private int arr[];
    private int size;
    private boolean isSorted;

    private int opsCounter;

    private Array() {
        isSorted = false;
    }

    public Array(int size) {
        this();
        this.size = 0;
        this.arr = new int[size];
    }

    public Array(int... args) {
        this();
        this.size = args.length;
        this.arr = args;
    }

    public int length() {
        return size;
    }

    public int get(int index) {
        if (index >= size)
            throw new ArrayIndexOutOfBoundsException(index);
        return arr[index];
    }

    public void set(int index, int value) {
        if (index >= size)
            throw new ArrayIndexOutOfBoundsException(index);
        arr[index] = value;
        isSorted = false;
    }

    public void append(int value) {
        if (size >= arr.length) {
            int[] temp = arr;
            arr = new int[size * 2];
            System.arraycopy(temp, 0, arr, 0, size);
        }
        arr[size++] = value;
        isSorted = false;
    }

    /**
     * Deletes the last value in array
     */
    boolean delete() {
        if (size == 0) return false;
        size--;
        return true;
    }

    boolean delete(int index) { // by index
        if (index >= size)
            throw new ArrayIndexOutOfBoundsException(index);
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return true;
    }

    boolean deleteAll(int value) { // by value
        boolean deletedOnce = false;

        for (int i = 0; i < size; ) {
            if (arr[i] == value) {
                delete(i);
                deletedOnce = true;
            } else i++;
        }
        return deletedOnce;
    }

    void deleteAll() { // clear array
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; ; i++) {
            b.append(arr[i]);
            if (i == size - 1)
                return b.append("]").toString();
            b.append(", ");
        }
    }

    /**
     * Search
     */
    public int find(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value)
                return i;
        }
        return -1;
    }

    public boolean hasValue(int value) {
        if (!isSorted)
            throw new RuntimeException("Try the 'find' method");
        int l = 0;
        int r = size;
        int m;
        while (l < r) {
            // n >> k == n / 2 ^ k
            m = (l + r) >> 1; // 8 = 00001000 >> 1 = 00000100 = 4
            if (value == arr[m])
                return true;
            else if (value < arr[m])
                r = m;
            else
                l = m + 1;
        }
        return false;
    }

    /**
     * Sort
     */
    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //simplest enhance is to decrease second loop by i;
    public void sortBubble() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                opsCounter++;
                if (arr[j] > arr[j + 1])    swap(j, j + 1);
            }
        }
        isSorted = true;
    }

    public void sortBubbleEnhanced() {
        boolean hasChanges;
        do {
            hasChanges = false;
            for (int i = 1; i < size - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    swap(i, i + 1);
                    hasChanges = true;
                }
                opsCounter++;
            }
            for (int i = 0; i < size - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    swap(i, i + 1);
                    hasChanges = true;
                }
                opsCounter++;
            }
        } while (hasChanges);
        isSorted = true;
    }

    public void sortSelect() {
        for (int flag = 0; flag < size; flag++) {
            int cMin = flag;
            for (int rem = flag + 1; rem < size; rem++) {
                opsCounter++;
                if (arr[rem] < arr[cMin])
                    cMin = rem;
            }
            swap(flag, cMin);
        }
        isSorted = true;
    }

    public void sortInsert() {
        int in;
        for (int out = 0; out < size; out++) {
            int temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                opsCounter++;
                arr[in] = arr[in - 1];
                in--;
            }
            arr[in] = temp;
        }
        isSorted = true;
    }

    public int getMax() {
        if (size < 1) throw new RuntimeException("array is empty");
        int max = arr[0];
        for (int i = 1; i < size; i++) {
            opsCounter++;
            if (max < arr[i]) max = arr[i];
        }
        return max;
    }

    public int getMin() {
        if (size < 1) throw new RuntimeException("array is empty");
        int min = arr[0];
        for (int i = 1; i < size; i++) {
            opsCounter++;
            if (min > arr[i]) min = arr[i];
        }
        return min;
    }

    /**
     * Please note: counter takes into account search of min and max in an array.
     * That definitely costs us additional resources (O(2N)).
     */
    public void sortByCounting() {
        int min = getMin();
        int max = getMax();
        int k = max - min + 1;
        //freqHolder[0] -> min, ..., freqHolder[k-1] -> max.
        int[] freqHolder = new int[k];
        for (int i = 0; i < size; i++) {
            freqHolder[arr[i] - min]++;
            opsCounter++;
        }

        deleteAll();
        for (int i = 0; i < k; i++) {
            while (freqHolder[i] > 0) {
                append(min + i);
                freqHolder[i]--;
                opsCounter++;
            }
        }
    }

    public static void main(String[] args) {
        int[] testArray = {1, 5, 8, -2, -5, 5, 12, 99, 0, -1, -18, -38, 2, -1, 76, 34, 2, -1, 2};
        Array array = new Array(testArray.clone());
        System.out.println("Sample array: " + array + ", array size is " + array.size);

        int indexToDelete = 1;
        array.delete(indexToDelete);
        System.out.println("After index deletion by index " + indexToDelete + ": " + array + ", array size is " + array.size);

        array = new Array(testArray.clone());
        int valueToDelete = -1;
        array.deleteAll(valueToDelete);
        System.out.println("After value deletion " + valueToDelete + ": " + array + ", array size is " + array.size);
        System.out.println("\n-----------------SORTING----------------- \n");

        System.out.println("Bubble sort - small enhance:");
        new Array(testArray.clone()).testSort(Array::sortBubble);
        System.out.println("Enhanced bubble sort (odd-even):");
        new Array(testArray.clone()).testSort(Array::sortBubbleEnhanced);
        System.out.println("Select sort:");
        new Array(testArray.clone()).testSort(Array::sortSelect);
        System.out.println("Insert sort:");
        new Array(testArray.clone()).testSort(Array::sortInsert);
        System.out.println("Counting sort:");
        new Array(testArray.clone()).testSort(Array::sortByCounting);
    }

    public void testSort(Consumer<Array> sort) {
        opsCounter = 0;
        sort.accept(this);
        System.out.println(this);
        System.out.println("Operations used: " + opsCounter + "\n");
    }

}