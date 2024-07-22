package org.example.base;

import org.example.pages.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class TestsConfig {
    protected static WebDriver driver;
    protected static HomePage homePage;
    private static final String URL = "http://mts.by";

    @BeforeAll
    public static void setUp() {
        initializeDriver();
        initializePage();
    }

    private static void initializeDriver() {
        driver = new ChromeDriver
                (new ChromeOptions().addArguments("--headless"));
        driver.manage().window().maximize();
    }

    private static void initializePage() {
        driver.get(URL);
        homePage = new HomePage(driver);
        homePage.acceptCookies();
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("--------------------------------------------------");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
