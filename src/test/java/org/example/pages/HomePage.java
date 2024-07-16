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

    @FindBy(xpath = "//input[@placeholder='Номер телефона']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input [@id='connection-sum']")
    private WebElement amountInput;

    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailInput;

    @FindBy(xpath = "(//button[text()='Продолжить'])[1]")
    private WebElement continueButton;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement iframe;

    @FindBy(xpath = "(//div[@class='pay-description__cost']//span)[1]")
    private WebElement iframeDescriptionCost;

    @FindBy(xpath = "//div[@class='pay-description__text']//span")
    private WebElement iframeDescriptionText;

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    public boolean isBlockTitleDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(blockTitle));
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        return blockTitle.getText().equals(expectedTitle);
    }

    public boolean arePaymentLogosDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentLogos));
        return paymentLogos.stream().allMatch(WebElement::isDisplayed);
    }

    public void clickMoreInfoLink() {
        wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink));
        moreInfoLink.click();
    }

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
