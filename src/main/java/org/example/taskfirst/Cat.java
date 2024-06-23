package org.example.taskfirst;

public class Cat extends Animal {
    private static int countCat = 0;
    private static final int RUN_LIMIT = 200;
    private boolean satiety;

    public Cat(String catName) {
        super(catName);
        this.satiety = false;
        countCat++;
    }

    public static int getCountCat() {
        return countCat;
    }

    @Override
    public void run(int length) {
        String result = length > RUN_LIMIT ? "Кот " + getName() + " не может пробежать >200м :(" : "Кот " + getName() +
                " пробежал(а) " + length + "м";
        System.out.println(result + "\n");
    }

    @Override
    public void swim(int length) {
        System.out.printf("Кот " + getName() + " не может проплыть %dм, он не умеет плавать%n%n", length);
    }

    /**
     * Метод кормит котов
     */
    public void eat(Bowl bowl, int foodQuantity) {
        if (bowl.getFoodQuantity() >= foodQuantity && !this.satiety) {
            bowl.deleteEat(foodQuantity);
            this.satiety = true;
            System.out.print(getName() + " ест" + " ");
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
            System.out.println("Кот " + getName() + " наелся :)");
        } else {
            System.out.println("Кот " + getName() + " голоден :(");
            System.out.println();
        }
    }

    public void infoSatietyCat(Cat[] cats) {
        for (Cat cat : cats) {
            cat.infoSatietyCat();
        }
    }

}
