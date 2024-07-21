package org.example.pages;

import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class HomePage {
    private static final int TIMEOUT_IN_SECONDS = 10;
    private final WebDriverWait wait;
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
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
    @FindBy(xpath = "//button[@class='colored disabled']")
    private WebElement iFrameButtonSum;
    @FindBy(xpath = "//p[text()='Введите сумму платежа']")
    private List<WebElement> errorSum;
    @FindBy(xpath = "//p[text()='Неверно указан номер']")
    private List<WebElement> errorPhone;
    @FindBy(xpath = "//p[text()='Введите корректный адрес электронной почты.']")
    private List<WebElement> errorEmail;
    @FindBy(xpath = "//label[text()='Номер карты']")
    private WebElement iFrameLabelCardNum;
    @FindBy(xpath = "//label[text()='Срок действия']")
    private WebElement iFrameLabelPeriod;
    @FindBy(xpath = "//label[text()='CVC']")
    private WebElement iFrameLabelCvc;
    @FindBy(xpath = "//label[text()='Имя держателя (как на карте)']")
    private WebElement iFrameLabelName;
    @FindBy(xpath = "//div[@class='cards-brands ng-tns-c46-1']//img")
    private List<WebElement> iFrameLogosPayment;

    public void acceptCookies() {
        clickElement(acceptCookiesButton);
    }

    public void inputDataAndSwitchFrame(String phoneNumber, String amount, String email) {
        if (fillFormAndSubmit(phoneNumber, amount, email)) {
            switchToIframeAndAwait(iframe, iFrameSum);
        }
    }

    public String getIframeText(String phoneNumber, String amount, String email, WebElement element) {
        inputDataAndSwitchFrame(phoneNumber, amount, email);
        return getTextWhenVisible(element);
    }

    public String getIframeText(WebElement element) {
        return getTextWhenVisible(element);
    }

    public String getPlaceholderText(WebElement element) {
        return element.getAttribute("placeholder");
    }

    public boolean fillFormAndSubmit(String phoneNumber, String amount, String email) {
        driver.navigate().refresh();
        clickElement(servicesDropdown);
        clickElement(communicationServices);
        fillInputField(phonePlaceholderCom, phoneNumber);
        fillInputField(amountPlaceholderCom, amount);
        fillInputField(emailPlaceholderCom, email);
        clickElement(continueButton);
        return !areAnyErrorMessagesDisplayed() && isElementVisible(iframe);
    }

    private boolean areAnyErrorMessagesDisplayed() {
        return getErrorElements().stream().anyMatch(WebElement::isDisplayed);
    }

    private List<WebElement> getErrorElements() {
        List<WebElement> errorElements = new ArrayList<>();
        errorElements.addAll(errorPhone);
        errorElements.addAll(errorSum);
        errorElements.addAll(errorEmail);
        return errorElements;
    }

    private void clickElement(WebElement element) {
        waitUntilClickable(element).click();
    }

    private void fillInputField(WebElement element, String value) {
        WebElement visibleElement = waitUntilVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(value);
    }

    private String getTextWhenVisible(WebElement element) {
        return waitUntilVisible(element).getText();
    }

    private boolean isElementVisible(WebElement element) {
        return waitUntilVisible(element).isDisplayed();
    }

    private WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private void switchToIframeAndAwait(WebElement iframe, WebElement elementToWaitFor) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        waitUntilVisible(elementToWaitFor);
    }

    private void waitVisibilityOfElements(List<WebElement> element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public boolean checkIframeLogosDisplayed() {
        defaultLoginSwitchIframe();
        return iFrameLogosPayment.stream().allMatch(WebElement::isDisplayed);
    }

    public int checkSizeLogos() {
        defaultLoginSwitchIframe();
        return iFrameLogosPayment.size();
    }

    public void defaultLoginSwitchIframe() {
        fillFormAndSubmit("297777777", "499", "test@example.com");
        driver.switchTo().frame(iframe);
        waitVisibilityOfElements(iFrameLogosPayment);
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