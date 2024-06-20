package org.example.printing;

import java.util.Random;

/**
 * @author PavelZgera
 * @version 1.0
 * @since 2024-06-18
 */

public class PrintColor {
    private int value;

    public PrintColor() {
        this.value = new Random().nextInt(301) - 100;
    }

    public PrintColor(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Если value <= 0 - Красный, если value > 0 и value <= 100 - Жёлтый, иначе Зелёный")
     */
    public void printColor() {
        System.out.printf("Value = %d : ", value);
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Жёлтый");
        } else {
            System.out.println("Зелёный");
        }
        System.out.println();
    }
}