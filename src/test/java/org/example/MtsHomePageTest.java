package org.example;

import org.example.base.TestsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MtsHomePageTest extends TestsConfig {

    static Stream<Arguments> placeholderDataProvider() {
        return homePage.getPlaceholderData();
    }

    static Stream<Arguments> iFrameFormData() {
        return homePage.getIframeFormData();
    }

    static Stream<Arguments> iframeLabel() {
        return homePage.getIframeLabel();
    }

    @ParameterizedTest
    @MethodSource("placeholderDataProvider")
    @DisplayName("Проверка плейсхолдеров")
    public void testPlaceholders(WebElement inputElement, String expectedText) {
        assertEquals(expectedText, homePage.getPlaceholderText(inputElement),
                "Несоответствие в плейсхолдере: " + homePage.getPlaceholderText(inputElement));
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
        System.out.printf("Тест 'Заполнить поля и проверить работу кнопки «Продолжить»' - выполнен%nДанные : %s, %s, %s%n%n", phoneNumber, amount, email);
    }

    //    @ParameterizedTest
//    @MethodSource("formData")
//    public void testDataFrameSum(String phoneNumber, String amount, String email) {
//        assertEquals(amount + ".00 BYN", homePage.getIframeSum(phoneNumber, amount, email));
//    }
//
//    @ParameterizedTest
//    @MethodSource("formData")
//    public void testDataFramePhoneNumber(String phoneNumber, String amount, String email) {
//        assertEquals("Оплата: Услуги связи Номер:375" + phoneNumber, homePage.getIframePhoneNumber(phoneNumber, amount, email));
//    }

    @ParameterizedTest
    @MethodSource("iFrameFormData")
    public void testIframeData(String phoneNumber, String amount, String email, WebElement element, String expected, String comment) {
        String actualText = homePage.getIframeText(phoneNumber, amount, email, element);
        assertEquals(expected, actualText,
                "Ошибка: Ожидалось значение '" + expected + "', но было получено '" + actualText + "'");
        System.out.printf("Проверка %s в фрейме :%nДанные : телефон: %s, Сумма: %s, Email: %s%n%n", comment, phoneNumber, amount, email);
    }

    @ParameterizedTest
    @MethodSource("iframeLabel")
    @DisplayName("Проверка label iframe")
    public void testLabelIframe(WebElement inputElement, String expectedText) {
        homePage.inputDataAndSwitchFrame("297777777", "499", "test@example.com");
        assertEquals(expectedText, homePage.getLabelText(inputElement),
                "Несоответствие label фрейма: " + homePage.getLabelText(inputElement));
    }
}
