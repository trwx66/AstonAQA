package org.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Задание №1\n");

        String string = "на дворе трава на траве дрова не руби дрова на траве двора";
        WordCounter wordCounter = new WordCounter(string);

        wordCounter.processInput();

        System.out.println("Начальный массив " + Arrays.toString(wordCounter.getWordArray()) + "\n");
        System.out.println("Список уникальных слов " + wordCounter.getUniqueWords() + "\n");
        wordCounter.printWordCounts();

        System.out.println("\nЗадание №2\n");

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Пупкин", "375 44 123 12 12");
        phoneBook.add("Пупкин", "375 29 123 23 23");
        phoneBook.add("Ефимова", "375 29 999 88 77");

        System.out.println(phoneBook.get("Пупкин"));
        System.out.println(phoneBook.get("Смирнов"));
        System.out.println(phoneBook.get("Ефимова"));
    }
}

