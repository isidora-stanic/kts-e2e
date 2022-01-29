package com.rokzasok.ktse2e.jovan_tests.pages;

import com.rokzasok.ktse2e.isidora_tests.pages.MenuPage;
import com.rokzasok.ktse2e.jovan_tests.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPageExtended extends MenuPage {
    public MenuPageExtended(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "add-dish")
    private WebElement addDishButton;

    @FindBy(id = "add-drink")
    private WebElement addDrinkButton;

    @FindBy(className = "dish-allergens")
    private List<WebElement> dishAllergens;

    @FindBy(className = "dish-ingredients")
    private List<WebElement> dishIngredients;

    @FindBy(className = "dish-recipe")
    private List<WebElement> dishRecipes;

    @FindBy(className = "edit")
    private WebElement editButton;

    @FindBy(className = "drink-category")
    protected List<WebElement> drinkCategories;

    @FindBy(className = "drink-allergens")
    private List<WebElement> drinkAllergens;

    @FindBy(className = "drink-ingredients")
    private List<WebElement> drinkIngredients;

    @FindBy(className = "drink-name")
    private List<WebElement> drinkNames;


    public String getLastDishCategory() {
        Utilities.visibilityWait(driver, firstDishCat, 10);
        WebElement element = dishCat.get(dishCards.size() - 1);
        return element.getText();
    }

    public String getLastDishName() {
        Utilities.visibilityWait(driver, firstDishName, 10);
        WebElement element = dishNames.get(dishCards.size() - 1);
        return element.getText();
    }

    public String getLastDishAllergens() {
        Utilities.visibilityWait(driver, firstDishCard, 10);
        WebElement element = dishAllergens.get(dishCards.size() - 1);
        return element.getText();
    }

    public String getLastDishRecipe() {
        Utilities.visibilityWait(driver, firstDishCard, 10);
        WebElement element = dishRecipes.get(dishCards.size() - 1);
        return element.getText();
    }

    public String getLastDishIngredients() {
        Utilities.visibilityWait(driver, firstDishCard, 10);
        WebElement element = dishIngredients.get(drinkCards.size() - 1);
        return element.getText();
    }

    public String getLastDrinkCategory() {
        Utilities.visibilityWait(driver, firstDrinkCard, 10);
        WebElement element = drinkCategories.get(drinkCards.size() - 1);
        return element.getText();
    }

    public String getLastDrinkName() {
        Utilities.visibilityWait(driver, firstDrinkName, 10);
        WebElement element = drinkNames.get(drinkCards.size() - 1);
        return element.getText();
    }

    public String getLastDrinkAllergens() {
        Utilities.visibilityWait(driver, firstDrinkCard, 10);
        WebElement element = drinkAllergens.get(drinkCards.size() - 1);
        return element.getText();
    }

    public String getLastDrinkIngredients() {
        Utilities.visibilityWait(driver, firstDrinkCard, 10);
        WebElement element = drinkIngredients.get(drinkCards.size() - 1);
        return element.getText();
    }


    public void clickFirstEditButton() {
        Utilities.clickableWait(driver, editButton, 10).click();
    }


    public void clickAddDishButton() {
        Utilities.clickableWait(driver, addDishButton, 10).click();
    }

    public void clickAddDrinkButton() {
        Utilities.clickableWait(driver, addDrinkButton, 10).click();
    }

}
