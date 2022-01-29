package com.rokzasok.ktse2e.klimenta_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NewCookOrders {
    private WebDriver driver;

    @FindBy(id = "acceptOrder")
    private List<WebElement> acceptOrderButtonList;

    @FindBy(id = "new-cook-orders")
    private WebElement newOrders;

    @FindBy(id = "empty")
    private WebElement message;

    //@FindBy(id = "//*[@id='invoice']/div/div/main/table[1]/tbody")
    private List<WebElement> body;


    public NewCookOrders(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptFirstOrder(Integer index){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(acceptOrderButtonList.get(index))).click();
    }

    public boolean checkNumberOfElements(Integer number){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        body = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id='invoice']/div/div/main/table[1]/tbody"), number));
        return body.size() == number;
    }

    public void newCookOrders(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(newOrders)).click();
    }

    public boolean checkMessage(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.textToBePresentInElement(message, "Nema novih porudzbina."));
    }
}
