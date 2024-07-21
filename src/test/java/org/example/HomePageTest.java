package org.example;

import org.example.base.TestsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HomePageTest extends TestsConfig {

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
    public void testPlaceholders(WebElement inputElement, String expectedText) {
        assertEquals(expectedText, homePage.getPlaceholderText(inputElement),
                "Несоответствие в плейсхолдере: " + homePage.getPlaceholderText(inputElement));
        logger.info("Проверка плейсхолдера для элемента {}: Ожидалось '{}', получено '{}'",
                inputElement.getText(), expectedText, homePage.getPlaceholderText(inputElement));
    }

    @ParameterizedTest
    @CsvSource({
            "297777777, 499, test@example.com",
            "298888888, 250, example@test.com",
    })
    @DisplayName("Заполнение формы и проверка кнопки 'Продолжить'")
    public void testFormSubmission(String phoneNumber, String amount, String email) {
        assertTrue(homePage.fillFormAndSubmit(phoneNumber, amount, email),
                "Фрейм не появился. Возможно вы ввели некорректные данные в блок 'Онлайн пополнение без комиссии'");
        logger.info("Тест 'Заполнение формы и проверка кнопки «Продолжить»' - выполнен. Данные: phoneNumber='{}', amount='{}', email='{}'",
                phoneNumber, amount, email);
    }

    @ParameterizedTest
    @MethodSource("iFrameFormData")
    public void testIframeData(String phoneNumber, String amount, String email, WebElement element, String expected, String comment) {
        String actualText = homePage.getIframeText(phoneNumber, amount, email, element);
        assertEquals(expected, actualText,
                "Ошибка: Ожидалось значение '" + expected + "', но было получено '" + actualText + "'");
        logger.info("Проверка {} в фрейме: Данные - телефон: '{}', Сумма: '{}', Email: '{}'",
                comment, phoneNumber, amount, email);
    }

    @ParameterizedTest
    @MethodSource("iframeLabel")
    @DisplayName("Проверка label iframe")
    public void testLabelIframe(WebElement inputElement, String expectedText) {
        assertEquals(expectedText, homePage.getIframeText("297777777", "499", "test@example.com", inputElement),
                "Несоответствие label фрейма: " + homePage.getIframeText(inputElement));
        logger.info("Проверка label iframe: Ожидалось '{}', получено '{}'",
                expectedText, homePage.getIframeText(inputElement));
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void shouldDisplayAllPaymentLogos() {
        assertAll("Проверка отображения, наличия и кол-ва платёжных систем iframe",
                () -> {
                    assertThat(homePage.checkIframeLogosDisplayed())
                            .as("Не все логотипы платежных систем в iframe отображаются")
                            .isTrue();
                    logger.info("Тест \"Проверка наличия логотипов платежных в iframe систем\" - выполнен");
                },
                () -> {
                    assertThat(homePage.checkSizeLogos())
                            .as("Неверное кол-во логотипов в iframe")
                            .isEqualTo(5);
                    logger.info("Тест \"Проверка кол-ва логотипов платежных систем в iframe\" - выполнен");
                }
        );
    }
}
