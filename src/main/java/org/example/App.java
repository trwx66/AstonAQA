package org.example;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        Person person1 = new Person("Згера Павел",
                "Инженер", "test@gmail.com", "+375 44 555-55-55", 150_000, 32);
        person1.printInfo();

        Person[] arrayPerson = new Person[5];
        arrayPerson[0] = person1;
        arrayPerson[1] = new Person("Иванов Иван",
                "Водитель", "test1@gmail.com", "+375 44 555-55-54", 50_000, 43);
        arrayPerson[2] = new Person("Гулик Игорь",
                "Повар", "test2@gmail.com", "+375 44 555-55-53", 45_000, 34);
        arrayPerson[3] = new Person("Рыбин Николай",
                "Продавец", "test3@gmail.com", "+375 44 555-55-52", 40_000, 39);
        arrayPerson[4] = new Person("Фурц Татьяна",
                "Программист", "test4@gmail.com", "+375 44 555-55-51", 250_000, 31);
        person1.printInfoArray(arrayPerson);

        Park park = new Park();
        ArrayList<Park.Attraction> attractionList = new ArrayList<>();
        attractionList.add(park.new Attraction("Комната смеха", "09:00 - 20:00", 15));
        attractionList.add(park.new Attraction("Детский поезд", "09:00 - 21:00", 10));
        Park.Attraction attraction = park.new Attraction("Надувные горки", "010:00 - 21:00", 15);
        attractionList.add(attraction);
        attraction.infoAttractionList(attractionList);
    }
}
