package com.rokzasok.ktse2e.isidora_tests.tests;

import com.rokzasok.ktse2e.isidora_tests.pages.AddToMenuPage;
import com.rokzasok.ktse2e.isidora_tests.pages.MenuPage;
import com.rokzasok.ktse2e.isidora_tests.pages.ReportsPage;
import com.rokzasok.ktse2e.isidora_tests.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class FoodMenuTest {
    private static final String BASE_URL = "http://localhost:4200";
    private WebDriver driver;

    private LoginPage loginPage;
    private MenuPage foodMenuPage;
    private ReportsPage reportsPage;
    private AddToMenuPage addToMenuPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver-isidora.exe");

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        foodMenuPage = new MenuPage(driver);
        reportsPage = new ReportsPage(driver);
        addToMenuPage = new AddToMenuPage(driver);

        driver.navigate().to(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }


    public void addAllDishes() {
        int size = foodMenuPage.getNumOfDishSuggestions();
        for (int i = 0; i < size; i++){
            /* TODO add first dish */
            foodMenuPage.addFirstDish();

            /* TODO ADD DISH PAGE */

            assertTrue(this.driver.getCurrentUrl().contains("/add/dish/"));

            int price = 150;
            addToMenuPage.setPriceIN(String.valueOf(price));
            addToMenuPage.clickAdd();

            assertEquals(size - i - 1, foodMenuPage.getNumOfDishSuggestions());
        }
    }

    public void removeOneDish() {
        /* TODO remove first dish in menu */
        foodMenuPage.removeFirst();

        assertTrue(foodMenuPage.noCategories());
    }

    public void addOneDish() {
        /* TODO add first dish */

        String dishToAddName = foodMenuPage.getFirstDishName();
        String dishCat = foodMenuPage.getFirstDishCat();
        foodMenuPage.addFirstDish();

        /* TODO ADD DISH PAGE */

        assertTrue(this.driver.getCurrentUrl().contains("/add/dish/"));

        int price = 150;
        addToMenuPage.setPriceIN(String.valueOf(price));
        addToMenuPage.clickAdd();

        /* TODO FOOD MENU PAGE */
        //assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/manager-menus/food-menu");

        assertFalse(foodMenuPage.noCategories());
        foodMenuPage.openCategory(dishCat.split(" ")[1]);
        boolean dishInMenu = foodMenuPage.checkDishNameInMenuF(dishToAddName.split(" ")[0]);
        boolean priceSame = foodMenuPage.checkDishPriceInMenuF(String.valueOf(price));
        assertTrue(dishInMenu);
        assertTrue(priceSame);
    }

    public void removeAllDishes() {
        while (true) {
            foodMenuPage.removeAllFirsts();
            if (foodMenuPage.noCategories()) {
                break;
            }
        }

        assertTrue(foodMenuPage.noCategories());
    }

    public void deleteMenu() {
        foodMenuPage.deleteClick();
    }

    public void deleteAllMenus() {
        while (true) {
            try {
                deleteMenu();
            } catch (Exception e) {
                System.out.println("Nema sta da se obrise vise");
                break;
            }
        }
    }

    @Test
    public void testCreateNewMenuAddOneDishRemoveOneDish() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO REPORTS PAGE */

        //assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/reports/show/monthly");

        reportsPage.goToFoodMenu();

        /* TODO FOOD MENU PAGE */
        deleteAllMenus();

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/manager-menus/food-menu");

        LocalDate date = LocalDate.now();

        /* TODO create new */

        foodMenuPage.createNewClick();

        assertTrue(foodMenuPage.noCategories());

        assertTrue(foodMenuPage.createdOnDateIsPresent());
        assertTrue(foodMenuPage.isCreatedOnDate(date));

        addOneDish();

        removeOneDish();

        deleteMenu();

    }

    @Test
    public void testCreateNewMenuAddAllRemoveAll() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO REPORTS PAGE */

        //assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/reports/show/monthly");

        reportsPage.goToFoodMenu();

        /* TODO FOOD MENU PAGE */
        deleteAllMenus();

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/manager-menus/food-menu");

        LocalDate date = LocalDate.now();

        /* TODO create new */

        foodMenuPage.createNewClick();

        assertTrue(foodMenuPage.noCategories());

        assertTrue(foodMenuPage.createdOnDateIsPresent());
        assertTrue(foodMenuPage.isCreatedOnDate(date)); // TODO ako ne radi obrisi

        addAllDishes();

        removeAllDishes();

        deleteMenu();
    }

    @After
    public void tearUp() {
        driver.quit();
    }
}
