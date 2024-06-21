package org.example;

public class Dog extends Animal {
    private static int countDog = 0;
    private static final int RUN_LIMIT = 500;
    private static final int SWIM_LIMIT = 10;

    public Dog(String name) {
        super(name);
        countDog++;
    }

    public static void printCount() {
        System.out.println("Всего создано собак: " + countDog + "\n");
    }

    @Override
    public void run(int length) {
        String result = length > RUN_LIMIT ? "Собака " + name + " не может пробежать >200м :(" : "Собака "
                + name + " пробежал(а) " + length + "м";
        System.out.println(result + "\n");
    }

    @Override
    public void swim(int length) {
        String result = length > SWIM_LIMIT ? "Собака " + name + " не может проплыть >10м :(" : "Собака "
                + name + " проплыл(а) " + length + "м";
        System.out.println(result + "\n");
    }
}
