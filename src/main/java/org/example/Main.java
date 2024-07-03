package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        System.out.println("Задание №1\n");

        String string = "на дворе трава на траве дрова не руби дрова на траве двора";
        String[] array = string.split(" ");
        System.out.println("Начальный массив " + Arrays.toString(array) + "\n");

        Map<String, Integer> uniqueWordsCounter = new HashMap<>();
        for (String word : array) {
            if (uniqueWordsCounter.containsKey(word)) {
                uniqueWordsCounter.put(word, uniqueWordsCounter.get(word) + 1);
            } else uniqueWordsCounter.put(word, 1);
        }

        Set<String> uniqueWords = uniqueWordsCounter.keySet();
        System.out.println("Список уникальных слов " + uniqueWords + "\n");

        for (Map.Entry<String, Integer> entry : uniqueWordsCounter.entrySet()) {
            System.out.printf("Слово \"%s\" повторяется %d раз(а) \n", entry.getKey(), entry.getValue());
        }

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

