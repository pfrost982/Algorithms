package collections;


import java.util.*;

public class Main {
    public static void main(String[] args) {
//3.1
        Integer[] array = new Integer[400];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 300);
        }
        long startTime = System.nanoTime();
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(array));
        System.out.println("Время преобразования в список: " + (System.nanoTime() - startTime) + "\n");

        startTime = System.nanoTime();
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(array));
        System.out.println("Время преобразования в коллекцию: " + (System.nanoTime() - startTime) + "\n");
//3.2
        startTime = System.nanoTime();
        arrayList.add(233);
        arrayList.remove(new Integer(233));
        arrayList.add(300, 500);
        arrayList.get(300);
        System.out.println("Время работы с основными методами ArrayList: " + (System.nanoTime() - startTime) + "\n");
//3.3
        System.out.println("Пример работы с односвязным списком:");
        OneLinkedList<String> stringList = new OneLinkedList<String>();
        System.out.println("Проверка на пустоту: " + stringList.isEmpty());
        stringList.insert("first");
        stringList.insert("second");
        stringList.insert("third");
        stringList.insert("fourth");
        System.out.println("Вывод после добавления:");
        stringList.print();
        System.out.println("Удаляем элемент: " + stringList.delete().getContainedObject());
        System.out.println("Удаляем элемент: " + stringList.delete().getContainedObject());
        System.out.println("Вывод после удаления:");
        stringList.print();
        System.out.println("Поиск элемента second: " + stringList.find("second"));
//3.4
        System.out.println("\nПример работы с основными методами LinkedList: ");
        LinkedList<Integer> intLinkedList = new LinkedList<>(arrayList);
        System.out.println("Длинна списка после создания: " + intLinkedList.size());
        for (int i = 0; i < 390; i++) {
            intLinkedList.remove();
        }
        System.out.println("Список после удаления 390 элементов: " + intLinkedList);
        intLinkedList.addFirst(0);
        intLinkedList.addLast(999);
        System.out.println("Список после добавления в начало(" + intLinkedList.peekFirst() +
                ") и конец(" + intLinkedList.peekLast() + "): " + intLinkedList);
        System.out.println("Содержит ли список 999: " + intLinkedList.contains(999));
        System.out.println("Список после удаления " + intLinkedList.pollFirst() +
                " и " + intLinkedList.pollLast() + ": " + intLinkedList);
        Cat[] cats = {
                new Cat("Барсик", 4),
                new Cat("Кузя", 2),
                new Cat("Рыжик", 5),
                new Cat("Мурзик", 3),
                new Cat("Марс", 6),
                new Cat("Кнопка", 4),
                new Cat("Белка", 3)};
        LinkedList<Cat> catsLinkedList = new LinkedList<>(Arrays.asList(cats));
//3.5
        System.out.println("\nПример работы с итератором: ");
        Iterator<Cat> catIterator = catsLinkedList.iterator();
        startTime = System.nanoTime();
        while (catIterator.hasNext()) {
            System.out.println(catIterator.next());
        }
        System.out.println("Время работы с итератором: " + (System.nanoTime() - startTime) + "\n");
    }
}

class Node<T> {
    private T containedObject;
    private Node<T> nextNode;

    public Node(T content) {
        this.containedObject = content;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public T getContainedObject() {
        return containedObject;
    }
}

class OneLinkedList<T> {

    private Node<T> firstNode;

    public boolean isEmpty() {
        return (firstNode == null);
    }

    public void insert(T obj) {
        Node<T> newNode = new Node<>(obj);
        newNode.setNextNode(firstNode);
        firstNode = newNode;
    }

    public Node<T> delete() {
        if (isEmpty()) {
            return null;
        } else {
            Node<T> deletedNode = firstNode;
            firstNode = firstNode.getNextNode();
            return deletedNode;
        }
    }

    public void print() {
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            System.out.println(currentNode.getContainedObject());
            currentNode = currentNode.getNextNode();
        }
    }

    public boolean find(T searchObject) {
        Node<T> currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.getContainedObject().equals(searchObject)) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }
}

class Cat {
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        if (age != cat.age) return false;
        return name.equals(cat.name);
    }

    @Override
    public String toString() {
        return "Кот " + name + ", возраст " + age;
    }
}


