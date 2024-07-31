package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Epic("Тесты для \"Онлайн пополнение без комиссии\" на сайте mts.by")
public class HomePageTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(HomePageTest.class);

    Stream<Arguments> placeholderDataProvider() {
        return homePage.getPlaceholderData();
    }

    Stream<Arguments> iFrameFormData() {
        return homePage.getIframeFormData();
    }

    Stream<Arguments> iframeLabel() {
        return homePage.getIframeLabel();
    }

    @ParameterizedTest
    @MethodSource("placeholderDataProvider")
    @DisplayName("Проверка плейсхолдеров")
    @Feature("Placeholders")
    @Story("Проверка соответствия плейсхолдеров ожидаемым значениям")
    public void testPlaceholders(String field, String actualText, String expectedText) {
        assertThat(actualText)
                .as("Несоответствие в плейсхолдере: " + actualText)
                .isEqualTo(expectedText);
        logger.info("\nБлок '{}': фактическое значение плейсхолдера : '{}'", field, actualText);
    }

    @ParameterizedTest
    @CsvSource({
            "297777777, 499, test@example.com",
            "298888888, 250, example@test.com",
    })
    @DisplayName("Заполнение формы и проверка кнопки 'Продолжить'")
    @Feature("Form Submission")
    @Story("Проверка отправки формы с различными данными")
    public void testFormSubmission(String phoneNumber, String amount, String email) {
        assertThat(homePage.fillFormAndSubmit(phoneNumber, amount, email))
                .as("Фрейм не появился. Возможно вы ввели некорректные данные в блок 'Онлайн пополнение без комиссии'")
                .isTrue();
        logger.info("\nТест 'Заполнение формы и проверка кнопки «Продолжить» - выполнен. Данные: phoneNumber='{}', amount='{}', email='{}'",
                phoneNumber, amount, email);
    }

    @ParameterizedTest
    @MethodSource("iFrameFormData")
    @DisplayName("Проверка инф-ии в iframe сумма\\телефон\\инф на кнопке")
    @Feature("iFrame Verification")
    @Story("Проверка отображаемой информации в iframe")
    public void testIframeData(String actualText, String expected, String comment) {
        assertThat(actualText)
                .as("Ошибка: Ожидалось значение '" + expected + "', но было получено '" + actualText + "'")
                .isEqualTo(expected);
        logger.info("\nПроверка {} в фрейме: фактическое значение '{}'",
                comment, actualText);
    }

    @ParameterizedTest
    @MethodSource("iframeLabel")
    @DisplayName("Проверка label iframe")
    @Feature("iFrame Label Verification")
    @Story("Проверка правильности label в iframe")
    public void testLabelIframe(String actualText, String expectedText) {
        assertThat(actualText)
                .as("Несоответствие label фрейма: " + actualText)
                .isEqualTo(expectedText);
        logger.info("\nПроверка поля '{}' в iframe : фактическое значение '{}'",
                expectedText, actualText);
    }

    @Test
    @DisplayName("Проверка наличия и кол-ва логотипов платежных систем")
    @Feature("Payment Logos")
    @Story("Проверка наличия и количества логотипов платежных систем в iframe")
    public void shouldDisplayAllPaymentLogos() {
        assertAll("\nПроверка отображения, наличия и кол-ва платёжных систем iframe",
                () -> {
                    assertThat(homePage.checkIframeLogosDisplayed())
                            .as("Не все логотипы платежных систем в iframe отображаются")
                            .isTrue();
                    logger.info("\nТест \"Проверка наличия логотипов платежных систем в iframe\" - выполнен");
                },
                () -> {
                    assertThat(homePage.checkSizeLogos())
                            .as("Неверное кол-во логотипов в iframe")
                            .isEqualTo(5);
                    logger.info("\nТест \"Проверка кол-ва логотипов платежных систем в iframe\" - выполнен");
                }
        );
    }
}
