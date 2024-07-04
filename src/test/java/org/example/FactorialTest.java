package org.example;

import org.testng.Assert;
import org.testng.annotations.*;

import java.math.BigInteger;

public class FactorialTest {
    private Factorial factorial;

    @BeforeClass
    public void setUpBeforeClass() {
        System.out.println("Начать тест");
    }

    @AfterClass
    public void tearDownAfterClass() {
        System.out.println("Закончить тест");
    }

    @BeforeMethod
    public void setUp() {
        factorial = new Factorial();
    }

    @AfterMethod
    public void tearDown() {
        factorial = null;
    }

    @Test(priority = 1)
    public void testFactorialOfZero() {
        System.out.printf("Запускаю testFactorialOfZero%nФакториал 0 = %d%n%n", factorial.getFactorial(0));
        Assert.assertEquals(factorial.getFactorial(0), BigInteger.ONE, "Факториал 0");
    }

    @Test(priority = 2)
    public void testFactorialOfOne() {
        System.out.printf("Запускаю testFactorialOfOne%nФакториал 1 = %d%n%n", factorial.getFactorial(1));
        Assert.assertEquals(factorial.getFactorial(1), BigInteger.ONE, "Факториал 1");
    }

    @Test(priority = 3)
    public void testFactorialOfPositiveNumber() {
        System.out.printf("Запускаю testFactorialOfPositiveNumber%nФакториал 5 = %d%n%n", factorial.getFactorial(5));
        Assert.assertEquals(factorial.getFactorial(5), BigInteger.valueOf(120), "Факториал 5");
    }

    @Test(priority = 4)
    public void testFactorialOfLargeNumber() {
        System.out.printf("Запускаю testFactorialOfLargeNumber%nФакториал 20 = %d%n%n", factorial.getFactorial(20));
        Assert.assertEquals(factorial.getFactorial(20), new BigInteger("2432902008176640000"), "Факториал 20");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, priority = 5)
    public void testFactorialOfNegativeNumber() {
        System.out.println("Запускаю testFactorialOfNegativeNumber");
        factorial.getFactorial(-1);
    }
}
