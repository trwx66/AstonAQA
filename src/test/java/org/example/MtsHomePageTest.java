package org.example;

import org.example.base.TestsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsHomePageTest extends TestsConfig {

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        assertTrue(mtsHomePage.isBlockTitleDisplayed(), "Заголовок блока не отображается или не соответствует ожидаемому значению.");
        System.out.println("Тест \"Проверка названия блока 'Онлайн пополнение без комиссии'\" - выполнен\n");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentLogos() {
        assertTrue(mtsHomePage.arePaymentLogosDisplayed(), "Не все логотипы платежных систем отображаются");
        System.out.println("Тест \"Проверка наличия логотипов платежных систем\" - выполнен\n");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreInfoLink() {
        mtsHomePage.clickMoreInfoLink();
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Ссылка 'Подробнее о сервисе' не работает.");
        System.out.println("Тест \"Проверка работы ссылки 'Подробнее о сервисе'\" - выполнен\n");
    }

    @ParameterizedTest
    @CsvSource({
            "297777777, 499, test@example.com",
            "298888888, 250, example@test.com",
            "292323322, 1, user@domain.com"
    })
    @DisplayName("Заполнение формы и проверка кнопки 'Продолжить'")
    public void testFormSubmission(String phoneNumber, String amount, String email) {
        assertTrue(mtsHomePage.fillFormAndSubmit(phoneNumber, amount, email),
                "Фрейм не появился. Вы ввели некорректные данные в блок 'Онлайн пополнение без комиссии'");
        System.out.printf("Тест 'Заполнение формы и проверка кнопки 'Продолжить' - выполнен%nДанные : %s, %s, %s%n%n", phoneNumber, amount, email);
    }
}
