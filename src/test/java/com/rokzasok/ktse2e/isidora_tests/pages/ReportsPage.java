package com.rokzasok.ktse2e.isidora_tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportsPage {

    private WebDriver driver;

    @FindBy(id = "food-menu")
    private WebElement foodMenuLink;

    @FindBy(id = "drink-menu")
    private WebElement drinkMenuLink;

    public ReportsPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToFoodMenu() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(foodMenuLink));

        foodMenuLink.click();
    }

    public void goToDrinkMenu() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(drinkMenuLink));

        drinkMenuLink.click();
    }
}
