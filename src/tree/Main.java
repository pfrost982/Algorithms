package tree;

public class Main {
    private static final int NODE_NUMBER = 100;
    public static void main(String[] args) {
        Tree<String> tree = new Tree<String>();
        for (int i = 0; i < NODE_NUMBER; i++) {
            int id = (int) (Math.random() * 100);
            System.out.println(id);
            boolean isAdded = tree.add(id, Integer.toString(id));
            if(!isAdded){
                System.out.println("Повтор " + id);
                System.out.println("Поиск " + tree.find(id));
            }
        }
        System.out.println("Всего в дереве: " + Tree.count);

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
}

class Tree<T> {
    private Node<T> root = null;
    public static int count = 0;

    public T find(int id) {
        Node<T> current = root;
        while (current != null) {
            if (id == current.getId()) {
                return current.getObj();
            } else if (id < current.getId()) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        return null;
    }

    public boolean add(int id, T obj) {
        Node<T> newNode = new Node<T>(id, obj);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node<T> parent = null;
        Node<T> current = root;
        boolean isLeftChild = false;
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
        count++;
        return true;
    }
}