package lesson1;

public class Main {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;
        System.out.println("a: " + a + ", b: " + b + ", c: " + c + "\n");

        char[] charArray = {'a', 'b', 'c', 'd'};
        System.out.println(charArray);
        System.out.println();

        Cat[] cats = {new Cat("Барсик", 4), new Cat("Кузя", 2), new Cat("Рыжик", 5),
                new Cat("Мурзик", 3)};
        for (int i = 0; i < cats.length; i++) {
            System.out.println(cats[i]);
        }

        System.out.println("\nНачинаю поиск котика...");
        long startTime = System.nanoTime();
        int res = findCat(new Cat("Мурзик", 3), cats);
        System.out.println("Поиск занял: " + (System.nanoTime() - startTime) + " наносекунд");

    }

    static int findCat(Cat cat, Cat[] cats) {
        for (int i = 0; i < cats.length; i++) {
            if (cat.equals(cats[i])) {
                return i;
            }
        }
        return -1;
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
