package test;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Data<String> data = new Data<>();
        data.add("Test");
        data.poll();
        Data<Integer> data1 = new Data<>();
        data1.add(5);
        data1.poll();
    }
}

class Data<T> {
    private ArrayList<T> list;

    public Data() {

        list = new ArrayList<>();
    }

    public void add(T obj) {
        list.add(obj);
    }

    public void poll() {
        System.out.println(list.remove(0));
    }
}