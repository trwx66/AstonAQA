package org.example.tests;

import org.example.tests.base.TestsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HomePageTest extends TestsConfig {
    private static final String EXPECTED_LINK = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
    private static final Logger logger = LoggerFactory.getLogger(HomePageTest.class);

    @Test
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void shouldDisplayCorrectBlockTitle() {
        assertThat(homePage.checkBlockTitleDisplayed())
                .as("Заголовок блока не отображается или не соответствует ожидаемому значению.")
                .isEqualTo("Онлайн пополнение\nбез комиссии");
        logger.info("Тест \"Проверка названия блока 'Онлайн пополнение без комиссии'\" - выполнен");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void shouldDisplayAllPaymentLogos() {
        assertAll("Проверка отображения, наличия и кол-ва логотипов",
                () -> {
                    assertThat(homePage.checkPaymentLogosDisplayed())
                            .as("Не все логотипы платежных систем отображаются")
                            .isTrue();
                    logger.info("Тест \"Проверка наличия логотипов платежных систем\" - выполнен");
                },
                () -> {
                    assertThat(homePage.checkSizeLogos())
                            .as("Неверное кол-во логотипов в окне 'Онлайн пополнение\n" +
                                    "без комиссии'")
                            .isEqualTo(5);
                    logger.info("Тест \"Проверка кол-ва логотипов платежных систем\" - выполнен");
                },
                () -> {
                    assertThat(homePage.checkListLogos())
                            .as("Не все ожидаемые логотипы есть в списке\"")
                            .containsAll(ListExpectedLogos());
                    logger.info("Тест \"Проверка наличия логотипов\" - выполнен");
                }
        );
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void shouldOpenMoreInfoLink() {

        assertAll("Проверка ссылки и заголовка",
                () -> {
                    assertThat(homePage.clickMoreInfoLink())
                            .as("Ссылка 'Подробнее о сервисе' не работает.")
                            .isEqualTo(EXPECTED_LINK);
                    logger.info("Тест \"Проверка работы ссылки 'Подробнее о сервисе'\" - выполнен");
                },
                () -> {
                    assertThat(homePage.paymentCardHeader.getText())
                            .as("Заголовок не соответствует ожидаемому")
                            .isEqualTo("Оплата банковской картой");
                    logger.info("Тест \"Проверка заголовка на странице 'Подробнее о сервисе'\" - выполнен");
                });

    }

    @ParameterizedTest
    @CsvSource({
            "297777777, 499, test@example.com",
            "298888888, 250, example@test.com",
    })
    @DisplayName("Заполнение формы и проверка кнопки 'Продолжить'")
    public void shouldSubmitFormAndDisplayIframe(String phoneNumber, String amount, String email) {
        assertThat(homePage.fillFormAndSubmit(phoneNumber, amount, email))
                .as("Фрейм не появился. Вероятно вы ввели некорректные" +
                        " данные в блок 'Онлайн пополнение без комиссии'")
                        .isTrue();
        logger.info("Тест \"Заполнение формы и проверка кнопки 'Продолжить'\" - выполнен. Данные: {}, {}, {}", phoneNumber, amount, email);
    }

    private List<String> ListExpectedLogos() {
        return List.of("Visa", "MasterCard", "Verified By Visa", "MasterCard Secure Code", "Белкарт");
    }
}
