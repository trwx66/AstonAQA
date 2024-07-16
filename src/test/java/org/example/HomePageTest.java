package org.example;

import org.example.base.TestsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends TestsConfig {

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        assertTrue(homePage.isBlockTitleDisplayed(), "Заголовок блока не отображается или не соответствует ожидаемому значению.");
        System.out.println("Тест \"Проверить название указанного блока\" - выполнен");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentLogos() {
        assertTrue(homePage.arePaymentLogosDisplayed(), "Не все логотипы платежных систем отображаются");
        System.out.println("Тест \"Проверить наличие логотипов платёжных систем\" - выполнен");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreInfoLink() {
        homePage.clickMoreInfoLink();
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Ссылка 'Подробнее о сервисе' не работает.");
        System.out.println("Тест \"Проверить работу ссылки «Подробнее о сервисе»\" - выполнен");
    }

    @Test
    @DisplayName("Заполняем форму и проверяем работу кнопки \"Продолжить\"")
    public void testFormSubmission() {
        assertTrue(homePage.fillFormAndSubmit("297777777", "499", "test@example.com"));
        System.out.println("Тест \"Заполнить поля и проверить работу кнопки «Продолжить»\" - выполнен");
    }
}
