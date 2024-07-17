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
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//button[@class='select__header']")
    private WebElement servicesDropdown;

    @FindBy(xpath = "//p[text()='Услуги связи']")
    private WebElement communicationServices;

    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    private WebElement phoneInputCommunication;

    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement amountInputCommunication;

    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailInputCommunication;

    @FindBy(xpath = "//input[@id='internet-phone']")
    private WebElement phoneInputInternet;

    @FindBy(xpath = "//input[@id='internet-sum']")
    private WebElement amountInputInternet;

    @FindBy(xpath = "//input[@id='internet-email']")
    private WebElement emailInputInternet;

    @FindBy(xpath = "//input[@id='score-instalment']")
    private WebElement accountNumberInputInstallment;

    @FindBy(xpath = "//input[@id='instalment-sum']")
    private WebElement amountInputInstallment;

    @FindBy(xpath = "//input[@id='instalment-email']")
    private WebElement emailInputInstallment;

    @FindBy(xpath = "//input[@id='score-arrears']")
    private WebElement accountNumberInputDebt;

    @FindBy(xpath = "//input[@id='arrears-sum']")
    private WebElement amountInputDebt;

    @FindBy(xpath = "//input[@id='arrears-email']")
    private WebElement emailInputDebt;

    @FindBy(xpath = "(//button[text()='Продолжить'])[1]")
    private WebElement continueButton;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement iframe;

    @FindBy(xpath = "//div[@class='payment-page__order-description pay-description']")
    private List<WebElement> iframeDescription;


    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
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
        String actual = wait.until(ExpectedConditions.visibilityOfAllElements(iframeDescription))
                .stream().map(WebElement::getText).collect(Collectors.joining("\n"));
        String expected = amount + ".00 BYN\nОплата: Услуги связи Номер:375" + phone;
        return expected.equals(actual);
    }

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
                arguments(amountInputDebt, "Сумма"),
                arguments(emailInputDebt, "E-mail для отправки чека")
        );
    }
}
