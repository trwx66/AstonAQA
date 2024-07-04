package org.example;

public class Main {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        int number = 10;
        System.out.printf("Факториал %d = %d%n", number, factorial.getFactorial(number));
    }
}

