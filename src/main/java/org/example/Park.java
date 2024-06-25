package org.example;

import java.util.ArrayList;
import java.util.List;

public class Park {

    private static class Attraction {

        private final String name;
        private final String workTime;
        private final int price;

        public Attraction(String name, String workTime, int price) {
            this.name = name;
            this.workTime = workTime;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format(
                    "Аттракцион: %s%nВремя работы: %s%nСтоимость: %d%n",
                    name, workTime, price);
        }
    }

    private final List<Attraction> attractions;

    public Park() {
        this.attractions = new ArrayList<>();
    }


    /**
     * Метод для добавления аттракционов в коллекцию
     */

    public void addAttraction(String name, String workTime, int price) {
        Attraction attraction = new Attraction(name, workTime, price);
        attractions.add(attraction);
    }

    /**
     * Метод для вывода коллекции с информацией об аттракционах в консоль
     */

    public void infoAttractionList() {
        for (Attraction attraction : attractions) {
            System.out.println(attraction);
        }
    }
}

