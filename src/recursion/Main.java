package recursion;

import java.util.Arrays;

public class Main {
    final static int SIZE = 50;

//5.1
/*

        Вы подносите микрофон к колонкам, произносите в микрофон стартовое слово и оно
        звуча из колонок снова попадает в микрофон и звук начинает рекурсивно распространяться
        как изображение в двух зеркалах, расположенных друг против друга;
        Классический резонанс может рассматриваться и как рекурсивное наложение колебаний друг
        на друга с эффектом сумарного накопления амплитуды;

*/

//5.2

    void infiniteRecursion() {
        infiniteRecursion();
    }

    void finiteRecursion(int i) {
        if (i == 10_000) return;
        finiteRecursion(i++);
    }

//5.3

    //Стек вызова в обоихи случаях выглядит одинаково, разве что при рекурсии заполняется более интенсивно

    public static void main(String[] args) {
        int[] array = new int[SIZE];
        long startTime, finishTime;

//5.4
        //Заполняем массив цифрами от 0 до 99
        startTime = System.nanoTime();
        fillArrayWhile(array, 0);
        finishTime = System.nanoTime();
        System.out.println(Arrays.toString(array));
        System.out.println("Время заполнения массива в цикле: " + (finishTime - startTime));

        startTime = System.nanoTime();
        fillArrayRecursion(array, 0);
        finishTime = System.nanoTime();
        System.out.println(Arrays.toString(array));
        System.out.println("Время заполнения массива в рекурсивно: " + (finishTime - startTime));
//5.5

        startTime = System.nanoTime();
        int index = binarySearch(array, 0);
        finishTime = System.nanoTime();
        System.out.println("\nВремя выполнения обычного бинарного поиска: " + (finishTime - startTime) + " Индекс найденного элемента: " + index);

        startTime = System.nanoTime();
        binarySearchRecursive(array, 0, 0, array.length - 1);
        finishTime = System.nanoTime();
        System.out.println("Время выполнения рекурсивного бинарного поиска: " + (finishTime - startTime) + " Индекс найденного элемента: " + index);

//5.6
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 300);
        }
        System.out.println("\nСортировка слиянием исходный массив");
        System.out.println(Arrays.toString(array));
        startTime = System.nanoTime();
        array = mergeSort(array);
        finishTime = System.nanoTime();
        System.out.println("Отсортированный массив");
        System.out.println(Arrays.toString(array));
        System.out.println("Время сортировки методом слияния: " + (finishTime - startTime));

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 300);
        }
        System.out.println("\nОбычная сортировка исходный массив");
        System.out.println(Arrays.toString(array));
        startTime = System.nanoTime();
        Arrays.sort(array);
        finishTime = System.nanoTime();
        System.out.println("Отсортированный массив");
        System.out.println(Arrays.toString(array));
        System.out.println("Время обычной сортировки: " + (finishTime - startTime));

    }

    public static void fillArrayWhile(int[] array, int i) {
        while (i < array.length) {
            array[i] = i;
            i++;
        }
    }

    public static void fillArrayRecursion(int[] array, int i) {
        if (i < array.length) {
            array[i] = i;
            fillArrayRecursion(array, i + 1);
        } else {
            return;
        }
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

    public static int binarySearchRecursive(int[] sortedArray, int key, int low, int high) {
        int index = -1;
        if (low > high) {
            return index;
        }
        int mid = (low + high) / 2;
        if (sortedArray[mid] < key) {
            binarySearchRecursive(sortedArray, key, mid + 1, high);
        } else if (sortedArray[mid] > key){
            binarySearchRecursive(sortedArray, key, low, mid - 1);
        } else if (sortedArray[mid] == key) {
            index = mid;
        }
        return index;
    }

    public static int[] mergeSort(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int firstLength = array.length / 2;
        int secondLength = array.length - firstLength;
        int[] firstHalf = new int[firstLength];
        int[] secondHalf = new int[secondLength];
        System.arraycopy(array, 0, firstHalf, 0, firstLength);
        System.arraycopy(array, firstLength, secondHalf, 0, secondLength);
        return merge(mergeSort(firstHalf), mergeSort(secondHalf));
    }

    public static int[] merge(int[] firstHalf, int[] secondHalf) {
        int arrayLength = firstHalf.length + secondHalf.length;
        int[] array = new int[arrayLength];
        int firstIndex = 0, secondIndex = 0;
        for (int i = 0; i < arrayLength; i++) {
            if (firstIndex == firstHalf.length) {
                array[i] = secondHalf[secondIndex++];
                continue;
            }
            if (secondIndex == secondHalf.length) {
                array[i] = firstHalf[firstIndex++];
                continue;
            }
            if (firstHalf[firstIndex] < secondHalf[secondIndex]) {
                array[i] = firstHalf[firstIndex++];
            } else {
                array[i] = secondHalf[secondIndex++];
            }
        }
        return array;
    }
}

