package com.rokzasok.ktse2e.matija_tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaiterMainPage {
    private WebDriver driver;

    @FindBy(xpath = "(//div[contains(@class, 'sitting-table')])[last()]")
    private WebElement lastTable;

    //TODO: Izbrisati kad isidora popravi
    @FindBy(xpath = "((//div[contains(@class, 'sitting-table')])[last()])/p")
    private WebElement lastTableLink;

    @FindBy(id = "waiters-orders-link")
    private WebElement ordersLink;

    @FindBy(xpath = "a[contains(text(), 'Logout')]")
    private WebElement logoutLink;

    @FindBy(id = "user-name-link")
    private WebElement usernameLink;

    public WaiterMainPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void orderForLastTable() {
        //TODO: Zameniti lastTableLink sa lastTable kad isidora popravi
        new WebDriverWait(this.driver, 5)
                .until(ExpectedConditions.elementToBeClickable(lastTableLink));

        lastTableLink.click();
    }

    public void goToWaiterOrders() {
        new WebDriverWait(this.driver, 5)
                .until(ExpectedConditions.elementToBeClickable(ordersLink));

        ordersLink.click();
    }

    public void logout() {
        new WebDriverWait(this.driver, 5)
                .until(ExpectedConditions.elementToBeClickable(logoutLink));

        logoutLink.click();
    }

    public String getLastTableId() {
        new WebDriverWait(this.driver, 5)
                .until(ExpectedConditions.visibilityOf(lastTable));

        return lastTable.getAttribute("id").split("-")[1];
    }

    public String getUsername() {
        new WebDriverWait(this.driver, 5)
                .until(ExpectedConditions.visibilityOf(usernameLink));

        return usernameLink.getText();
    }
}
