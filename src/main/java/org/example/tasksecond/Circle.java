package org.example.tasksecond;

public class Circle extends AbstractFigure {
    private float radius;

    public Circle(String fillColor, String borderColor, float radius) {
        super(fillColor, borderColor);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public float figureArea() {
        return (float) (Math.PI * (radius * 2));
    }

    @Override
    public float figurePerimeter() {
        return (float) (Math.PI * radius);
    }

    @Override
    public String toString() {
        return "Circle:" + super.toString();
    }
}
