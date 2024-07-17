package org.example;

import org.example.base.TestsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HomePageTest extends TestsConfig {

    Stream<Arguments> placeholderDataProvider() {
        return homePage.getPlaceholderData();
    }

    @ParameterizedTest
    @MethodSource("placeholderDataProvider")
    @DisplayName("Проверка плейсхолдеров")
    public void testPlaceholders(WebElement inputElement, String expectedText) {
        assertEquals(expectedText, homePage.getPlaceholderText(inputElement),
                "Несоответствие в плейсхолдере: " + homePage.getPlaceholderText(inputElement));
    }
}