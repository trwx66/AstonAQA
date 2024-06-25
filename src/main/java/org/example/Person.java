package org.example;

public class Person {
    private final String fullName;
    private final String position;
    private final String email;
    private final String phone;
    private final float salary;
    private final int age;

    public Person(String fullName, String position, String email, String phone, float salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format(
                "ФИО: %s%nДолжность: %s%nEmail: %s%nТелефон: %s%nЗарплата: %.2f%nВозраст: %d%n",
                fullName, position, email, phone, salary, age
        );
    }

    /**
     * Метод для вывода массива с информацией о сотрудниках в консоль
     */
    public static void printInfoArray(Person[] personArr) {
        for (Person arr : personArr) {
            System.out.println(arr);
        }
    }
}
