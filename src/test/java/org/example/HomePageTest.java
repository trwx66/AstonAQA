package org.example;

import org.example.base.TestsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
            "297777777, 499, test@example.com",
            "298888888, 250, example@test.com",
            "292323322, 1, user@domain.com"
    })
    @DisplayName("Заполняем форму и проверяем работу кнопки \"Продолжить\"")
    public void testFormSubmission(String phoneNumber, String amount, String email) {
        assertTrue(homePage.fillFormAndSubmit(phoneNumber, amount, email),
                "Вы ввели некорректные данные в блок \"Онлайн пополнение без комиссии\"");
        System.out.println("Тест \"Заполнить поля и проверить работу кнопки «Продолжить»\" - выполнен для данных: "
                + phoneNumber + ", " + amount + ", " + email);
    }
}
