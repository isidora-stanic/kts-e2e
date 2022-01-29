package com.rokzasok.ktse2e.isidora_tests.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddTablePage {

    private WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameIN;
    @FindBy(id = "x")
    private WebElement xIN;
    @FindBy(id = "y")
    private WebElement yIN;

    @FindBy(id = "name-err")
    private WebElement nameErrLBL;
    @FindBy(id = "same-name-err")
    private WebElement sameNameErrLBL;
    @FindBy(id = "x-err")
    private WebElement xErrLBL;
    @FindBy(id = "y-err")
    private WebElement yErrLBL;

    @FindBy(id = "save-btn")
    private WebElement saveBTN;

    public AddTablePage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clearName() {
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.visibilityOf(nameIN));

        nameIN.clear();
    }
    public void clearX() {
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.visibilityOf(xIN));

        xIN.clear();
    }
    public void clearY() {
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.visibilityOf(yIN));

        yIN.clear();
    }

    public void setNameIN(String name) {
        clearName();
        nameIN.sendKeys(name);
    }
    public void setXIN(String x) {
        clearX();
        xIN.sendKeys(x);
    }
    public void setYIN(String y) {
        clearY();
        yIN.sendKeys(y);
    }

    public void clickSave() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(saveBTN));

        saveBTN.click();
    }

    public boolean warnIsPresent(String warn) {
        WebElement toWait = null;
        switch (warn) {
            case "name":
                toWait = nameErrLBL;
                break;
            case "same name":
                toWait = sameNameErrLBL;
                break;
            case "x":
                toWait = xErrLBL;
                break;
            case "y":
                toWait = yErrLBL;
                break;
            default:
                return false;
        }

        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOf(toWait));
            System.out.println(toWait.getText());
            return toWait.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
