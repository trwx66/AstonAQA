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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HomePageTest extends TestsConfig {

    static Stream<Arguments> placeholderDataProvider() {
        return homePage.getPlaceholderData();
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
            "292323322, 1, user@domain.com"
    })
    @DisplayName("Заполнение формы и проверка кнопки 'Продолжить'")
    public void testFormSubmission(String phoneNumber, String amount, String email) {
        driver.navigate().refresh();
        assertTrue(homePage.fillFormAndSubmit(phoneNumber, amount, email),
                "Вы ввели некорректные данные в блок 'Онлайн пополнение без комиссии'");
        System.out.println("Тест 'Заполнить поля и проверить работу кнопки «Продолжить»' - выполнен для данных: "
                + phoneNumber + ", " + amount + ", " + email);
    }
}
