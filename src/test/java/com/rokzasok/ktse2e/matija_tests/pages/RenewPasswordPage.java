package com.rokzasok.ktse2e.matija_tests.pages;

import com.rokzasok.ktse2e.matija_tests.Utils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RenewPasswordPage {
    private WebDriver driver;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(css = ".alert.alert-danger")
    private WebElement errorResponse;

    public RenewPasswordPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(usernameField));

        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(passwordField));

        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitPasswordChange() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(submitButton));

        submitButton.click();
    }

    public String getErrorMessage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(errorResponse));

        return errorResponse.getText();
    }
}
