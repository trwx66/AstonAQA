package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.example.pages.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public abstract class TestsConfig {
    protected static WebDriver driver;
    protected static HomePage homePage;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://mts.by");
        homePage = new HomePage(driver);
        homePage.acceptCookies();
    }

    @AfterAll
    static void afterAll() {
        if (driver != null) {
            driver.quit();
        }
    }
}