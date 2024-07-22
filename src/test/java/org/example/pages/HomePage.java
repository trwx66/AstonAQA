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

    public String getIframeText(WebElement element) {
        defaultLoginSwitchIframe();
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
        return Stream.of(errorPhone, errorSum, errorEmail)
                .flatMap(List::stream)
                .collect(Collectors.toList());
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
                arguments("Услуги вязи (Номер телефона)", getPlaceholderText(phonePlaceholderCom), "Номер телефона"),
                arguments("Услуги вязи (Сумма)", getPlaceholderText(amountPlaceholderCom), "Сумма"),
                arguments("Услуги вязи (E-mail для отправки чека)", getPlaceholderText(emailPlaceholderCom), "E-mail для отправки чека"),
                arguments("Домашний интернет (Номер абонента)", getPlaceholderText(phonePlaceholderInternet), "Номер абонента"),
                arguments("Домашний интернет (Сумма)", getPlaceholderText(amountPlaceholderInternet), "Сумма"),
                arguments("Домашний интернет (E-mail для отправки чека)", getPlaceholderText(emailPlaceholderInternet), "E-mail для отправки чека"),
                arguments("Рассрочка (Номер счета на 44)", getPlaceholderText(accNumPlaceholderIns), "Номер счета на 44"),
                arguments("Рассрочка (Сумма)", getPlaceholderText(amountPlaceholderIns), "Сумма"),
                arguments("Рассрочка (E-mail для отправки чека)", getPlaceholderText(emailPlaceholderIns), "E-mail для отправки чека"),
                arguments("Задолженность (Номер счета на 2073)", getPlaceholderText(accNumPlaceholderDebt), "Номер счета на 2073"),
                arguments("Задолженность (Сумма)", getPlaceholderText(amountPlaceholderDebt), "Сумма"),
                arguments("Задолженность (E-mail для отправки чека)", getPlaceholderText(emailPlaceholderDebt), "E-mail для отправки чека")
        );
    }

    public Stream<Arguments> getIframeLabel() {
        return Stream.of(
                arguments(getIframeText(iFrameLabelCardNum), "Номер карты"),
                arguments(getIframeText(iFrameLabelPeriod), "Срок действия"),
                arguments(getIframeText(iFrameLabelCvc), "CVC"),
                arguments(getIframeText(iFrameLabelName), "Имя держателя (как на карте)")
        );
    }

    public Stream<Arguments> getIframeFormData() {
        return Stream.of(
                arguments(getIframeText(iFrameSum), "499.00 BYN", "\"label суммы\""),
                arguments(getIframeText(iFramePhoneNumber), "Оплата: Услуги связи Номер:375297777777", "\"label информации об оплате\""),
                arguments(getIframeText(iFrameButtonSum), "Оплатить 499.00 BYN", "\"кнопка подтверждения оплаты\"")
        );
    }
}