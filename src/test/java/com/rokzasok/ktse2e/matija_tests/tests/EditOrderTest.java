package com.rokzasok.ktse2e.matija_tests.tests;

import com.rokzasok.ktse2e.matija_tests.pages.EditOrderPage;
import com.rokzasok.ktse2e.matija_tests.pages.LoginPage;
import com.rokzasok.ktse2e.matija_tests.pages.WaiterMainPage;
import com.rokzasok.ktse2e.matija_tests.pages.WaiterOrdersPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EditOrderTest {
    private static final String BASE_URL = "http://localhost:4200";

    private WebDriver driver;

    private LoginPage loginPage;
    private WaiterMainPage waiterMainPage;
    private WaiterOrdersPage waiterOrdersPage;
    private EditOrderPage editOrderPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver");

        driver = new FirefoxDriver();
        loginPage = new LoginPage(driver);
        waiterMainPage = new WaiterMainPage(driver);
        waiterOrdersPage = new WaiterOrdersPage(driver);
        editOrderPage = new EditOrderPage(driver);

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.navigate().to(BASE_URL);
    }

    @Test
    public void test() throws InterruptedException {
        //assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("Trcika");
        loginPage.setPassword("password");
        loginPage.doLogin();

        //assertEquals(driver.getCurrentUrl(), BASE_URL + "/waiter-tables");

        waiterMainPage.goToWaiterOrders();

        //assertEquals(driver.getCurrentUrl(), BASE_URL + "/orders/waiter-orders");

        int initialOrderItems = waiterOrdersPage.getNumberOfLatestOrderItems();

        waiterOrdersPage.goToEditLatestOrder();

        //assertTrue(driver.getCurrentUrl().contains(BASE_URL + "/orders/edit/"));

        int initialItemsNumber = editOrderPage.getAddedItemsNumber();

        assertEquals(initialOrderItems, initialItemsNumber);

        editOrderPage.removeOne();

        int finalItemsNumber = editOrderPage.getAddedItemsNumber();

        assertEquals(initialItemsNumber-1, finalItemsNumber);

        editOrderPage.confirmEdit();

        //assertEquals(driver.getCurrentUrl(), BASE_URL + "/orders/waiter-orders");

        int finalOrderItems = waiterOrdersPage.getNumberOfLatestOrderItems();

        assertEquals(initialOrderItems-1, finalOrderItems);
    }
}
