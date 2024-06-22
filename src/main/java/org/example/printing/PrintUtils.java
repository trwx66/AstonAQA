/**
 * Утилити класс (содержит static методы), которые не зависят от состояния объекта.
 */

package org.example.printing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PrintUtils {

    /**
     * Метод printThreeWords печатает три слова в столбик: Orange, Banana, Apple
     */
    public static void printThreeWords() {
        ArrayList<String> fruitList = new ArrayList<>(Arrays.asList("Orange", "Banana", "Apple"));
        for (String word : fruitList) {
            System.out.println(word);
        }
        System.out.println();
    }

    /**
     * Выводим строку str - count раз
     */
    public static void printStringMultiple(String str, int count) {
        System.out.printf("Выводим строку %s %d раз(а):\n", str, count);
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
        System.out.println();
    }

    /**
     * Метод checkSumSign() вводит 2 числа(проверяет целое ли число ввели),
     * если сумма num1 + num2 >= 0 выводим "Сумма положительная",
     * иначе Сумма отрицательная.
     * Проверки на int\double и т.д можно было вынести отдельным
     * классом и использовать в каждом методе где надо проверить число, но я не стал
     */
    public static void checkSumSign() {

        Scanner keyboard = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;
        boolean validA = false;
        boolean validB = false;

        while (!validA) {
            System.out.println("Введите первое целое число ");
            String inputA = keyboard.nextLine();
            try {
                num1 = Integer.parseInt(inputA);
                validA = true;
            } catch (NumberFormatException e) {
                System.out.println("Введённое значение не является целым числом.");
            }
        }
        while (!validB) {
            System.out.println("Введите второе целое число ");
            String inputA = keyboard.nextLine();
            try {
                num2 = Integer.parseInt(inputA);
                validB = true;
            } catch (NumberFormatException e) {
                System.out.println("Введённое значение не является целым числом.");
            }
        }
        String result = num1 + num2 >= 0 ? "Сумма положительная" : "Сумма отрицательная";
        System.out.println(result + "\n");
        keyboard.close();
    }

    /**
     * Метод isSumInRange() принимает 2 числа и проверяет, находится ли сумма двух целых чисел в диапазоне от 10 до 20 включительно.
     * Проверки на int нет. Можно сделать по аналогии как в методе checkSumSign() в классе CheckSumSign.
     * @param num1 первое число.
     * @param num2 второе число.
     * @return true, если сумма 2-х чисел находится в диапазоне от 10 до 20 включительно, иначе false.
     */

    public static boolean isSumInRange(int num1, int num2) {
        int sum = num1 + num2;
        System.out.printf("num1 = %d; num2 = %d; сумма = %d. Результат : ", num1, num2, sum);
        return (sum >= 10) && (sum <= 20);
    }

    /**
     * Метод checkNumber() принимает целое число. Если num >= 0 - вывод число положительное, иначе число отрицательное.
     */
    public static void checkNumber(int num) {
        String result = num >= 0 ? num + " - число положительное" : num + " - число отрицательное";
        System.out.println(result + "\n");
    }

    /**
     * Метод checkTrueFalse() принимает целое число num и возвращает false если num >= 0, иначе true
     */
    public static boolean checkTrueFalse(int num) {
        System.out.printf("Число равно %d . Метод возвращает ", num);
        return num >= 0 ? false : true;
    }

    /**
     * Метод создаёт массив {1, 1, 0, 0, 1, 0, 1, 1, 0, 0} и меняет значения 1 на 0 и наоборот.
     */
    public static void swapZeroAndOne() {
        int[] arrayNumbers = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Текущий массив: " + Arrays.toString(arrayNumbers));
        for (int i = 0; i < arrayNumbers.length; i++) {
            arrayNumbers[i] = arrayNumbers[i] == 1 ? 0 : 1;
        }
        System.out.println("Итоговый массив: " + Arrays.toString(arrayNumbers) + "\n");
    }

    /**
     * Метод создаёт массив из 100 целых чисел, заполняет его от 1 до 100 и выводит его на экран.
     */
    public static void arrayOneHundred() {
        int[] arrayNumbers = new int[100];
        for (int i = 0; i < arrayNumbers.length; i++) {
            arrayNumbers[i] = i + 1;
        }
        System.out.println(Arrays.toString(arrayNumbers) + "\n");
    }

    /**
     * Метод создаёт массив {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1}, умножает числа меньше 6 на 2.
     */
    public static void lessSixTimesTwo() {
        int[] arrayNumbers = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Текущий массив: " + Arrays.toString(arrayNumbers));
        for (int i = 0; i < arrayNumbers.length; i++) {
            if (arrayNumbers[i] < 6) {
                arrayNumbers[i] *= 2;
            }
        }
        System.out.println("Итоговый массив: " + Arrays.toString(arrayNumbers) + "\n");
    }

    /**
     * Метод двумерный массив 6на6, заполняет диагонали единицами и выводит на экран квадрат.
     */

    public static void squareDiagonals() {
        int[][] arraySquare = new int[6][6];
        for (int i = 0; i < arraySquare.length; i++) {
            arraySquare[i][i] = 1;
        }
        for (int j = 0; j < arraySquare.length; j++) {
            arraySquare[j][arraySquare.length - (j + 1)] = 1;
        }
        for (int[] ints : arraySquare) {
            for (int j = 0; j < arraySquare.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Метод создаёт одномерный массив длинной len и заполняет значениями initialValue.
     * @param len длинна массива.
     * @param initialValue значения в массиве.
     * @return одномерный массив длинной len, значения initialValue.
     */

    public static int[] arrayCreate(int len, int initialValue) {
        int[] arrayOne = new int[len];
        Arrays.fill(arrayOne, initialValue);
        return arrayOne;
    }
}