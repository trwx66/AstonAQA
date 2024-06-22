package org.example.tasksecond;

public class Rectangle extends AbstractFigure {
    private final float side1;
    private final float side2;

    public Rectangle(String fillColor, String borderColor, float side1, float side2) {
        super(fillColor, borderColor);
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public float figureArea() {
        return side1 * side2;
    }

    @Override
    public float figurePerimeter() {
        return 2 * (side1 + side2);
    }

    @Override
    public String toString() {
        return "Rectangle:" + super.toString();
    }
}
