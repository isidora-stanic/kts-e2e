package com.rokzasok.ktse2e.matija_tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateOrderPage {
    private WebDriver driver;

    private WebElement itemCategoriesCard;

    private List<WebElement> itemCategoryExpandLinks;

    private List<WebElement> orderItem;

    private WebElement confirmOrderButton;

    public CreateOrderPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
