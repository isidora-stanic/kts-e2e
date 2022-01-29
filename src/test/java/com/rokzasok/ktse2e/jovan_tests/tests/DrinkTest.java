package com.rokzasok.ktse2e.jovan_tests.tests;

import com.rokzasok.ktse2e.isidora_tests.pages.ReportsPage;
import com.rokzasok.ktse2e.jovan_tests.pages.DrinkPage;
import com.rokzasok.ktse2e.jovan_tests.pages.MenuPageExtended;
import com.rokzasok.ktse2e.matija_tests.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class DrinkTest {

    private WebDriver browser;
    private LoginPage loginPage;
    private DrinkPage drinkPage;
    private ReportsPage reportsPage;
    private MenuPageExtended menuPage;


    private static final String BASE_URL = "http://localhost:4200/";

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver-jovan");
        browser = new ChromeDriver();

        loginPage = PageFactory.initElements(browser, LoginPage.class);
        drinkPage = PageFactory.initElements(browser, DrinkPage.class);
        reportsPage = PageFactory.initElements(browser, ReportsPage.class);
        menuPage = PageFactory.initElements(browser, MenuPageExtended.class);

        browser.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        browser.manage().window().maximize();
        browser.navigate().to(BASE_URL);
    }


    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

    @Test
    public void shouldSuccessfullyAddNewDrink() {
        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        reportsPage.goToDrinkMenu();

        menuPage.clickAddDrinkButton();

        String name = generateRandomString();
        String code = generateRandomString();
        String allergens = generateRandomString();
        String ingredients = generateRandomString();
        String description = generateRandomString();

        drinkPage.setName(name);
        drinkPage.setCode(code);
        drinkPage.setCategory("Bezalkoholna pića");
        drinkPage.setAllergens(allergens);
        drinkPage.setIngredients(ingredients);
        drinkPage.setPreparationPrice("100");
        drinkPage.setDescription(description);
        drinkPage.setImagePath("https://via.placeholder.com/350x150");


        drinkPage.submitBtnClick();

        String lastDrinkName = menuPage.getLastDrinkName();
        String lastDrinkCategory = menuPage.getLastDrinkCategory();
        String lastDrinkAllergens = menuPage.getLastDrinkAllergens();
        String lastDrinkIngredients = menuPage.getLastDrinkIngredients();

        assertTrue(lastDrinkName.contains(name));
        assertTrue(lastDrinkName.contains(code));
        assertTrue(lastDrinkCategory.contains("NON_ALCOHOLIC"));
        assertTrue(lastDrinkAllergens.contains(allergens));
        assertTrue(lastDrinkIngredients.contains(ingredients));

    }

    public void shouldShowAlert() {
        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        reportsPage.goToFoodMenu();

        menuPage.clickAddDishButton();

        String name = generateRandomString();

        drinkPage.setName(name);

        drinkPage.submitBtnClick();
        new WebDriverWait(browser, 5).until(ExpectedConditions.alertIsPresent());
        Alert message = browser.switchTo().alert();
        assertTrue(message.getText().contains("Loš unos"));

    }

    @Test
    public void shouldSuccessfullyEditDrink() {
        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        reportsPage.goToDrinkMenu();

        menuPage.openCategory("NON_ALCOHOLIC");
        menuPage.clickFirstEditButton();

        String name = generateRandomString();

        drinkPage.setName(name);
        drinkPage.submitBtnClick();

        menuPage.openCategory("NON_ALCOHOLIC");
        assertTrue(menuPage.checkDrinkNameInMenuF(name));
    }




    @After
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }

}
