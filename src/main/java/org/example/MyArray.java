package org.example;

public class MyArray {

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        // Проверка на количество строк
        if (array.length != 4) {
            throw new MyArraySizeException("Ошибка размера строки в массиве. " +
                    "Массив должен быть 4x4. Найдено строк: " + array.length);
        }
        // Проверка на количество столбцов в каждой строке
        for (String[] column : array) {
            if (column.length != 4) {
                throw new MyArraySizeException("Ошибка размера столбца в массиве. Массив должен быть 4x4. Найденные столбцы в строке: " + column.length);
            }
        }
        // Итерация по массиву и суммирование элементов
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format
                            ("Ошибка данных. Преобразование не удалось. Строка i%d, столбец j%d, значение: %s", i, j, array[i][j]));
                }
            }
        }
        return sum;
    }
}
