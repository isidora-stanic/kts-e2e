package com.rokzasok.ktse2e.klimenta_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CookOrdersPage {
    private WebDriver driver;
    //*[@id="invoice"]/div/div/main/table[1]
    @FindBy(xpath = "//*[@id='invoice']/div/div/main/table[1]//*[@id='completeOrder']")
    private List<WebElement> completeOrderButton;

    @FindBy(id = "cook-orders")
    private WebElement myOrders;

    //@FindBy(id = "//*[@id='invoice']/div/div/main/table[1]/tbody")
    private List<WebElement> body;

    public CookOrdersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkNumberOfElements(Integer number){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        body = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id='invoice']/div/div/main/table[1]/tbody"), number));
        return body.size() == number;
    }

    public void myOrdersButtonClick(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(myOrders)).click();
    }

    public void completeFirstOrder(Integer number){
        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        body = wait1.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id='invoice']/div/div/main/table[1]/tbody"), number+1));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(completeOrderButton.get(number))).click();
    }

}
