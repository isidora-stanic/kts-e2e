package com.rokzasok.ktse2e.matija_tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportsPage {
    private WebDriver driver;

    @FindBy(id = "dish-income")
    private WebElement dishIncome;

    @FindBy(id = "dish-expenses")
    private WebElement dishExpenses;

    @FindBy(id = "dish-total")
    private WebElement dishTotal;

    @FindBy(id = "drink-income")
    private WebElement drinkIncome;

    @FindBy(id = "drink-expenses")
    private WebElement drinkExpenses;

    @FindBy(id = "drink-total")
    private WebElement drinkTotal;

    @FindBy(id = "salary-total")
    private WebElement salaryExpenses;

    @FindBy(css = ".mat-select-trigger")
    private WebElement switchReportIntervalButton;

    @FindBy(id = "mat-option-0")
    private WebElement selectMonthlyReports;

    @FindBy(id = "mat-option-1")
    private WebElement selectWeeklyReports;

    public ReportsPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getDishExpenses() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(dishExpenses));

        return Integer.parseInt(dishExpenses.getText());
    }

    public int getDishIncome() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(dishIncome));

        return Integer.parseInt(dishIncome.getText());
    }

    public int getDishTotal() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(dishTotal));

        return  Integer.parseInt(dishTotal.getText());
    }

    public int getDrinkExpenses() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(drinkExpenses));

        return Integer.parseInt(drinkExpenses.getText());
    }


    public int getDrinkIncome() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(drinkIncome));

        return Integer.parseInt(drinkIncome.getText());
    }

    public int getDrinkTotal() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(drinkTotal));

        return Integer.parseInt(drinkTotal.getText());
    }

    public int getSalaryExpenses() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(salaryExpenses));

        return Integer.parseInt(salaryExpenses.getText());
    }

    public void openReportIntervalMenu() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(switchReportIntervalButton));

        switchReportIntervalButton.click();
    }

    public void selectMonthlyReports() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(selectMonthlyReports));

        selectMonthlyReports.click();
    }

    public void selectWeeklyReports() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(selectWeeklyReports));

        selectWeeklyReports.click();
    }
}
