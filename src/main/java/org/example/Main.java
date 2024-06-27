package org.example;

public class Main {
    public static void main(String[] args) {

        String[][] correctArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        String[][] incorrectSizeArrayRow = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15"}
        };
        String[][] incorrectSizeArrayColumn = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
        };
        String[][] incorrectDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "1qwe6"}
        };
        //подробнее в README.md
        try {
            System.out.println("Сумма всех элементов массива correctArray = " + MyArray.processArray(correctArray));
            System.out.println("Сумма всех элементов массива incorrectSizeArrayRow = " + MyArray.processArray(incorrectSizeArrayRow));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Сумма всех элементов массива incorrectDataArray = " + MyArray.processArray(incorrectDataArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Сумма всех элементов массива incorrectSizeArrayColumn = " + MyArray.processArray(incorrectSizeArrayColumn));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }
}

