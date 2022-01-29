package com.rokzasok.ktse2e.klimenta_tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUserPage {

    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "imagePath")
    private WebElement image;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(xpath = "//*[@id='type']")
    private WebElement type;

    @FindBy(xpath = "//*[@id='type']/*[@id='director']")
    private WebElement managerType;

    @FindBy(xpath = "//*[@id='type']/*[@id='cook']")
    private WebElement cookType;

    @FindBy(id = "salary")
    private WebElement salary;

    @FindBy(id = "phoneNumber")
    private WebElement phoneNumber;

    @FindBy(id = "submit")
    private WebElement submit;

    @FindBy(id = "errorMessage")
    private WebElement errorMessage;

    @FindBy(id = "errorMessageAdd")
    private WebElement errorAddMessage;

    @FindBy(id = "logout")
    private WebElement logout;

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
    }


    public void logoutClick(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(logout)).click();
    }

    public void checkType(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(type)).click();
    }

    public void selectDirector(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(managerType)).click();
    }

    public void selectCook(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(cookType)).click();
    }

    public void setSalary(String newSalary){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(salary));
        el.clear();
        el.sendKeys(newSalary);
    }
    //-----------------
    public void setFirstName(String newName){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(firstName));
        el.clear();
        el.sendKeys(newName);
    }

    public void setLastName(String newLastName){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(lastName));
        el.clear();
        el.sendKeys(newLastName);
    }

    public void setEmail(String newEmail){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(email));
        el.clear();
        el.sendKeys(newEmail);
    }

    public void setPhoneNumber(String newPhoneNumber){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(phoneNumber));
        el.clear();
        el.sendKeys(newPhoneNumber);
    }

    public void setAddress(String newAddress){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(address));
        el.clear();
        el.sendKeys(newAddress);
    }

    public void setUsername(String newUsername){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(username));
        el.clear();
        el.sendKeys(newUsername);
    }

    public void setImage(String newImage){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.visibilityOf(image));
        el.clear();
        el.sendKeys(newImage);
    }

    public void submitClick(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(submit)).click();
    }

    public boolean checkMessage(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(errorMessage, text));
    }

    public boolean checkAddMessage(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(errorAddMessage, text));
    }
}
