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
        String result = length > RUN_LIMIT ? "Собака " + getName() + " не может пробежать >200м :(" : "Собака "
                + getName() + " пробежал(а) " + length + "м";
        System.out.println(result + "\n");
    }

    @Override
    public void swim(int length) {
        String result = length > SWIM_LIMIT ? "Собака " + getName() + " не может проплыть >10м :(" : "Собака "
                + getName() + " проплыл(а) " + length + "м";
        System.out.println(result + "\n");
    }

    public static int getCountDog() {
        return countDog;
    }
}
