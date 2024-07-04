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
        System.out.println("Запускаю testFactorialOfZero");
        System.out.println("Факториал 0 = " + factorial.getFactorial(0));
        Assert.assertEquals(factorial.getFactorial(0), BigInteger.ONE, "Факториал 0");
    }

    @Test(priority = 2)
    public void testFactorialOfOne() {
        System.out.println("Запускаю testFactorialOfOne");
        System.out.println("Факториал 1 = " + factorial.getFactorial(1));
        Assert.assertEquals(factorial.getFactorial(1), BigInteger.ONE, "Факториал 1");
    }

    @Test(priority = 3)
    public void testFactorialOfPositiveNumber() {
        System.out.println("Запускаю testFactorialOfPositiveNumber");
        System.out.println("Факториал 5 = " + factorial.getFactorial(5));
        Assert.assertEquals(factorial.getFactorial(5), new BigInteger("120"), "Факториал 5");
    }

    @Test(priority = 4)
    public void testFactorialOfLargeNumber() {
        System.out.println("Запускаю testFactorialOfLargeNumber");
        System.out.println("Факториал 20 = " + factorial.getFactorial(20));
        Assert.assertEquals(factorial.getFactorial(20), new BigInteger("2432902008176640000"), "Факториал 20");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialOfNegativeNumber() {
        System.out.println("Запускаю testFactorialOfNegativeNumber");
        factorial.getFactorial(-1);
    }
}
