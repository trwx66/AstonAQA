package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class MtsTests {
    private static WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://mts.by");
        WebElement acceptCookiesButton = driver.findElement(By.xpath("//button[contains(@class, 'btn_black') and text()='Принять']"));
        acceptCookiesButton.click();
        System.out.println("Cookies приняты.");
    }

    @AfterAll
    static void afterAll() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    void testBlockTitle() {
        assertEquals(driver.findElement
                        (By.xpath("//div[@class='pay__wrapper']/h2")).getText(),
                "Онлайн пополнение\nбез комиссии",
                "Название блока не соответствует ожидаемому.");
        System.out.println("Заголовок блока соответствует ожидаемому значению.");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentSystemLogos() {
        List<WebElement> icons = driver.findElements(By.xpath("//div[@class='pay__partners']/ul"));
        icons.forEach(icon -> assertTrue(icon.isDisplayed(), "Иконка не отображается"));
        System.out.println("Все иконки платежных систем успешно отображены.");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testLearnMoreLink() {
        WebElement learnMoreLink = driver.findElement(By.xpath("//a[text()='Подробнее о сервисе']"));
        assertNotNull(learnMoreLink, "Ссылка 'Подробнее о сервисе' не найдена");
        learnMoreLink.click();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl(), "URL после перехода по ссылке не совпадает с ожидаемым");
        driver.navigate().back();
    }
}

