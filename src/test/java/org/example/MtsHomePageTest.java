package org.example;

import org.example.base.TestsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsHomePageTest extends TestsConfig {

    private static final Logger logger = LoggerFactory.getLogger(MtsHomePageTest.class);

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void shouldDisplayCorrectBlockTitle() {
        assertThat(mtsHomePage.checkBlockTitleDisplayed())
                .as("Заголовок блока не отображается или не соответствует ожидаемому значению.")
                .isEqualTo("Онлайн пополнение\nбез комиссии");
        logger.info("Тест \"Проверка названия блока 'Онлайн пополнение без комиссии'\" - выполнен");
    }


    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void shouldDisplayAllPaymentLogos() {
        assertThat(mtsHomePage.checkPaymentLogosDisplayed())
                .as("Не все логотипы платежных систем отображаются")
                .isTrue();
        logger.info("Тест \"Проверка наличия логотипов платежных систем\" - выполнен");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void shouldOpenMoreInfoLink() {

        assertAll("Проверка ссылки и заголовка",
                () -> {
                    assertThat(mtsHomePage.clickMoreInfoLink())
                            .as("Ссылка 'Подробнее о сервисе' не работает.")
                            .isEqualTo("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
                    logger.info("Тест \"Проверка работы ссылки 'Подробнее о сервисе'\" - выполнен");
                },
                () -> {
                    assertThat(mtsHomePage.paymentCardHeader.getText())
                            .as("Заголовок не соответствует ожидаемому")
                            .isEqualTo("Оплата банковской картой");
                    logger.info("Тест \"Проверка заголовка на странице 'Подробнее о сервисе'\" - выполнен");
                });

    }

    @ParameterizedTest
    @CsvSource({
            "297777777, 499, test@example.com",
            "298888888, 250, example@test.com",
            "292323322, 1, user@domain.com"
    })
    @DisplayName("Заполнение формы и проверка кнопки 'Продолжить'")
    public void shouldSubmitFormAndDisplayIframe(String phoneNumber, String amount, String email) {
        assertTrue(mtsHomePage.fillFormAndSubmit(phoneNumber, amount, email),
                "Фрейм не появился. Вы ввели некорректные данные в блок 'Онлайн пополнение без комиссии'");
        logger.info("Тест \"Заполнение формы и проверка кнопки 'Продолжить'\" - выполнен. Данные: {}, {}, {}", phoneNumber, amount, email);
    }

    @Test
    public void test() {
        mtsHomePage.clickMoreInfoLink();
    }
}
