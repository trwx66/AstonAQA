package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@class, 'btn_black') and text()='Принять']")
    public WebElement acceptCookiesButton;

    @FindBy(xpath = "//button[@class='select__header']")
    public WebElement servicesDropdown;

    @FindBy(xpath = "//p[text()='Услуги связи']")
    public WebElement communicationServices;

    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    public WebElement phoneInput;

    @FindBy(xpath = "//input [@id='connection-sum']")
    public WebElement amountInput;

    @FindBy(xpath = "//input[@id='connection-email']")
    public WebElement emailInput;

    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
    public WebElement blockTitle;

    @FindBy(xpath = "//div[@class='pay__partners']/ul/li")
    public List<WebElement> paymentLogos;

    @FindBy(xpath = "//a[text()='Подробнее о сервисе']")
    public WebElement moreInfoLink;


    @FindBy(xpath = "(//button[text()='Продолжить'])[1]")
    public WebElement continueButton;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    public WebElement iframe;

    @FindBy(xpath = "//div [@class='payment-page__order-description pay-description']")
    public WebElement iframeDescription;

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }
    public boolean isPlaceholderCorrect(WebElement element, String expectedPlaceholder) {
        return element.getAttribute("placeholder").equals(expectedPlaceholder);
    }
    public String getPlaceholderText(WebElement element) {
        return element.getAttribute("placeholder");
    }

}