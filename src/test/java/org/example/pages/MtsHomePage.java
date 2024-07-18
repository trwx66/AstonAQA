package org.example.pages;

import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MtsHomePage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public MtsHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@class, 'btn_black') and text()='Принять']")
    private WebElement acceptCookiesButton;
    @FindBy(xpath = "//button[@class='select__header']")
    private WebElement servicesDropdown;
    @FindBy(xpath = "//p[text()='Услуги связи']")
    private WebElement communicationServices;
    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    private WebElement phonePlaceholderCom;
    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement amountPlaceholderCom;
    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailPlaceholderCom;
    @FindBy(xpath = "//input[@id='internet-phone']")
    private WebElement phonePlaceholderInternet;
    @FindBy(xpath = "//input[@id='internet-sum']")
    private WebElement amountPlaceholderInternet;
    @FindBy(xpath = "//input[@id='internet-email']")
    private WebElement emailPlaceholderInternet;
    @FindBy(xpath = "//input[@id='score-instalment']")
    private WebElement accNumPlaceholderIns;
    @FindBy(xpath = "//input[@id='instalment-sum']")
    private WebElement amountPlaceholderIns;
    @FindBy(xpath = "//input[@id='instalment-email']")
    private WebElement emailPlaceholderIns;
    @FindBy(xpath = "//input[@id='score-arrears']")
    private WebElement accNumPlaceholderDebt;
    @FindBy(xpath = "//input[@id='arrears-sum']")
    private WebElement amountPlaceholderDebt;
    @FindBy(xpath = "//input[@id='arrears-email']")
    private WebElement emailPlaceholderDebt;
    @FindBy(xpath = "(//button[text()='Продолжить'])[1]")
    private WebElement continueButton;
    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement iframe;
    @FindBy(xpath = "//span[contains(text(), '.00 BYN')]")
    private WebElement iFrameSum;
    @FindBy(xpath = "//span[contains(text(), 'Оплата: Услуги связи')]")
    private WebElement iFramePhoneNumber;
    @FindBy(xpath = "//button [@class='colored disabled']")
    private WebElement iFrameButtonSum;
    @FindBy(xpath = "//p [text()='Введите сумму платежа']")
    private List<WebElement> errorSum;
    @FindBy(xpath = "//p [text()='Неверно указан номер']")
    private List<WebElement> errorPhone;
    @FindBy(xpath = "//p [text()='Введите корректный адрес электронной почты.']")
    private List<WebElement> errorEmail;
    @FindBy(xpath = "//label [text()='Номер карты']")
    private WebElement iFrameLabelCardNum;
    @FindBy(xpath = "//label [text()='Срок действия']")
    private WebElement iFrameLabelPeriod;
    @FindBy(xpath = "//label [text()='CVC']")
    private WebElement iFrameLabelCvc;
    @FindBy(xpath = "//label [text()='Имя держателя (как на карте)']")
    private WebElement iFrameLabelName;

    public void inputDataAndSwitchFrame(String phoneNumber, String amount, String email) {
        if (fillFormAndSubmit(phoneNumber, amount, email))
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
    }

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

    public String getLabelText(WebElement element) {
        return element.getText();
    }

    public boolean fillFormAndSubmit(String phoneNumber, String amount, String email) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(servicesDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(communicationServices)).click();
        phonePlaceholderCom.sendKeys(phoneNumber);
        amountPlaceholderCom.sendKeys(amount);
        emailPlaceholderCom.sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        if (areAnyErrorMessagesDisplayed(Arrays.asList(errorPhone, errorSum, errorEmail))) return false;
        return wait.until(ExpectedConditions.visibilityOf(iframe)).isDisplayed();
    }

    private boolean areAnyErrorMessagesDisplayed(List<List<WebElement>> errorElementsLists) {
        return errorElementsLists.stream()
                .flatMap(List::stream)
                .anyMatch(WebElement::isDisplayed);
    }

    public Stream<Arguments> getPlaceholderData() {
        return Stream.of(
                arguments(phonePlaceholderCom, "Номер телефона"),
                arguments(amountPlaceholderCom, "Сумма"),
                arguments(emailPlaceholderCom, "E-mail для отправки чека"),
                arguments(phonePlaceholderInternet, "Номер абонента"),
                arguments(amountPlaceholderInternet, "Сумма"),
                arguments(emailPlaceholderInternet, "E-mail для отправки чека"),
                arguments(accNumPlaceholderIns, "Номер счета на 44"),
                arguments(amountPlaceholderIns, "Сумма"),
                arguments(emailPlaceholderIns, "E-mail для отправки чека"),
                arguments(accNumPlaceholderDebt, "Номер счета на 2073"),
                arguments(amountPlaceholderDebt, "Сумма"),
                arguments(emailPlaceholderDebt, "E-mail для отправки чека")
        );
    }

    public Stream<Arguments> getIframeLabel() {
        return Stream.of(
                arguments(iFrameLabelCardNum, "Номер карты"),
                arguments(iFrameLabelPeriod, "Срок действия"),
                arguments(iFrameLabelCvc, "CVC"),
                arguments(iFrameLabelName, "Имя держателя (как на карте)")
        );
    }

    public Stream<Arguments> getIframeFormData() {

        return Stream.of(
                arguments("297777777", "499", "test@example.com", iFrameSum, "499.00 BYN", "\"label суммы\""),
                arguments("297777777", "499", "test@example.com", iFramePhoneNumber, "Оплата: Услуги связи Номер:375297777777", "\"label информации об оплате\""),
                arguments("297777777", "499", "test@example.com", iFrameButtonSum, "Оплатить 499.00 BYN", "\"кнопка подтверждения оплаты\"")
        );
    }
}
