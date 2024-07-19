package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MtsHomePage {
    private final WebDriverWait wait;

    public MtsHomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@class, 'btn_black') and text()='Принять']")
    private WebElement acceptCookiesButton;
    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
    private WebElement blockTitle;
    @FindBy(xpath = "//div[@class='pay__partners']/ul/li")
    private List<WebElement> paymentLogos;
    @FindBy(xpath = "//a[text()='Подробнее о сервисе']")
    private WebElement moreInfoLink;
    @FindBy(xpath = "//button[@class='select__header']")
    private WebElement servicesDropdown;
    @FindBy(xpath = "//p[text()='Услуги связи']")
    private WebElement communicationServices;
    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    private WebElement phoneInputCommunication;
    @FindBy(xpath = "//input [@id='connection-sum']")
    private WebElement amountInputCommunication;
    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailInputCommunication;
    @FindBy(xpath = "(//button[text()='Продолжить'])[1]")
    private WebElement continueButton;
    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement iframe;
    @FindBy(xpath = "//p [text()='Введите сумму платежа']")
    private List<WebElement> errorSum;
    @FindBy(xpath = "//p [text()='Неверно указан номер']")
    private List<WebElement> errorPhone;
    @FindBy(xpath = "//p [text()='Введите корректный адрес электронной почты.']")
    private List<WebElement> errorEmail;

    private void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void acceptCookies() {
        clickElement(acceptCookiesButton);
    }

    public boolean checkBlockTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(blockTitle));
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        return blockTitle.getText().equals(expectedTitle);
    }

    public boolean checkPaymentLogosDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentLogos));
        return paymentLogos.stream().allMatch(WebElement::isDisplayed);
    }

    public void clickMoreInfoLink() {
        clickElement(moreInfoLink);
    }

    public boolean fillFormAndSubmit(String phoneNumber, String amount, String email) {
        clickElement(servicesDropdown);
        clickElement(communicationServices);
        phoneInputCommunication.sendKeys(phoneNumber);
        amountInputCommunication.sendKeys(amount);
        emailInputCommunication.sendKeys(email);
        clickElement(continueButton);

        if (checkAnyErrorMessagesDisplayed(Arrays.asList(errorPhone, errorSum, errorEmail))) return false;

        return wait.until(ExpectedConditions.visibilityOf(iframe)).isDisplayed();
    }

    private boolean checkAnyErrorMessagesDisplayed(List<List<WebElement>> errorElementsLists) {
        return errorElementsLists.stream()
                .flatMap(List::stream)
                .anyMatch(WebElement::isDisplayed);
    }
}
