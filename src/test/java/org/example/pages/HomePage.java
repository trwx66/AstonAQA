package org.example.pages;

import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

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
    public WebElement phoneInputCommunication;

    @FindBy(xpath = "//input [@id='connection-sum']")
    public WebElement amountInputCommunication;

    @FindBy(xpath = "//input[@id='connection-email']")
    public WebElement emailInputCommunication;

    @FindBy(xpath = "//input[@id='internet-phone']")
    public WebElement phoneInputInternet;

    @FindBy(xpath = "//input[@id='internet-sum']")
    public WebElement amountInputInternet;

    @FindBy(xpath = "//input[@id='internet-email']")
    public WebElement emailInputInternet;

    @FindBy(xpath = "//input[@id='score-instalment']")
    public WebElement accountNumberInputInstallment;

    @FindBy(xpath = "//input[@id='instalment-sum']")
    public WebElement amountInputInstallment;

    @FindBy(xpath = "//input[@id='instalment-email']")
    public WebElement emailInputInstallment;

    @FindBy(xpath = "//input[@id='score-arrears']")
    public WebElement accountNumberInputDebt;

    @FindBy(xpath = "//input[@id='arrears-sum']")
    public WebElement amountInputDebt;

    @FindBy(xpath = "//input[@id='arrears-email']")
    public WebElement emailInputDebt;

    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
    public WebElement blockTitle;

    @FindBy(xpath = "//div[@class='pay__partners']/ul/li")
    public List<WebElement> paymentLogos;

    @FindBy(xpath = "(//button[text()='Продолжить'])[1]")
    public WebElement continueButton;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    public WebElement iframe;

    @FindBy(xpath = "//div [@class='payment-page__order-description pay-description']")
    public WebElement iframeDescription;

    public Stream<Arguments> getPlaceholderData() {
        return Stream.of(
             arguments(phoneInputCommunication, "Номер телефона"),
             arguments(amountInputCommunication, "Сумма"),
             arguments(emailInputCommunication, "E-mail для отправки чека"),
             arguments(phoneInputInternet, "Номер абонента"),
             arguments(amountInputInternet, "Сумма"),
             arguments(emailInputInternet, "E-mail для отправки чека"),
             arguments(accountNumberInputInstallment, "Номер счета на 44"),
             arguments(amountInputInstallment, "Сумма"),
             arguments(emailInputInstallment, "E-mail для отправки чека"),
             arguments(accountNumberInputDebt, "Номер счета на 2073"),
             arguments(amountInputInstallment, "Сумма"),
             arguments(emailInputInstallment, "E-mail для отправки чека")
        );
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    public String getPlaceholderText(WebElement element) {
        return element.getAttribute("placeholder");
    }

    public boolean fillFormAndSubmit(String phone, String amount, String email) {
        servicesDropdown.click();
        communicationServices.click();
        phoneInputCommunication.sendKeys(phone);
        amountInputCommunication.sendKeys(amount);
        emailInputCommunication.sendKeys(email);
        continueButton.click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        String actual = wait.until(ExpectedConditions.visibilityOfAllElements(iframeDescription)).
                stream().map(WebElement::getText).collect(Collectors.joining("\n"));
        String expected = amount.concat(".00 BYN\n")
                .concat("Оплата: Услуги связи Номер:375").concat(phone);
        return expected.equals(actual);
    }
}