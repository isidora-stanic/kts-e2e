package com.rokzasok.ktse2e.isidora_tests.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MenuPage {

    private WebDriver driver;

    @FindBy(id = "breakfast")
    private WebElement breakfastCat;

    @FindBy(id = "appetizer")
    private WebElement appetizerCat;

    @FindBy(id = "soup")
    private WebElement soupCat;

    @FindBy(id = "salad")
    private WebElement saladCat;

    @FindBy(id = "main-course")
    private WebElement mainCourseCat;

    @FindBy(id = "dessert")
    private WebElement dessertCat;

    @FindBy(id = "non-alcoholic")
    private WebElement nonAlcoholicCat;

    @FindBy(id = "alcoholic")
    private WebElement alcoholicCat;

    @FindBy(id = "hot-beverage")
    private WebElement hotBeverageCat;

    // todo create new and date created

    @FindBy(id = "create-new")
    private WebElement createNewMenuBTN;

    @FindBy(id = "created-on")
    private WebElement createdOnDate;

    // todo all

    @FindBy(className = "dish-card")
    private List<WebElement> dishCards;

    @FindBy(className = "drink-card")
    private List<WebElement> drinkCards;

    @FindBy(className = "dish-name")
    private List<WebElement> dishNames;

    @FindBy(className = "dish-category")
    private List<WebElement> dishCat;

    @FindBy(className = "dish-add-btn")
    private List<WebElement> dishAddBTNs;

    // todo first

    @FindBy(className = "dish-card")
    private WebElement firstDishCard;

    @FindBy(className = "dish-name")
    private WebElement firstDishName;

    @FindBy(className = "dish-category")
    private WebElement firstDishCat;

    @FindBy(className = "dish-add-btn")
    private WebElement firstDishAddBTN;

    @FindBy(className = "drink-card")
    private WebElement firstDrinkCard;

    @FindBy(className = "drink-name")
    private WebElement firstDrinkName;

    @FindBy(className = "drink-category")
    private WebElement firstDrinkCat;

    @FindBy(className = "drink-add-btn")
    private WebElement firstDrinkAddBTN;

    // todo in menu

    @FindBy(className = "menu-dish-name")
    private List<WebElement> menuDishNames;

    @FindBy(className = "menu-dish-price")
    private List<WebElement> menuDishPrices;

    @FindBy(className = "menu-dish-name")
    private WebElement menuDishNameF;

    @FindBy(className = "menu-dish-price")
    private WebElement menuDishPriceF;

    @FindBy(className = "menu-drink-name")
    private WebElement menuDrinkNameF;

    @FindBy(className = "menu-drink-price")
    private WebElement menuDrinkPriceF;

    @FindBy(id = "empty-menu")
    private WebElement emptyMenuLabel;

    // todo delete

    @FindBy(id = "delete")
    private WebElement deleteBTN;

    @FindBy(className = "remove")
    private WebElement removeBTN;

    // todo waits

    private int categWaitSec;

    public MenuPage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        categWaitSec = 3;
    }

    public boolean noCategories() {
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOf(emptyMenuLabel));
            System.out.println(emptyMenuLabel.getText());
            return emptyMenuLabel.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            return false;
        } catch (Exception e) {
            return false;
        }

        //return !brakfastIsPresent() && !appetizerIsPresent() && !soupIsPresent() && !saladIsPresent() && !mainCourseIsPresent() && !dessertIsPresent();
    }

    public boolean isCreatedOnDate(LocalDate date) {
        String dateString = LocalDate.now().format(DateTimeFormatter.ofPattern("d.M.yyyy."));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(createdOnDate));
        return createdOnDate.getText().equals("created on: "+dateString);
    }

    public boolean createdOnDateIsPresent() {
        try {
            new WebDriverWait(driver, categWaitSec)
                    .until(ExpectedConditions.visibilityOf(createdOnDate));

            return createdOnDate.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void createNewClick() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(createNewMenuBTN));

        createNewMenuBTN.click();
    }

    public void addFirstDish() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(firstDishAddBTN));

        System.out.println(firstDishAddBTN.getText());

        String jsSyyle = "'3px solid red'";
        js.executeScript("arguments[0].style.border=" + jsSyyle, firstDishAddBTN);

        firstDishAddBTN.click();
    }

    public void addFirstDrink() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(firstDrinkAddBTN));

        System.out.println(firstDrinkAddBTN.getText());

        String jsSyyle = "'3px solid red'";
        js.executeScript("arguments[0].style.border=" + jsSyyle, firstDrinkAddBTN);

        firstDrinkAddBTN.click();
    }

    public String getFirstDishName() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(firstDishName));

        System.out.println(firstDishName.getText());

        return firstDishName.getText();
    }

    public String getFirstDrinkName() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(firstDrinkName));

        System.out.println(firstDrinkName.getText());

        return firstDrinkName.getText();
    }

    public String getFirstDishCat() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(firstDishCat));

        System.out.println(firstDishCat.getText());

        return firstDishCat.getText();
    }

    public String getFirstDrinkCat() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(firstDrinkCat));

        System.out.println(firstDrinkCat.getText());

        return firstDrinkCat.getText();
    }

    public void openCategory(String category) {
        WebElement toCLick = null;
        switch (category) {
            case "BREAKFAST":
                toCLick = breakfastCat;
                break;
            case "APPETIZER":
                toCLick = appetizerCat;
                break;
            case "SOUP":
                toCLick = soupCat;
                break;
            case "SALAD":
                toCLick = saladCat;
                break;
            case "MAIN_COURSE":
                toCLick = mainCourseCat;
                break;
            case "DESSERT":
                toCLick = dessertCat;
                break;
            case "NON_ALCOHOLIC":
                toCLick = nonAlcoholicCat;
                break;
            case "ALCOHOLIC":
                toCLick = alcoholicCat;
                break;
            case "HOT_BEVERAGE":
                toCLick = hotBeverageCat;
                break;
            default:
                return;
        }
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(toCLick));

        toCLick.click();
    }

    public boolean checkDishNameInMenuF(String dishToAddName) {
        WebElement dName = menuDishNameF;

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(dName));

        System.out.println(dName.getText());

        return dName.getText().contains(dishToAddName);
    }

    public boolean checkDrinkNameInMenuF(String drinkToAddName) {
        WebElement dName = menuDrinkNameF;

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(dName));

        System.out.println(dName.getText());

        return dName.getText().contains(drinkToAddName);
    }

    public boolean checkDishPriceInMenuF(String dishToAddPrice) {
        WebElement dPrice = menuDishPriceF;

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(dPrice));

        System.out.println(dPrice.getText());

        return dPrice.getText().contains(dishToAddPrice);
    }

    public boolean checkDrinkPriceInMenuF(String drinkToAddPrice) {
        WebElement dPrice = menuDrinkPriceF;

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(dPrice));

        System.out.println(dPrice.getText());

        return dPrice.getText().contains(drinkToAddPrice);
    }

    public int getNumOfDishSuggestions() {
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOf(firstDishCard));
            return this.dishCards.size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public int getNumOfDrinkSuggestions() {
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOf(firstDrinkCard));
            return this.drinkCards.size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public int getNumOfDInMenu() {
        int count = 0;

        try {
            openCategory("BREAKFAST");
            count += menuDishNames.size();
            openCategory("BREAKFAST");
        } catch (Exception e) {

        }

        try {
            openCategory("APPETIZER");
            count += menuDishNames.size();
            openCategory("APPETIZER");
        } catch (Exception e) {

        }

        try {
            openCategory("SOUP");
            count += menuDishNames.size();
            openCategory("SOUP");
        } catch (Exception e) {

        }

        try {
            openCategory("SALAD");
            count += menuDishNames.size();
            openCategory("SALAD");
        } catch (Exception e) {

        }

        try {
            openCategory("MAIN_COURSE");
            count += menuDishNames.size();
            openCategory("MAIN_COURSE");
        } catch (Exception e) {

        }

        try {
            openCategory("DESSERT");
            count += menuDishNames.size();
            openCategory("DESSERT");
        } catch (Exception e) {

        }

        // drink

        try {
            openCategory("NON_ALCOHOLIC");
            count += menuDishNames.size();
            openCategory("NON_ALCOHOLIC");
        } catch (Exception e) {

        }

        try {
            openCategory("ALCOHOLIC");
            count += menuDishNames.size();
            openCategory("ALCOHOLIC");
        } catch (Exception e) {

        }

        try {
            openCategory("HOT_BEVERAGE");
            count += menuDishNames.size();
            openCategory("HOT_BEVERAGE");
        } catch (Exception e) {

        }

        return count;
    }

    public void deleteClick() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(deleteBTN));

        deleteBTN.click();

        driver.switchTo().alert().accept();
    }

    public void removeFirst() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(removeBTN));

        removeBTN.click();

        driver.switchTo().alert().accept();
    }

    public int removeAllFirsts() {
        int count = 0;
        try {
            openCategory("BREAKFAST");
            removeFirst();
            openCategory("BREAKFAST");
            count++;
        } catch (Exception e) {

        }

        try {
            openCategory("APPETIZER");
            removeFirst();
            openCategory("APPETIZER");
            count++;
        } catch (Exception e) {

        }

        try {
            openCategory("SOUP");
            removeFirst();
            openCategory("SOUP");
            count++;
        } catch (Exception e) {

        }

        try {
            openCategory("SALAD");
            removeFirst();
            openCategory("SALAD");
            count++;
        } catch (Exception e) {

        }

        try {
            openCategory("MAIN_COURSE");
            removeFirst();
            openCategory("MAIN_COURSE");
            count++;
        } catch (Exception e) {

        }

        try {
            openCategory("DESSERT");
            removeFirst();
            openCategory("DESSERT");
            count++;
        } catch (Exception e) {

        }

        try {
            openCategory("NON_ALCOHOLIC");
            removeFirst();
            openCategory("NON_ALCOHOLIC");
            count++;
        } catch (Exception e) {

        }

        try {
            openCategory("ALCOHOLIC");
            removeFirst();
            openCategory("ALCOHOLIC");
            count++;
        } catch (Exception e) {

        }

        try {
            openCategory("HOT_BEVERAGE");
            removeFirst();
            openCategory("HOT_BEVERAGE");
            count++;
        } catch (Exception e) {

        }

        return count;
    }

}
