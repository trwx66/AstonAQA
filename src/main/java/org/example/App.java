package org.example;


public class App {
    public static void main(String[] args) {

        Cat cat1 = new Cat("Том");
        Dog dog1 = new Dog("Бобик");
        Bowl bowl1 = new Bowl(20);
        Cat[] cats = {
                new Cat("Симба"),
                new Cat("Леопольд"),
                cat1
        };

        Animal.printCount();
        Cat.printCount();
        Dog.printCount();

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


    }
}
