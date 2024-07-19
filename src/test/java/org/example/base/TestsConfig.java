package org.example.base;

import org.example.pages.MtsHomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class TestsConfig {
    protected static WebDriver driver;
    protected static MtsHomePage homePage;
    private static final String URL = "http://mts.by";

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        homePage = new MtsHomePage(driver);
        homePage.acceptCookies();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
