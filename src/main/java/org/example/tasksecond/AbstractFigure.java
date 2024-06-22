package org.example.tasksecond;

public abstract class AbstractFigure implements Calculations {
    private final String fillColor;
    private final String borderColor;

    protected AbstractFigure(String fillColor, String borderColor) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public String toString() {
        return " Периметр = " + figureArea() + ", Площадь = "
                + figurePerimeter() + ", Цвет фона: " + getFillColor() + ", Цвет границ: " + getBorderColor() + ".";
    }
}
