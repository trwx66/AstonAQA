package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhoneBook {
    private final HashMap<String, List<String>> phoneList;

    public PhoneBook() {
        this.phoneList = new HashMap<>();
    }

    /**
     * Метод для добавления записи в телефонный справочник
     */
    public void add(String lastName, String phoneNumber) {
        if (phoneList.containsKey(lastName)) {
            phoneList.get(lastName).add(phoneNumber);
        } else {
            List<String> listNumber = new ArrayList<>();
            listNumber.add(phoneNumber);
            phoneList.put(lastName, listNumber);
        }
    }

    public List<String> get(String lastName) {
        if (phoneList.containsKey(lastName)) {
            return phoneList.get(lastName);
        } else {
            System.out.println("По данной фамилии в базе нет номеров");
            return new ArrayList<>();
        }

    }
}

