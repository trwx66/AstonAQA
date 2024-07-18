package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.MtsHomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class TestsConfig {

    protected WebDriver driver;
    protected MtsHomePage mtsHomePage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get("http://mts.by");
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