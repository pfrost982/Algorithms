package collections2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    final private static int ARRAY_LENGTH = 20;

    public static void main(String[] args) {
        //Создание массива
        int[] array = new int[ARRAY_LENGTH];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        System.out.println("Исходный массив: " + Arrays.toString(array));
        long startTime, endTime;
//4.1
        MyStack stack = new MyStack(ARRAY_LENGTH);
        System.out.println("\nРабота со стеком:");
        startTime = System.nanoTime();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            stack.push(array[i]);
        }
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            System.out.println(stack.pop());
        }
        endTime = System.nanoTime();
        System.out.println("Время заполнения, вывода с удалением: " + (endTime - startTime));
//4.2
        MyQueue queue = new MyQueue(ARRAY_LENGTH);
        System.out.println("\nРабота с очередью:");
        startTime = System.nanoTime();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            queue.add(array[i]);
        }
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            System.out.println(queue.poll());
        }
        endTime = System.nanoTime();
        System.out.println("Время заполнения, вывода с удалением: " + (endTime - startTime));
//4.3
        MyDeque deque = new MyDeque(ARRAY_LENGTH);
        System.out.println("\nРабота с декой:");
        startTime = System.nanoTime();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            deque.addFirst(array[i]);
        }
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            System.out.println(deque.pollLast());
        }
        endTime = System.nanoTime();
        System.out.println("Время заполнения, вывода с удалением: " + (endTime - startTime));
//4.4
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(ARRAY_LENGTH);
        System.out.println("\nРабота с приоритетной очередью:");
        startTime = System.nanoTime();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            priorityQueue.add(array[i]);
        }
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            System.out.println(priorityQueue.poll());
        }
        endTime = System.nanoTime();
        System.out.println("Время заполнения, вывода с удалением: " + (endTime - startTime));
//4.5
        LinkedList<Integer> linkedList = new LinkedList<>();
        System.out.println("\nРабота со связанным списком:");
        startTime = System.nanoTime();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            linkedList.addFirst(array[i]);
        }
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            System.out.println(linkedList.pollLast());
        }
        endTime = System.nanoTime();
        System.out.println("Время заполнения, вывода с удалением: " + (endTime - startTime));
    }
}

class MyStack {
    int[] stack;
    int head = -1;

    public MyStack(int maxSize) {
        this.stack = new int[maxSize];
    }

    public boolean isEmpty() {
        return head == -1;
    }

    public boolean isFull() {
        return head == stack.length - 1;
    }

    public boolean push(int value) {
        if (isFull()) {
            return false;
        }
        head++;
        stack[head] = value;
        return true;
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return stack[head];
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return stack[head--];
    }
}

class MyQueue {
    int[] queue;
    int first = -1;
    int last = -1;
    int size = 0;

    public MyQueue(int maxSize) {
        queue = new int[maxSize];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public boolean add(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            first = 1;
            last = 1;
            size++;
            queue[last] = value;
            return true;
        }
        last++;
        if (last == queue.length) {
            last = 0;
        }
        size++;
        queue[last] = value;
        return true;
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return queue[first];
    }

    public int poll() {
        if (isEmpty()) {
            return -1;
        }
        int value = queue[first];
        size--;
        if (size == 0) {
            first = -1;
            last = -1;
            return value;
        }
        first++;
        if (first == queue.length) {
            first = 0;
        }
        return value;
    }
}

class MyDeque {
    int[] deque;
    int first = -1;
    int last = -1;
    int size = 0;

    public MyDeque(int maxSize) {
        deque = new int[maxSize];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == deque.length;
    }

    public boolean addFirst(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            first = deque.length - 1;
            last = first;
            size++;
            deque[first] = value;
            return true;
        }
        first--;
        if (first < 0) {
            first = deque.length - 1;
        }
        size++;
        deque[first] = value;
        return true;
    }

    public boolean addLast(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            first = 1;
            last = 1;
            size++;
            deque[last] = value;
            return true;
        }
        last++;
        if (last == deque.length) {
            last = 0;
        }
        size++;
        deque[last] = value;
        return true;
    }

    public int peekFirst() {
        if (isEmpty()) {
            return -1;
        }
        return deque[first];
    }

    public int peekLast() {
        if (isEmpty()) {
            return -1;
        }
        return deque[last];
    }

    public int pollFirst() {
        if (isEmpty()) {
            return -1;
        }
        int value = deque[first];
        size--;
        if (size == 0) {
            first = -1;
            last = -1;
            return value;
        }
        first++;
        if (first == deque.length) {
            first = 0;
        }
        return value;
    }

    public int pollLast() {
        if (isEmpty()) {
            return -1;
        }
        int value = deque[last];
        size--;
        if (size == 0) {
            first = -1;
            last = -1;
            return value;
        }
        last--;
        if (last < 0) {
            last = deque.length - 1;
        }
        return value;
    }
}