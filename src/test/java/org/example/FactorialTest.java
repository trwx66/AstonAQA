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
                    System.out.println("Факториал 0 = " + factorial.calc(0));
                    assertEquals(BigInteger.ONE, factorial.calc(0), "Факториал 0");
                },
                () ->
                {
                    System.out.println("Факториал 1 = " + factorial.calc(1));
                    assertEquals(BigInteger.ONE, factorial.calc(1), "Факториал 1");

                },
                () -> {
                    System.out.println("Факториал 5 = " + factorial.calc(5));
                    assertEquals(BigInteger.valueOf(120), factorial.calc(5), "Факториал 5");
                },
                () -> {
                    System.out.println("Факториал 20 = " + factorial.calc(20));
                    assertEquals(new BigInteger("2432902008176640000"), factorial.calc(20), "Факториал 20");
                },
                // если в методе calc(int n) не бросить исключение IllegalArgumentException при n<0 (например сделать return BigInteger.ONE), тест не пройдёт
                () -> assertThrows(IllegalArgumentException.class, () -> factorial.calc(-1), "Факториал -1")
        );
    }
}