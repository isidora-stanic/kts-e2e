package com.rokzasok.ktse2e.matija_tests.pages;

import com.rokzasok.ktse2e.matija_tests.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CreateOrderPage {
    private WebDriver driver;

    @FindBy(id = "accordion")
    private WebElement itemCategoriesCard;

    @FindBy(xpath = "//div[@id='accordion']//button")
    private List<WebElement> itemCategoryExpandLinks;

    @FindBy(css = ".order-item")
    private List<WebElement> orderItems;

    @FindBy(css = ".item-details")
    private List<WebElement> addedItems;

    @FindBy(id = "confirmOrder")
    private WebElement confirmOrderButton;

    @FindBy(css = ".item-price")
    private List<WebElement> itemPriceFields;

    @FindBy(css = ".total-price")
    private WebElement totalOrderPriceField;

    public CreateOrderPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void expandAllCategories() {
        new WebDriverWait(this.driver, 5)
                .until(ExpectedConditions.visibilityOf(itemCategoriesCard));

        for (WebElement itemCategoryExpandLink : itemCategoryExpandLinks) {
            itemCategoryExpandLink.click();
        }
    }

    public void addAllItems() {
        for (WebElement orderItem : orderItems) {
            new WebDriverWait(this.driver, 1)
                    .until(ExpectedConditions.visibilityOf(orderItem));

            orderItem.click();
        }
    }

    public int numberOfTotalItems() {
        int numberOfTotalItems = 0;
        for (WebElement orderItem : orderItems) {
            new WebDriverWait(this.driver, 1)
                    .until(ExpectedConditions.visibilityOf(orderItem));

            numberOfTotalItems++;
        }

        return numberOfTotalItems;
    }

    public int numberOfAddedItems() {
        return this.addedItems.size();
    }

    public int getSumOfItemPrices() {
        int sumOfPrices = 0;
        for (WebElement itemPrice : itemPriceFields) {
            new WebDriverWait(driver, 1)
                    .until(ExpectedConditions.visibilityOf(itemPrice));
            sumOfPrices += Integer.parseInt(itemPrice.getText());
        }
        return sumOfPrices;
    }


    public int getTotalPrice() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(totalOrderPriceField));

        return Integer.parseInt(totalOrderPriceField.getText());
    }

    public void confirmOrder(String orderNote) throws InterruptedException {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButton));

        confirmOrderButton.click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(orderNote);
        alert.accept();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        Alert confirm = driver.switchTo().alert();
        confirm.accept();
    }

    public String waitForOrderConfirmation() throws InterruptedException {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String message =  alert.getText();
        alert.dismiss();
        return message;
    }

}
