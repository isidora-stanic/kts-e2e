package com.rokzasok.ktse2e.isidora_tests.tests;

import com.rokzasok.ktse2e.isidora_tests.pages.AddToMenuPage;
import com.rokzasok.ktse2e.isidora_tests.pages.LoginPage;
import com.rokzasok.ktse2e.isidora_tests.pages.MenuPage;
import com.rokzasok.ktse2e.isidora_tests.pages.ReportsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddToMenuTest {
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

    public void removeOne() {
        /* TODO remove first in menu */
        drinkMenuPage.removeFirst();

        assertTrue(drinkMenuPage.noCategories());
    }

    public void addOneDrinkAllCases() {
        /* TODO add first drink */

        String drinkToAddName = drinkMenuPage.getFirstDrinkName();
        String drinkCat = drinkMenuPage.getFirstDrinkCat();

        drinkMenuPage.addFirstDrink();

        /* TODO ADD DRINK PAGE */

        assertTrue(this.driver.getCurrentUrl().contains("/add/drink/"));

        System.out.println("Pokusaj unosa nevalidnih karaktera");
        String garbage = "c6b-gn5648f-57--47";
        addToMenuPage.setPriceIN(garbage);
        assertThrows(TimeoutException.class, () -> addToMenuPage.clickAdd(), "Ne moze da doda pice sa nevalidnom cenom");

        System.out.println("Pokusaj unosa negativne cene");
        int priceNegative = -50;
        addToMenuPage.setPriceIN(String.valueOf(priceNegative));
        assertThrows(TimeoutException.class, () -> addToMenuPage.clickAdd(), "Ne moze da doda pice sa negativnom cenom");


        System.out.println("Pokusaj unosa cene 0");
        int priceZero = 0;
        addToMenuPage.setPriceIN(String.valueOf(priceZero));
        assertThrows(TimeoutException.class, () -> addToMenuPage.clickAdd(), "Ne moze da doda pice sa cenom 0");

        System.out.println("Pokusaj unosa pozitivne cene");
        int pricePositive = 150;
        addToMenuPage.setPriceIN(String.valueOf(pricePositive));
        addToMenuPage.clickAdd();

        /* TODO DRINK MENU PAGE */

        assertFalse(drinkMenuPage.noCategories());
        drinkMenuPage.openCategory(drinkCat.split(" ")[1]);
        boolean drinkInMenu = drinkMenuPage.checkDrinkNameInMenuF(drinkToAddName.split(" ")[0]);
        boolean priceSame = drinkMenuPage.checkDrinkPriceInMenuF(String.valueOf(pricePositive));
        assertTrue(drinkInMenu);
        assertTrue(priceSame);

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
    public void testAddOneDrinkAllCases() throws InterruptedException {
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

        /* TODO create new */

        drinkMenuPage.createNewClick();

        addOneDrinkAllCases();

        removeOne();

        deleteMenu();

    }

    @After
    public void tearUp() {
        driver.quit();
    }
}
