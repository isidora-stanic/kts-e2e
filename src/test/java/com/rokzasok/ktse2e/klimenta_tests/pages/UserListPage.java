package com.rokzasok.ktse2e.klimenta_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UserListPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='table']/tbody[last()]//*[@id='edit']")
    private WebElement edit;

    @FindBy(xpath = "//*[@id='table']/tbody[last()]//*[@id='delete']")
    private WebElement delete;

    @FindBy(id = "users")
    private WebElement usersButton;

    @FindBy(id = "users-manager")
    private WebElement usersManagerButton;

    @FindBy(xpath = "//*[@id='table']/tbody[last()]//*[@id='name']")
    private WebElement name;

    @FindBy(xpath = "//*[@id='table']/tbody[last()]//*[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//*[@id='table']/tbody[last()]//*[@id='phoneNumber']")
    private WebElement number;

    @FindBy(xpath = "//*[@id='table']/tbody[last()]//*[@id='address']")
    private WebElement address;

    @FindBy(xpath = "//*[@id='table']/tbody[last()]//*[@id='type']")
    private WebElement type;

    @FindBy(xpath = "//*[@id='table']/tbody[last()]//*[@id='salary']")
    private WebElement salary;

    //@FindBy(xpath = "//*[@id='table']/tbody")
    private List<WebElement> tableElements;

    @FindBy(id = "addUser")
    private WebElement addUser;

    public UserListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void usersManagerButtonClick(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(usersManagerButton)).click();
    }

    public void usersButtonClick(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(usersButton)).click();
    }

    public void add(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(addUser)).click();
    }

    public void edit(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(edit)).click();
    }

    public void delete(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(delete)).click();
    }
//------------------------------------
    public boolean checkName(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(name, text));
    }

    public boolean checkEmail(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(email, text));
    }

    public boolean checkNumber(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(number, text));
    }

    public boolean checkAddress(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(address, text));
    }

    public boolean checkType(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(type, text));
    }

    public boolean checkSalary(String text){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(salary, text));
    }

    //----------------------
    public boolean checkNumberOfElements(Integer number){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        tableElements = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id='table']/tbody"), number));
        return tableElements.size() == number;
    }
}
