package com.rokzasok.ktse2e.matija_tests.tests;

import com.rokzasok.ktse2e.matija_tests.pages.CreateOrderPage;
import com.rokzasok.ktse2e.matija_tests.pages.LoginPage;
import com.rokzasok.ktse2e.matija_tests.pages.WaiterMainPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class CreateOrderTest {
    private static final String BASE_URL = "http://localhost:4200";
    private WebDriver driver;

    private LoginPage loginPage;
    private WaiterMainPage waiterMainPage;
    private CreateOrderPage createOrderPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver");

        driver = new FirefoxDriver();
        loginPage = new LoginPage(driver);
        waiterMainPage = new WaiterMainPage(driver);
        createOrderPage = new CreateOrderPage(driver);

        this.driver.navigate().to(BASE_URL);
    }

    @Test
    public void testCreateOrder() throws InterruptedException {
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("Trcika");
        loginPage.setPassword("password");
        loginPage.doLogin();

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/waiter-tables");

        String tableId = waiterMainPage.getLastTableId();
        waiterMainPage.orderForLastTable();

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/orders/create/at/" + tableId);

        createOrderPage.expandAllCategories();

        int totalItems = createOrderPage.numberOfTotalItems();

        createOrderPage.addAllItems();

        int addedItems = createOrderPage.numberOfAddedItems();
        int sumOfPrices = createOrderPage.getSumOfItemPrices();
        int totalPrice = createOrderPage.getTotalPrice();

        assertEquals(totalItems, addedItems);
        assertEquals(totalPrice, sumOfPrices);

        createOrderPage.confirmOrder("Neki message");

        String successMessage = createOrderPage.waitForOrderConfirmation();

        assertEquals("Order successfully saved", successMessage);
    }

}
