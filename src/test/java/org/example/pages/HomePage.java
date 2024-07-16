package org.example.pages;

import org.openqa.selenium.By;
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

    // Локатор для кнопки принятия cookies
    @FindBy(xpath = "//button[contains(@class, 'btn_black') and text()='Принять']")
    private WebElement acceptCookiesButton;

    // Локатор для заголовка "Онлайн пополнение без комиссии"
    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
    private WebElement blockTitle;

    // Локатор для коллекции логотипов платёжных систем
    @FindBy(xpath = "//div[@class='pay__partners']/ul/li")
    private List<WebElement> paymentLogos;

    // Локатор для ссылки "Подробнее о сервисе"
    @FindBy(xpath = "//a[text()='Подробнее о сервисе']")
    private WebElement moreInfoLink;

    // Локатор для выпадающего списка "Услуги связи"
    @FindBy(xpath = "//button[@class='select__header']")
    private WebElement servicesDropdown;

    // Локатор для поля ввода номера телефона
    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    private WebElement phoneInput;

    // Локатор для поля ввода суммы
    @FindBy(xpath = "//input [@id='connection-sum']")
    private WebElement amountInput;

    // Локатор для поля ввода email
    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailInput;

    // Локатор для кнопки "Продолжить"
    @FindBy(xpath = "(//button[text()='Продолжить'])[1]")
    private WebElement continueButton;

    // Локатор для iframe
    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement iframe;

    // Локатор внутри фрейма с суммой пополнения
    @FindBy(xpath = "(//div[@class='pay-description__cost']//span)[1]")
    private WebElement iframeDescriptionCost;

    // Локатор внутри фрейма с суммой пополнения
    @FindBy(xpath = "//div[@class='pay-description__text']//span")
    private WebElement iframeDescriptionText;

    // Метод для принятия cookies
    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    // Метод для проверки наличия заголовка
    public boolean isBlockTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(blockTitle));
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        return blockTitle.getText().equals(expectedTitle);
    }

    // Метод для проверки наличия логотипов платёжных систем
    public boolean arePaymentLogosDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentLogos));
        return paymentLogos.stream().allMatch(WebElement::isDisplayed);
    }

    // Метод для клика по ссылке "Подробнее о сервисе"
    public void clickMoreInfoLink() {
        wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink));
        moreInfoLink.click();
    }

    // Метод для заполнения формы и клика по кнопке "Продолжить"
    public boolean fillFormAndSubmit(String phone, String amount, String email) {
        wait.until(ExpectedConditions.visibilityOf(phoneInput));
        servicesDropdown.click();
        servicesDropdown.findElement(By.xpath("//p[text()='Услуги связи']")).click();
        phoneInput.sendKeys(phone);
        amountInput.sendKeys(amount);
        emailInput.sendKeys(email);
        continueButton.click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        String actual = iframeDescriptionCost.getAttribute("innerText") + "\n".
                concat((iframeDescriptionText.getAttribute("innerText")));
        String expected = amount.concat(".00 BYN\n")
                .concat("Оплата: Услуги связи Номер:375").concat(phone);
        return expected.equals(actual);
    }
}
