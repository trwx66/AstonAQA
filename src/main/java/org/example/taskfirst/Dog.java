package org.example.taskfirst;

public class Dog extends Animal {
    private static int countDog = 0;
    private static final int RUN_LIMIT = 500;
    private static final int SWIM_LIMIT = 10;

    public Dog(String dogName) {
        super(dogName);
        countDog++;
    }

    @Override
    public void run(int length) {
        System.out.println(length > RUN_LIMIT ? "Собака " + getName() + " не может пробежать >200м :(" :
                "Собака " + getName() + " пробежал(а) " + length + "м");
    }

    @Override
    public void swim(int length) {
        System.out.println(length > SWIM_LIMIT ? "Собака " + getName() + " не может проплыть >10м :(\n" :
                "Собака " + getName() + " проплыл(а) " + length + "м");
    }

    public static int getCountDog() {
        return countDog;
    }
}
