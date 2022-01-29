package com.rokzasok.ktse2e.jovan_tests.tests;

import com.rokzasok.ktse2e.isidora_tests.pages.ReportsPage;
import com.rokzasok.ktse2e.jovan_tests.pages.DishPage;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DishTest {
    private WebDriver browser;
    private LoginPage loginPage;
    private DishPage dishPage;
    private ReportsPage reportsPage;
    private MenuPageExtended menuPage;

    private static final String BASE_URL = "http://localhost:4200/";

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


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver-jovan");
        browser = new ChromeDriver();

        loginPage = PageFactory.initElements(browser, LoginPage.class);
        dishPage = PageFactory.initElements(browser, DishPage.class);
        reportsPage = PageFactory.initElements(browser, ReportsPage.class);
        menuPage = PageFactory.initElements(browser, MenuPageExtended.class);

        browser.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        browser.manage().window().maximize();
        browser.navigate().to(BASE_URL);
    }

    @Test
    public void shouldSuccessfullyAddNewDish() {
        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        reportsPage.goToFoodMenu();

        menuPage.clickAddDishButton();

        String name = generateRandomString();
        String code = generateRandomString();
        String allergens = generateRandomString();
        String recipe = generateRandomString();
        String ingredients = generateRandomString();
        String description = generateRandomString();


        dishPage.setName(name);
        dishPage.setCode(code);
        dishPage.setCategory("Doručak");
        dishPage.setAllergens(allergens);
        dishPage.setRecipe(recipe);
        dishPage.setIngredients(ingredients);
        dishPage.setPreparationTime("10");
        dishPage.setPreparationPrice("100");
        dishPage.setDescription(description);
        dishPage.setImagePath("https://via.placeholder.com/350x150");

        dishPage.submitBtnClick();

        String lastDishName = menuPage.getLastDishName();
        String lastDishCategory = menuPage.getLastDishCategory();
        String lastDishAllergens = menuPage.getLastDishAllergens();
        String lastDishRecipe = menuPage.getLastDishRecipe();
        String lastDishIngredients = menuPage.getLastDishIngredients();

        assertTrue(lastDishName.contains(name));
        assertTrue(lastDishName.contains(code));
        assertTrue(lastDishCategory.contains("BREAKFAST"));
        assertTrue(lastDishAllergens.contains(allergens));
        assertTrue(lastDishRecipe.contains(recipe));
        assertTrue(lastDishIngredients.contains(ingredients));

    }

    @Test
    public void shouldShowAlert() {
        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        reportsPage.goToFoodMenu();

        menuPage.clickAddDishButton();

        String name = generateRandomString();

        dishPage.setName(name);

        dishPage.submitBtnClick();
        new WebDriverWait(browser, 5).until(ExpectedConditions.alertIsPresent());
        Alert message = browser.switchTo().alert();
        assertTrue(message.getText().contains("Loš unos"));

    }

    @Test
    public void shouldSuccessfullyEditDish() {
        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        reportsPage.goToFoodMenu();

        menuPage.openCategory("BREAKFAST");
        menuPage.clickFirstEditButton();

        String name = generateRandomString();

        dishPage.setName(name);
        dishPage.submitBtnClick();

        menuPage.openCategory("BREAKFAST");
        assertTrue(menuPage.checkDishNameInMenuF(name));
    }

    @After
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }

}
