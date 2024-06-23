package org.example.tasksecond;

public class Main {
    public static void main(String[] args) {

        Circle circle = new Circle("Красный", "Синий", 14.2f);
        Rectangle rectangle = new Rectangle("Желтый", "Белый", 10f, 20f);
        Triangle triangle = new Triangle("Фиолетовый", "Серый", 3f, 4f, 5f);

        System.out.println(circle + "\n" + rectangle + "\n" + triangle);
    }
}
