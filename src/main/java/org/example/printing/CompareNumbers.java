/**
 * Если конструктор без параметров a и b рандомные (0-10)
 * Поля a и b неизменяемые (final)
 * @author PavelZgera
 * @version 1.0
 * @since 2024-06-18
 */

package org.example.printing;

public class CompareNumbers {
    private final int a;
    private final int b;

    public CompareNumbers() {
        this.a = (int) (Math.random() * 10) + 1;
        this.b = (int) (Math.random() * 10) + 1;
    }

    public CompareNumbers(int a, int b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Инициализируем 2 числа a и b любым значением. Если a>=b, то выводим a>=b, иначе a < b
     */
    public void compareNumbers() {
        System.out.printf("a = %d и b = %d\n", a, b);
        String result = a >= b ? "a >= b" : "a < b";
        System.out.println(result + "\n");
    }
}