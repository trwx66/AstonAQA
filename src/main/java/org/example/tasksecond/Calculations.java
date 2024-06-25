package org.example.tasksecond;

public interface Calculations {

    float figureArea();

    default float circlePerimeter(float radius) {
        return (float) (Math.PI * radius * 2);
    }

    default float rectanglePerimeter(float side1, float side2) {
        return 2 * (side1 + side2);
    }

    default float trianglePerimeter(float side1, float side2, float side3) {
        return side1 + side2 + side3;
    }
}
