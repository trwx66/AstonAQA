/**
 * @author PavelZgera
 * @version 1.0
 * @since 2024-06-19
 */

package org.example.printing;

import java.time.LocalDate;

public class PrintLeapYear {
    private int year;

    public PrintLeapYear() {
        this.year = LocalDate.now().getYear(); //текущий год
    }

    public PrintLeapYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Метод возвращает true если год високосный и false если нет
     */
    public boolean leapOrNo() {
        if (this.year % 400 == 0) {
            return true;
        } else if (this.year % 100 == 0) {
            return false;
        } else if (this.year % 4 == 0) {
            return true;
        } else return false;
    }

    /**
     * Выводит в консоль год(високосный(true) или нет(false))
     */
    public void printLeapOrNo() {
        System.out.printf("Год %d високосный? %b\n", year, leapOrNo());

    }
}