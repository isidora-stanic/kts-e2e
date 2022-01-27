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

public class EditOrderPage {
    private WebDriver driver;

    @FindBy(css = ".item-details")
    private List<WebElement> addedItems;

    @FindBy(css = ".item-price")
    private List<WebElement> itemPriceFields;

    @FindBy(css = ".total-price")
    private WebElement totalOrderPriceField;

    @FindBy(css = ".items")
    private WebElement itemsContainer;

    @FindBy(xpath = "(//button[contains(@class, 'remove-item-btn')])[1]")
    private WebElement removeFirstButton;

    @FindBy(id = "confirmOrder")
    private WebElement confirmButton;

    public EditOrderPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getAddedItemsNumber() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElements(addedItems));

        return this.addedItems.size();
    }

    public void removeOne() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(removeFirstButton));

        removeFirstButton.click();
    }

    public void confirmEdit() throws InterruptedException {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(confirmButton));

        confirmButton.click();

        Utils.waitForAlert(driver, 5);
        Alert success = driver.switchTo().alert();
        success.dismiss();
    }
}
