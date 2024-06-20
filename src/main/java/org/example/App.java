package org.example;

import org.example.printing.CompareNumbers;
import org.example.printing.PrintColor;
import org.example.printing.PrintLeapYear;
import org.example.printing.PrintUtils;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        System.out.println("Задание №1. Печатать три слова: Orange, Banana, Apple");

        PrintUtils.printThreeWords();

        System.out.println("Задание №2. Ввести 2 целых числа(есть проверка на int) c клавиатуры (num1 и num2)." +
                " Если num1+num2>=0: вывод \"Сумма положительная\", иначе \"Сумма отрицательная\"");

        PrintUtils.checkSumSign();

        System.out.println("Задание №3. Инициализируем value. " +
                "Если value <= 0 - Красный, если value > 0 и value <= 100 - Жёлтый, иначе Зелёный");

        PrintColor color = new PrintColor(); //random value
        color.printColor();
        color.setValue(50);
        color.printColor();
        PrintColor colorTwo = new PrintColor(-100);
        colorTwo.printColor();

        System.out.println("Задание №4. Инициализируем 2 числа a и b любым значением." +
                " Если a>=b, то выводим a>=b, иначе a < b");
        CompareNumbers numbers = new CompareNumbers();
        numbers.compareNumbers();

        System.out.println("Задание №5. Если сумма 2х целых чисел в диапазоне >= 10 и <= 20 - выводим true, иначе false");
        System.out.println(PrintUtils.isSumInRange(10, 2) + "\n");

        System.out.println("Задание №6. Передать целое число. Если num >= 0 - вывод число положительное," +
                " иначе число отрицательное.");
        PrintUtils.checkNumber(-15);

        System.out.println("Задание №7. Передать целое число. Если num >= 0 вернёт false, иначе true.");
        System.out.println(PrintUtils.checkTrueFalse(-10) + "\n");

        System.out.println("Задание №8. Передать строку и число. Вывести в консоль указанную строку, указанное количество раз.");
        PrintUtils.printStringMultiple("QA Engineer Java", 3);

        System.out.println("Задание №9. Високосный год или нет, true(високосный)/false(невисокосный)");
        PrintLeapYear year1 = new PrintLeapYear(); //конструктор без полей = текущий год
        year1.printLeapOrNo();
        year1.setYear(2025);
        year1.printLeapOrNo();
        System.out.println();

        System.out.println("Задание №10. Массив [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. Заменить 1 на 0, а 0 на 1");
        PrintUtils.swapZeroAndOne();

        System.out.println("Задание №11. Заполнить целочисленный массив от 1 до 100");
        PrintUtils.arrayOneHundred();

        System.out.println("Задание №12. Если элемент массива меньше 6, умножить его на 2");
        PrintUtils.lessSixTimesTwo();

        System.out.println("Задание №13. Создаём квадрат (двумерный массив 6на6), диагонали заполняем единицами и выводим");
        PrintUtils.squareDiagonals();

        System.out.println("Задание №14. Заполняем массив длинной len и заполняем значениями initialValue, выводим массив");
        System.out.println(Arrays.toString(PrintUtils.arrayCreate(5, 123)));
    }
}