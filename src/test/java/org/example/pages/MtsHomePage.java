package org.example.pages;

import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MtsHomePage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public MtsHomePage(WebDriver driver) {
        this.driver = driver;
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

    @FindBy(xpath = "//span[contains(text(), '.00 BYN')]")
    public WebElement iFrameSum;

    @FindBy(xpath = "//span[contains(text(), 'Оплата: Услуги связи')]")
    public WebElement iFramePhoneNumber;

    @FindBy(xpath = "//button [@class='colored disabled']")
    public WebElement iFrameButtonSum;


    private void inputDataAndSwitchFrame(String phoneNumber, String amount, String email) {
        if (fillFormAndSubmit(phoneNumber, amount, email))
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
    }

//    public String getIframeSum(String phoneNumber, String amount, String email) {
//        inputDataAndSwitchFrame(phoneNumber, amount, email);
//        return wait.until(ExpectedConditions.visibilityOf(iFrameSum)).getText();
//    }
//
//    public String getIframePhoneNumber(String phoneNumber, String amount, String email) {
//        inputDataAndSwitchFrame(phoneNumber, amount, email);
//        return wait.until(ExpectedConditions.visibilityOf(iFramePhoneNumber)).getText();
//    }

    public String getIframeText(String phoneNumber, String amount, String email, WebElement element) {
        inputDataAndSwitchFrame(phoneNumber, amount, email);
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton)).click();
    }

    public String getPlaceholderText(WebElement element) {
        return element.getAttribute("placeholder");
    }

    public boolean fillFormAndSubmit(String phoneNumber, String amount, String email) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(servicesDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(communicationServices)).click();
        phoneInputCommunication.sendKeys(phoneNumber);
        amountInputCommunication.sendKeys(amount);
        emailInputCommunication.sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        return wait.until(ExpectedConditions.visibilityOf(iframe)).isDisplayed();
    }

//    public boolean verifyFrameContent(String phoneNumber, String amount, String email) {
//
//        if (fillFormAndSubmit(phoneNumber, amount, email)) {
//            String actual = wait.until(ExpectedConditions.visibilityOfAllElements(iframeDescription))
//                        .stream().map(WebElement::getText).collect(Collectors.joining("\n"));
//            String expected = amount + ".00 BYN\nОплата: Услуги связи Номер:375" + phoneNumber;
//            return expected.equals(actual);
//        }
//        return false;
//    }

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


    public Stream<Arguments> getFormData() {

        return Stream.of(
                arguments("297777777", "499", "test@example.com", iFrameSum, "499.00 BYN", "\"label суммы\""),
                arguments("298888888", "250", "example@test.com", iFrameSum, "250.00 BYN", "\"label суммы\""),
                arguments("292323322", "1", "user@domain.com", iFrameSum, "1.00 BYN", "label суммы"),
                arguments("297777777", "499", "test@example.com", iFramePhoneNumber, "Оплата: Услуги связи Номер:375297777777", "\"label информации об оплате\""),
                arguments("298888888", "250", "example@test.com", iFramePhoneNumber, "Оплата: Услуги связи Номер:375298888888","\"label информации об оплате\""),
                arguments("292323322", "1", "user@domain.com", iFramePhoneNumber, "Оплата: Услуги связи Номер:375292323322", "\"label информации об оплате\""),
                arguments("297777777", "499", "test@example.com", iFrameButtonSum, "Оплатить 499.00 BYN", "\"кнопка подтверждения оплаты\"")
        );
    }
}
