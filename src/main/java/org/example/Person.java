package org.example;

public class Person {
    private final String fullName;
    private final String position;
    private final String email;
    private final String phone;
    private final int salary;
    private final int age;

    public Person(String fullName, String position, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    /**
     * Метод для вывода информации об объекте в консоль
     */
    public void printInfo() {
        System.out.println("ФИО: " + fullName);
        System.out.println("Должность: " + position);
        System.out.println("Email: " + email);
        System.out.println("Телефон: " + phone);
        System.out.println("Зарплата: " + salary);
        System.out.println("Возраст: " + age);
        System.out.println();
    }

    /**
     * Метод для вывода массива с информацией о сотрудниках в консоль
     */
    public static void printInfoArray(Person[] personArr) {
        for (Person arr : personArr) {
            arr.printInfo();
        }
    }
}
