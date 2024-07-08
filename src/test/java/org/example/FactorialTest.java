package org.example;

import org.testng.annotations.*;
import java.math.BigInteger;
import static org.testng.Assert.*;

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

    @DataProvider(name = "factorialData")
    public Object[][] createFactorialData() {
        return new Object[][] {
                {0, BigInteger.ONE},
                {1, BigInteger.ONE},
                {5, BigInteger.valueOf(120)},
                {20, new BigInteger("2432902008176640000")}
        };
    }

    @Test(dataProvider = "factorialData", priority = 1)
    public void testFactorial(int input, BigInteger expected) {
        System.out.printf("Запускаю testFactorial%nФакториал %d = %d%n%n", input, factorial.getFactorial(input));
        assertEquals(factorial.getFactorial(input), expected, "Факториал " + input);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, priority = 2)
    public void testFactorialOfNegativeNumber() {
        System.out.println("Запускаю testFactorialOfNegativeNumber");
        factorial.getFactorial(-1);
    }
}
