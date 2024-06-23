package org.example;

import java.util.List;

public class Park {

    public static class Attraction {

        private final String name;
        private final String workTime;
        private final int price;

        public Attraction(String name, String workTime, int price) {
            this.name = name;
            this.workTime = workTime;
            this.price = price;
        }

        /**
         * Метод для вывода информации об объекте в консоль
         */
        public void infoAttraction() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workTime);
            System.out.println("Стоимость: " + price);
            System.out.println();
        }

        /**
         * Метод для вывода коллекции с информацией об аттракционах в консоль
         */

        public static void infoAttractionList(List<Park.Attraction> attractionList) {
            for (Attraction arr : attractionList) {
                arr.infoAttraction();
            }
        }
    }
}
