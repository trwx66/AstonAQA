package org.example.taskfirst;

abstract class Animal implements Runnable, Swimable {
    private final String name;
    private static int countAnimal = 0;

    public Animal(String name) {
        this.name = name;
        countAnimal++;
    }
    public static int getCountAnimal() {
        return countAnimal;
    }

    public String getName() {
        return name;
    }
}
