package tree;

//6.1
/*
    В «Энциклопедии» использовалась древовидная диаграмма для отображения способа упорядочивания её элементов.
Для построения родового дерева. Биология: филогенетическое дерево. Бизнес: финансовая пирамида
Управление проектами: структура декомпозиции работ
*/

//6.7
/*
    Для больших баз данных, где критично время поиска, которое зависит от высоты дерево, а сбалансированное дерево
имеет минимальную высоту при любом количестве данных.
*/

import java.util.Arrays;

public class Main {
    private static final int SIZE = 20;
    private static final int MAX_RANDOM = 20;

    public static void main(String[] args) {
//6.2 - 6.5
        Tree<String> tree = new Tree<>();
        int[] array = new int[SIZE];
        long startTime, finishTime;
        boolean success;

        System.out.println("Формируем дерево случайным образом...");
        startTime = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            int id = (int) (Math.random() * MAX_RANDOM);
            success = tree.add(id, "число " + id);
            if (!success) {
                System.out.println("Повтор " + id + ", не добавлено");
            }
        }
        finishTime = System.nanoTime();
        System.out.println("Дерево сформировано, время формирования: " + (finishTime - startTime));
        tree.printTree();
        System.out.println("Минимальный: " + tree.min() + ", максимальный: " + tree.max());

        System.out.println("\nПоиск и удаление случайных ключей...");
        startTime = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            int id = (int) (Math.random() * MAX_RANDOM);
            String foundString = tree.find(id);
            if (foundString == null) {
                System.out.println("Ключ " + id + " не найден");
            } else {
                tree.delete(id);
            }
        }
        finishTime = System.nanoTime();
        System.out.println("Поиск и удаление заверешено, время: " + (finishTime - startTime));
        tree.printTree();
        System.out.println("Минимальный: " + tree.min() + ", максимальный: " + tree.max());

//6.6
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * MAX_RANDOM);
        }
        System.out.println("\nПирамидальная сортировка исходный массив");
        System.out.println(Arrays.toString(array));
        startTime = System.nanoTime();
        heapSort(array);
        finishTime = System.nanoTime();
        System.out.println("Отсортированный массив");
        System.out.println(Arrays.toString(array));
        System.out.println("Время сортировки методом слияния: " + (finishTime - startTime));

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * MAX_RANDOM);
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

    private static int unsortedLength;

    static void heapSort(int[] array) {
        heapBuild(array);
        while (unsortedLength > 1) {
            int temp = array[0];
            array[0] = array[unsortedLength - 1];
            array[unsortedLength - 1] = temp;
            unsortedLength--;
            heapify(array, 0);
        }
    }

    static void heapBuild(int[] array) {
        unsortedLength = array.length;
        for (int i = array.length / 2; i >= 0; i--) {
            heapify(array, i);
        }
    }

    static void heapify(int[] array, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        if (left < unsortedLength && array[largest] < array[left]) largest = left;
        if (right < unsortedLength && array[largest] < array[right]) largest = right;
        if (i != largest) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, largest);
        }
    }
}

class Node<T> {
    private final int id;
    private final T obj;
    private Node<T> leftChild = null;
    private Node<T> rightChild = null;

    public Node(int id, T obj) {
        this.id = id;
        this.obj = obj;
    }

    public int getId() {
        return id;
    }

    public T getObj() {
        return obj;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "{" + "id = " + id + ", obj = " + obj + '}';
    }
}

class Tree<T> {
    private Node<T> root = null;
    private Node<T> findParent;
    boolean isLeftChild;

    public T find(int id) {
        Node<T> node = findNode(id);
        if (node != null) {
            return node.getObj();
        }
        return null;
    }

    private Node<T> findNode(int id) {
        findParent = null;
        Node<T> current = root;
        while (current != null) {
            if (id == current.getId()) {
                return current;
            } else if (id < current.getId()) {
                findParent = current;
                current = current.getLeftChild();
                isLeftChild = true;
            } else {
                findParent = current;
                current = current.getRightChild();
                isLeftChild = false;
            }
        }
        return null;
    }

    public boolean add(int id, T obj) {
        Node<T> newNode = new Node<>(id, obj);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node<T> parent = null;
        Node<T> current = root;
        isLeftChild = false;
        while (current != null) {
            parent = current;
            if (newNode.getId() == current.getId()) {
                return false;
            } else if (newNode.getId() < current.getId()) {
                current = current.getLeftChild();
                isLeftChild = true;
            } else if (newNode.getId() > current.getId()) {
                current = current.getRightChild();
                isLeftChild = false;
            }
        }
        if (isLeftChild) {
            parent.setLeftChild(newNode);
        } else {
            parent.setRightChild(newNode);
        }
        return true;
    }

    public void printTree() {
        troughAll(root);
    }

    private void troughAll(Node<T> currentNode) {
        if (currentNode != null) {
            troughAll(currentNode.getLeftChild());
            System.out.println(currentNode);
            troughAll(currentNode.getRightChild());
        }
    }

    public T max() {
        Node<T> result = maxNode(root);
        if (result != null) {
            return result.getObj();
        }
        return null;
    }

    private Node<T> maxNode(Node<T> current) {
        Node<T> parent = null;
        while (current != null) {
            parent = current;
            current = current.getRightChild();
        }
        return parent;
    }

    public T min() {
        Node<T> result = minNode(root);
        if (result != null) {
            return result.getObj();
        }
        return null;
    }

    private Node<T> minNode(Node<T> current) {
        Node<T> parent = null;
        while (current != null) {
            parent = current;
            current = current.getLeftChild();
        }
        return parent;
    }

    public boolean delete(int id) {
        if (root == null) {
            return false;
        }

        Node<T> nodeToRemove = findNode(id);
        if (nodeToRemove == null) {
            return false;
        }

        if (nodeToRemove.getLeftChild() == null) {
            if (nodeToRemove == root) {
                root = nodeToRemove.getRightChild();
                return true;
            }
            if (isLeftChild) {
                findParent.setLeftChild(nodeToRemove.getRightChild());
            } else {
                findParent.setRightChild(nodeToRemove.getRightChild());
            }
            return true;
        }
        if (nodeToRemove.getRightChild() == null) {
            if (nodeToRemove == root) {
                root = nodeToRemove.getLeftChild();
                return true;
            }
            if (isLeftChild) {
                findParent.setLeftChild(nodeToRemove.getLeftChild());
            } else {
                findParent.setRightChild(nodeToRemove.getLeftChild());
            }
            return true;
        }

        Node<T> saveFindParent = findParent;
        boolean saveIsLeftChild = isLeftChild;
        Node<T> minRight = minNode(nodeToRemove.getRightChild());
        delete(minRight.getId());
        minRight.setLeftChild(nodeToRemove.getLeftChild());
        minRight.setRightChild(nodeToRemove.getRightChild());
        if (nodeToRemove == root) {
            root = minRight;
            return true;
        }
        if (saveIsLeftChild) {
            saveFindParent.setLeftChild(minRight);
        } else {
            saveFindParent.setRightChild(minRight);
        }
        return true;
    }


}