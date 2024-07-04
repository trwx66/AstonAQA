package org.example;

import org.junit.jupiter.api.*;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    private Factorial factorial;

    @BeforeAll
    static void startMsg() {
        System.out.println("Начать тест");
    }

    @AfterAll
    static void endMsg() {
        System.out.println("Закончить тест");
    }

    @BeforeEach
    void beforeEachTest() {
        factorial = new Factorial();
    }

    @AfterEach
    void afterEachTest() {
        factorial = null;
    }

    @Test
    void testFactorial() {
        assertAll("Тесты факториала",
                () -> {
                    System.out.println("Факториал 0 = " + factorial.getFactorial(0));
                    assertEquals(BigInteger.ONE, factorial.getFactorial(0), "Факториал 0");
                },
                () ->
                {
                    System.out.println("Факториал 1 = " + factorial.getFactorial(1));
                    assertEquals(BigInteger.ONE, factorial.getFactorial(1), "Факториал 1");

                },
                () -> {
                    System.out.println("Факториал 5 = " + factorial.getFactorial(5));
                    assertEquals(BigInteger.valueOf(120), factorial.getFactorial(5), "Факториал 5");
                },
                () -> {
                    System.out.println("Факториал 20 = " + factorial.getFactorial(20));
                    assertEquals(new BigInteger("2432902008176640000"), factorial.getFactorial(20), "Факториал 20");
                },
                // если в методе getFactorial(int n) не бросить исключение IllegalArgumentException при n<0 (например сделать return BigInteger.ONE), тест не пройдёт
                () -> assertThrows(IllegalArgumentException.class, () -> factorial.getFactorial(-1), "Факториал -1")
        );
    }
}