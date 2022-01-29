package com.rokzasok.ktse2e.isidora_tests.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TablesPage {

    private WebDriver driver;

    @FindBy(id = "unsaved-warn")
    private WebElement unsavedWarnLBL;

    @FindBy(id = "same-name-err")
    private WebElement sameNameErrLBL;

    @FindBy(id = "same-coord-err")
    private WebElement sameCoordErrLBL;

    @FindBy(id = "name-err")
    private WebElement nameErrLBL;
    @FindBy(id = "x-err")
    private WebElement xErrLBL;
    @FindBy(id = "y-err")
    private WebElement yErrLBL;

    @FindBy(className = "sitting-table")
    private List<WebElement> tables;

    @FindBy(className = "sitting-table")
    private WebElement firstTable;

    @FindBy(className = "selected")
    private WebElement selectedTable;

    @FindBy(className = "not-selected")
    private WebElement notSelectedTable;

    @FindBy(id = "name")
    private WebElement nameIN;
    @FindBy(id = "x")
    private WebElement xIN;
    @FindBy(id = "y")
    private WebElement yIN;

    @FindBy(css = "button[type='submit']")
    private WebElement saveBTN;

    @FindBy(id = "add-new")
    private WebElement addBTN;

    @FindBy(className = "remove-btn")
    private WebElement deleteBTN;


    public TablesPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean warnIsPresent(String warn) {
        WebElement toWait = null;
        switch (warn) {
            case "unsaved":
                toWait = unsavedWarnLBL;
                break;
            case "same name":
                toWait = sameNameErrLBL;
                break;
            case "same coord":
                toWait = sameCoordErrLBL;
                break;
            case "name":
                toWait = nameErrLBL;
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

    public void clickFirstTable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(firstTable));

        firstTable.click();
    }

    public void clickNotSelectedTable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(notSelectedTable));

        notSelectedTable.click();
    }

    public String getSelectedTableName() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(selectedTable));
        System.out.println("Selektovani sto: " + selectedTable.findElement(new By.ByClassName("table-name")).getText());
        return selectedTable.findElement(new By.ByClassName("table-name")).getText();
    }

    public void clearName() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(nameIN));

        nameIN.clear();
    }
    public void clearX() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(xIN));

        xIN.clear();
    }
    public void clearY() {
        new WebDriverWait(driver, 5)
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

    public String getNameINValue() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(nameIN));
        System.out.println("Naziv Stola: " + nameIN.getAttribute("value"));
        return nameIN.getAttribute("value");
    }

    public String getXINValue() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(xIN));
        return xIN.getAttribute("value");
    }

    public String getYINValue() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(yIN));
        return yIN.getAttribute("value");
    }

    public void clickSave() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(saveBTN));

        saveBTN.click();
    }

    public void clickAdd() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(addBTN));

        addBTN.click();
    }

    public void deleteFirstTable() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(deleteBTN));

        deleteBTN.click();

        driver.switchTo().alert().accept();
    }

}
