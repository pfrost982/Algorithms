package sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] originArray = new int[400];
        int[] copyArray;

        long startTime = System.nanoTime();
        for (int i = 0; i < originArray.length; i++) {
            originArray[i] = (int) (Math.random() * 300);
        }
        System.out.println("Время создания массива: " + (System.nanoTime() - startTime) + "\n");

        startTime = System.nanoTime();
        System.out.println(Arrays.toString(originArray));
        System.out.println("Время вывода массива в консоль: " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        copyArray = Arrays.copyOf(originArray, originArray.length);
        System.out.println("\nВремя копирования массива: " + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        Arrays.sort(copyArray);
        System.out.println("\nВремя сортировки массива: " + (System.nanoTime() - startTime));

        System.out.println("\nНачинаем линейный поиск элемента 299...");
        startTime = System.nanoTime();
        int index = linearSearch(copyArray, 299);
        if (index > 0) {
            System.out.println("Элемент найден на позиции: " + index);
        } else {
            System.out.println("Элемент не найден");
        }
        System.out.println("Время линейного поиска: " + (System.nanoTime() - startTime));
        System.out.println(Arrays.toString(copyArray));

        System.out.println("\nНачинаем бинарный поиск элемента 299...");
        startTime = System.nanoTime();
        index = binarySearch(copyArray, 299);
        if (index > 0) {
            System.out.println("Элемент найден на позиции: " + index);
        } else {
            System.out.println("Элемент не найден");
        }
        System.out.println("Время бинарного поиска: " + (System.nanoTime() - startTime));
        System.out.println(Arrays.toString(copyArray));

        System.out.println("\nНачинаем быструю сортировку...");
        copyArray = Arrays.copyOf(originArray, originArray.length);
        startTime = System.nanoTime();
        Arrays.sort(copyArray);
        System.out.println("Время быстрой сортировки массива: " + (System.nanoTime() - startTime));

        System.out.println("\nНачинаем пузырьковую сортировку...");
        copyArray = Arrays.copyOf(originArray, originArray.length);
        startTime = System.nanoTime();
        bubbleSort(copyArray);
        System.out.println("Время пузырьковой сортировки массива: " + (System.nanoTime() - startTime));

        System.out.println("\nНачинаем сортировку методом выбора...");
        copyArray = Arrays.copyOf(originArray, originArray.length);
        startTime = System.nanoTime();
        selectionSort(copyArray);
        System.out.println("Время сортировки методом выбора: " + (System.nanoTime() - startTime));

        System.out.println("\nНачинаем сортировку методом вставки...");
        copyArray = Arrays.copyOf(originArray, originArray.length);
        startTime = System.nanoTime();
        insertionSort(copyArray);
        System.out.println("Время сортировки методом вставки: " + (System.nanoTime() - startTime));
    }

    public static int linearSearch(int[] array, int key) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int binarySearch(int[] sortedArray, int key) {
        int index = -1;
        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static void bubbleSort(int[] array) {
        boolean isSorted = false;
        int buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    isSorted = false;
                    buf = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = buf;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            int minValue = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minValue) {
                    minIndex = j;
                    minValue = array[j];
                }
            }
            array[minIndex] = array[i];
            array[i] = minValue;
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}