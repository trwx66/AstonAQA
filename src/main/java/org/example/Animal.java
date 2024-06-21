package org.example;

abstract class Animal {
    protected String name;
    private static int countAnimal = 0;

    public Animal(String name) {
        this.name = name;
        countAnimal++;
    }

    public abstract void run(int length);

    public abstract void swim(int length);

    public static void printCount() {
        System.out.println("Всего создано животных: " + countAnimal + "\n");
    }
}
