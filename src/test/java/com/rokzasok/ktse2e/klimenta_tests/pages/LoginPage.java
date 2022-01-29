package com.rokzasok.ktse2e.klimenta_tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement login;

    @FindBy(id = "usernameError")
    private WebElement usernameError;

    @FindBy(id = "passwordError")
    private WebElement passwordError;

    @FindBy(id = "error")
    private WebElement error;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(this.username));
        element.clear();
        element.sendKeys(username);
    }

    public void setPassword(String password){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(this.password));
        element.clear();
        element.sendKeys(password);
    }

    public void loginClick(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(login)).click();
    }

    public boolean checkUsernameError(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(usernameError, text));
    }

    public boolean checkPasswordError(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(passwordError, text));
    }

    public boolean checkError(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(error, text));
    }
}
