package org.example.tasksecond;

public abstract class AbstractFigure implements Calculations {
    private String fillColor;
    private String borderColor;

    public AbstractFigure(String fillColor, String borderColor) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public String toString() {
        return " Периметр = " + figureArea() + ", Площадь = "
                + figurePerimeter() + ", Цвет фона: " + getFillColor() + ", Цвет границ: " + getBorderColor() + ".";
    }
}
