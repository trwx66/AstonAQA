package org.example.tasksecond;

public class Triangle extends AbstractFigure {
    private float side1;
    private float side2;
    private float side3;

    public Triangle(String fillColor, String borderColor, float side1, float side2, float side3) {
        super(fillColor, borderColor);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public Triangle(String fillColor, String borderColor) {
        super(fillColor, borderColor);

    }

    @Override
    public float figureArea() {
        float semiPerimeter = (side1 + side2 + side3) / 2;
        return (float) Math.sqrt(semiPerimeter * (semiPerimeter - side1)
                * (semiPerimeter - side2) * (semiPerimeter - side3));
    }

    @Override
    public float figurePerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public String toString() {
        return "Triangle :" + super.toString();
    }
}
