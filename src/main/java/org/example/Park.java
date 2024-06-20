package org.example;

import java.util.ArrayList;

public class Park {

    public class Attraction {

        private String name;
        private String workTime;
        private int price;

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

        public void infoAttractionList(ArrayList<Park.Attraction> attractionList) {
            for (Attraction arr : attractionList) {
                arr.infoAttraction();
            }

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
