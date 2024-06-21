package org.example;

public class Cat extends Animal {
    private static int countCat = 0;
    private static final int RUN_LIMIT = 200;
    private boolean satiety;

    public Cat(String name) {
        super(name);
        this.satiety = false;
        countCat++;
    }

    public static void printCount() {
        System.out.println("Всего создано котов: " + countCat + "\n");
    }

    @Override
    public void run(int length) {
        String result = length > RUN_LIMIT ? "Кот " + name + " не может пробежать >200м :(" : "Кот " + name +
                " пробежал(а) " + length + "м";
        System.out.println(result + "\n");
    }

    @Override
    public void swim(int length) {
        System.out.printf("Кот " + name + " не может проплыть %dм, он не умеет плавать\n\n", length);
    }

    /**
     * Метод кормит котов
     */
    public void eat(Bowl bowl, int foodQuantity) {
        if (bowl.getFoodQuantity() >= foodQuantity && !this.satiety) {
            bowl.deleteEat(foodQuantity);
            this.satiety = true;
            System.out.print(name + " ест" + " ");
        } else {
            System.out.println("Миска пуста, добавляю 10 вискаса");
            bowl.addEat(10);
        }
        System.out.println();
    }

    public static void eat(Cat[] cats, Bowl bowl, int foodQuantity) {
        for (Cat cat : cats) {
            cat.eat(bowl, foodQuantity);
        }
    }

    public void infoSatietyCat() {
        if (satiety) {
            System.out.println("Кот " + name + " наелся :)");
        } else {
            System.out.println("Кот " + name + " голоден :(");
            System.out.println();
        }
    }

    public void infoSatietyCat(Cat[] cats) {
        for (Cat cat : cats) {
            cat.infoSatietyCat();
        }
    }
}
