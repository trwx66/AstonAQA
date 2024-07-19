package org.example.base;


import org.example.pages.MtsHomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class TestsConfig {

    protected WebDriver driver;
    protected MtsHomePage mtsHomePage;
    private static final String URL = "http://mts.by";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        mtsHomePage = new MtsHomePage(driver);
        mtsHomePage.acceptCookies();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}