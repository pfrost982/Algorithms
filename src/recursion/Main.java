package recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 300);
        }
        System.out.println(Arrays.toString(array));
        long startTime = System.nanoTime();
    }

    public int[] sortRecursion(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int[] firstHalf = new int[array.length / 2 + 1];
        int[] secondHalf = new int[array.length / 2 + 1];
        System.arraycopy(array, 0, firstHalf, 0, array.length/2 );
        System.arraycopy(array, array.length/2, secondHalf, 0, array.length/2 );
        return merge(sortRecursion(firstHalf), sortRecursion(secondHalf));
    }

    public int[] merge(int[] firstHalf, int[] secondHalf) {
        return firstHalf;
    }
}

