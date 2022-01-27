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

public class WaiterOrdersPage {
    private WebDriver driver;

    @FindBy(css = ".mat-table.cdk-table")
    private WebElement ordersTable;

    @FindBy(xpath = "//tr[contains(@class, 'example-element-row')]")
    private List<WebElement> orderRows;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-waiter-orders/table/tbody/tr[1]")
    private WebElement latestOrderRow;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-waiter-orders/table/tbody/tr[1]/td[3]")
    private WebElement latestOrderStatusField;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-waiter-orders/table/tbody/tr[1]/td[4]/button[1]")
    private WebElement editLatestOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-waiter-orders/table/tbody/tr[1]/td[4]/button[2]")
    private WebElement finishLatestOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-waiter-orders/table/tbody/tr[1]/td[4]/button[3]")
    private WebElement deleteLatestOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-waiter-orders/table/tbody/tr[2]/td/div/div/table[1]/tr")
    private List<WebElement> latestOrderDishes;

    @FindBy(xpath = "/html/body/app-root/div/div/div/app-waiter-orders/table/tbody/tr[2]/td/div/div/table[2]/tr")
    private List<WebElement> latestOrderDrinks;

    public WaiterOrdersPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfOrders() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(ordersTable));

        return orderRows.size();
    }

    public void deleteLatestOrder() throws InterruptedException {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(deleteLatestOrderButton));

        deleteLatestOrderButton.click();

        Alert confirm = driver.switchTo().alert();
        confirm.accept();

        Utils.waitForAlert(driver, 5);
        Alert complete = driver.switchTo().alert();
        complete.dismiss();
    }

    public void finishLatestOrder() throws InterruptedException {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(finishLatestOrderButton));

        finishLatestOrderButton.click();

        Utils.waitForAlert(driver, 5);
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void goToEditLatestOrder() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(editLatestOrderButton));

        editLatestOrderButton.click();
    }

    public int getNumberOfLatestOrderItems() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(latestOrderRow));

        return latestOrderDishes.size() + latestOrderDrinks.size() - 2; // -2 ZBOG 2 REDA KOJI SADRZE HEADERE TABELE
    }

    public String getLatestOrderStatus() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(ordersTable));

        return latestOrderStatusField.getText();
    }
}
