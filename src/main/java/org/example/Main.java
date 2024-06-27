package org.example;

import org.example.taskfirst.Animal;
import org.example.taskfirst.Bowl;
import org.example.taskfirst.Cat;
import org.example.taskfirst.Dog;
import org.example.tasksecond.Circle;
import org.example.tasksecond.Rectangle;
import org.example.tasksecond.Triangle;

public class Main {
    public static void main(String[] args) {

        System.out.println("Задание №1\n");

        Cat cat1 = new Cat("Том");
        Dog dog1 = new Dog("Бобик");
        Bowl bowl1 = new Bowl(20);

        Cat[] cats = {
                new Cat("Симба"),
                new Cat("Леопольд"),
                cat1
        };

        cat1.run(50);
        cat1.run(201);
        dog1.run(50);
        dog1.run(501);

        cat1.swim(50);
        dog1.swim(2);
        dog1.swim(11);

        Cat.eat(cats, bowl1, 10);
        bowl1.addEat(10);
        cat1.infoSatietyCat(cats);
        bowl1.addEat(20);
        cats[2].eat(bowl1, 15);
        cat1.infoSatietyCat();

        System.out.printf("%nВсего создано животных: %d%n" +
                        "Всего создано котов: %d%n" +
                        "Всего создано собак: %d%n%n",
                Animal.getCountAnimal(), Cat.getCountCat(), Dog.getCountDog());

        System.out.println("Задание №2\n");

        Circle circle = new Circle("Красный", "Синий", 14.2f);
        Rectangle rectangle = new Rectangle("Желтый", "Белый", 10f, 20f);
        Triangle triangle = new Triangle("Фиолетовый", "Серый", 3f, 4f, 5f);

        System.out.println(circle + "\n" + rectangle + "\n" + triangle);
    }
}
