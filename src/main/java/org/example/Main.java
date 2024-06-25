package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("Задание №1-2");

        Person person1 = new Person("Згера Павел",
                "Инженер", "test@gmail.com", "+375 44 555-55-55", 150_000, 32);

        Person[] arrayPerson = {
                person1,
                new Person("Иванов Иван", "Водитель", "test1@gmail.com", "+375 44 555-55-54", 50_000, 43),
                new Person("Гулик Игорь", "Повар", "test2@gmail.com", "+375 44 555-55-53", 45_000, 34),
                new Person("Рыбин Николай", "Продавец", "test3@gmail.com", "+375 44 555-55-52", 40_000, 39),
                new Person("Фурц Татьяна", "Программист", "test4@gmail.com", "+375 44 555-55-51", 250_000, 31)
        };
        Person.printInfoArray(arrayPerson);

        System.out.println("Задание №3");

        Park park = new Park();

        park.addAttraction("Комната смеха", "09:00 - 20:00", 15);
        park.addAttraction("Детский поезд", "09:00 - 21:00", 10);
        park.addAttraction("Колесо обозрения", "09:00 - 20:30", 7);

        park.infoAttractionList();
    }
}
