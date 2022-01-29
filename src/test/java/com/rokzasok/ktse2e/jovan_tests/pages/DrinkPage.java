package com.rokzasok.ktse2e.jovan_tests.pages;

import com.rokzasok.ktse2e.jovan_tests.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DrinkPage {
    private final WebDriver driver;

    public DrinkPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "code")
    private WebElement codeInput;

    @FindBy(id = "category")
    private WebElement categorySelect;

    @FindBy(id = "allergens")
    private WebElement allergensInput;

    @FindBy(id = "ingredients")
    private WebElement ingredientsInput;

    @FindBy(id = "purchasePrice")
    private WebElement purchasePriceInput;

    @FindBy(id = "description")
    private WebElement descriptionTextArea;

    @FindBy(id = "imagePath")
    private WebElement imagePathInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public WebElement getName() {
        return Utilities.visibilityWait(driver, this.nameInput, 10);
    }

    public void setName(String name) {
        WebElement el = getName();
        el.clear();
        el.sendKeys(name);
    }

    public WebElement getCode() {
        return Utilities.visibilityWait(driver, this.codeInput, 10);
    }

    public void setCode(String code) {
        WebElement el = getCode();
        el.clear();
        el.sendKeys(code);
    }

    public WebElement getCategory() {
        return Utilities.visibilityWait(driver, this.categorySelect, 10);
    }

    public void setCategory(String category) {
        WebElement el = getCategory();
        Select select = new Select(el);

        select.selectByVisibleText(category);
    }

    public WebElement getAllergens() {
        return Utilities.visibilityWait(driver, this.allergensInput, 10);
    }

    public void setAllergens(String allergens) {
        WebElement el = getAllergens();
        el.clear();
        el.sendKeys(allergens);
    }

    public WebElement getPurchasePrice() {
        return Utilities.visibilityWait(driver, this.purchasePriceInput, 10);
    }

    public void setPreparationPrice(String preparationPrice) {
        WebElement el = getPurchasePrice();
        el.clear();
        el.sendKeys(preparationPrice);
    }

    public WebElement getDescription() {
        return Utilities.visibilityWait(driver, this.descriptionTextArea, 10);
    }

    public void setDescription(String description) {
        WebElement el = getDescription();
        el.clear();
        el.sendKeys(description);
    }

    public WebElement getImagePath() {
        return Utilities.visibilityWait(driver, this.imagePathInput, 10);
    }

    public void setImagePath(String imagePath) {
        WebElement el = getImagePath();
        el.clear();
        el.sendKeys(imagePath);
    }

    public void submitBtnClick() {
        Utilities.clickableWait(driver, this.submitButton, 10).click();
    }

    public WebElement getIngredients() {
        return Utilities.visibilityWait(driver, this.ingredientsInput, 10);
    }

    public void setIngredients(String ingredients) {
        WebElement el = getIngredients();
        el.clear();
        el.sendKeys(ingredients);
    }


}
