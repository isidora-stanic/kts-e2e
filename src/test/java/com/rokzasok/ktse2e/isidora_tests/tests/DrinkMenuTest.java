package com.rokzasok.ktse2e.isidora_tests.tests;

import com.rokzasok.ktse2e.isidora_tests.pages.AddToMenuPage;
import com.rokzasok.ktse2e.isidora_tests.pages.MenuPage;
import com.rokzasok.ktse2e.isidora_tests.pages.LoginPage;
import com.rokzasok.ktse2e.isidora_tests.pages.ReportsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class DrinkMenuTest {
    private static final String BASE_URL = "http://localhost:4200";
    private WebDriver driver;

    private LoginPage loginPage;
    private MenuPage drinkMenuPage;
    private ReportsPage reportsPage;
    private AddToMenuPage addToMenuPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver-isidora.exe");

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        drinkMenuPage = new MenuPage(driver);
        reportsPage = new ReportsPage(driver);
        addToMenuPage = new AddToMenuPage(driver);

        driver.navigate().to(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }


    public void addAllDrinks() {
        int size = drinkMenuPage.getNumOfDrinkSuggestions();
        for (int i = 0; i < size; i++){
            /* TODO add first drink */
            drinkMenuPage.addFirstDrink();

            /* TODO ADD DRINK PAGE */

            assertTrue(this.driver.getCurrentUrl().contains("/add/drink/"));

            int price = 150;
            addToMenuPage.setPriceIN(String.valueOf(price));
            addToMenuPage.clickAdd();

            assertEquals(size - i - 1, drinkMenuPage.getNumOfDrinkSuggestions());
        }
    }

    public void removeOne() {
        /* TODO remove first in menu */
        drinkMenuPage.removeFirst();

        assertTrue(drinkMenuPage.noCategories());
    }

    public void addOneDrink() {
        /* TODO add first drink */

        String drinkToAddName = drinkMenuPage.getFirstDrinkName();
        String drinkCat = drinkMenuPage.getFirstDrinkCat();
        drinkMenuPage.addFirstDrink();

        /* TODO ADD DRINK PAGE */

        assertTrue(this.driver.getCurrentUrl().contains("/add/drink/"));

        int price = 150;
        addToMenuPage.setPriceIN(String.valueOf(price));
        addToMenuPage.clickAdd();

        /* TODO DRINK MENU PAGE */
        //assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/manager-menus/drink-menu");

        assertFalse(drinkMenuPage.noCategories());
        drinkMenuPage.openCategory(drinkCat.split(" ")[1]);
        boolean drinkInMenu = drinkMenuPage.checkDrinkNameInMenuF(drinkToAddName.split(" ")[0]);
        boolean priceSame = drinkMenuPage.checkDrinkPriceInMenuF(String.valueOf(price));
        assertTrue(drinkInMenu);
        assertTrue(priceSame);
    }

    public void removeAll() {
        while (true) {
            drinkMenuPage.removeAllFirsts();
            if (drinkMenuPage.noCategories()) {
                break;
            }
        }

        assertTrue(drinkMenuPage.noCategories());
    }

    public void deleteMenu() {
        drinkMenuPage.deleteClick();
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
    public void testCreateNewMenuAddOneDrinkRemoveOneDrink() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO REPORTS PAGE */

        //assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/reports/show/monthly");

        reportsPage.goToDrinkMenu();

        /* TODO DRINK MENU PAGE */

        deleteAllMenus();

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/manager-menus/drink-menu");

        LocalDate date2 = LocalDate.now();

        /* TODO create new */

        drinkMenuPage.createNewClick();

        assertTrue(drinkMenuPage.noCategories());

        assertTrue(drinkMenuPage.createdOnDateIsPresent());
        assertTrue(drinkMenuPage.isCreatedOnDate(date2));

        addOneDrink();

        removeOne();

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

        reportsPage.goToDrinkMenu();

        /* TODO DRINK MENU PAGE */
        deleteAllMenus();

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/manager-menus/drink-menu");

        LocalDate date = LocalDate.now();

        /* TODO create new */

        drinkMenuPage.createNewClick();

        assertTrue(drinkMenuPage.noCategories());

        assertTrue(drinkMenuPage.createdOnDateIsPresent());
        assertTrue(drinkMenuPage.isCreatedOnDate(date));

        addAllDrinks();

        removeAll();

        deleteMenu();
    }

    @After
    public void tearUp() {
        driver.quit();
    }

}
