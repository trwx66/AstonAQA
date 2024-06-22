package org.example.taskfirst;

public class Bowl {
    private int foodQuantity;

    public Bowl(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Количество еды не может быть отрицательным");
        }
        this.foodQuantity = quantity;
    }

    /**
     * Метод добавляет еду в тарелку
     */
    public void addEat(int quantity) {
        this.foodQuantity += quantity;
    }

    /**
     * Метод удаляет еду из тарелки
     */
    public void deleteEat(int quantity) {
        if (quantity > 0 && this.foodQuantity >= quantity) {
            this.foodQuantity -= quantity;
        }
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }
}
