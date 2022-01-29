package com.rokzasok.ktse2e.isidora_tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersPage {

    private WebDriver driver;

    @FindBy(id = "table-editor")
    private WebElement tableEditorLINK;

    public UsersPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToTableEditor() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(tableEditorLINK));

        tableEditorLINK.click();
    }
}
