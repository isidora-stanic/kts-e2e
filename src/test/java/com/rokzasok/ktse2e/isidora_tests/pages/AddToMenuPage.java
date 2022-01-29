package com.rokzasok.ktse2e.isidora_tests.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToMenuPage {

    private WebDriver driver;

    @FindBy(id = "price")
    private WebElement priceIN;

    @FindBy(id = "add-btn")
    private WebElement addBTN;

    public AddToMenuPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setPriceIN(String price) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(priceIN));

        priceIN.clear();
        //js.executeScript("arguments[0].value='"+price+"';", priceIN);
        priceIN.sendKeys(price);
    }

    public void clickAdd() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(addBTN));

        addBTN.click();
    }
}
