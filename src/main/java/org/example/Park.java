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

        @Override
        public String toString() {
            return String.format(
                    "Аттракцион: %s%nВремя работы: %s%nСтоимость: %d%n",
                    name, workTime, price);
        }

        /**
         * Метод для вывода коллекции с информацией об аттракционах в консоль
         */

        public static void infoAttractionList(List<Park.Attraction> attractionList) {
            for (Attraction arr : attractionList) {
                System.out.println(arr);
            }
        }
    }
}
