package com.rokzasok.ktse2e.matija_tests.tests;

import com.rokzasok.ktse2e.matija_tests.pages.LoginPage;
import com.rokzasok.ktse2e.matija_tests.pages.WaiterMainPage;
import com.rokzasok.ktse2e.matija_tests.pages.WaiterOrdersPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class FinishDeleteOrderTest {
    private static final String BASE_URL = "http://localhost:4200";

    private WebDriver driver;

    private LoginPage loginPage;
    private WaiterMainPage waiterMainPage;
    private WaiterOrdersPage waiterOrdersPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver");

        driver = new FirefoxDriver();
        loginPage = new LoginPage(driver);
        waiterMainPage = new WaiterMainPage(driver);
        waiterOrdersPage = new WaiterOrdersPage(driver);

        this.driver.navigate().to(BASE_URL);
    }

    @Test
    public void testFinishOrder() throws InterruptedException {
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("Trcika");
        loginPage.setPassword("password");
        loginPage.doLogin();

        assertEquals(driver.getCurrentUrl(), BASE_URL + "/waiter-tables");

        waiterMainPage.goToWaiterOrders();

        assertEquals(driver.getCurrentUrl(), BASE_URL + "/orders/waiter-orders");

        int initialNumberOfOrders = waiterOrdersPage.getNumberOfOrders();
        String initialLatestOrderStatus = waiterOrdersPage.getLatestOrderStatus();

        assertEquals("NOT_FINISHED", initialLatestOrderStatus);

        waiterOrdersPage.finishLatestOrder();

        driver.navigate().refresh();

        String finalLatestOrderStatus = waiterOrdersPage.getLatestOrderStatus();

        assertEquals("FINISHED", finalLatestOrderStatus);

        waiterOrdersPage.deleteLatestOrder();

        driver.navigate().refresh();
        int finalNumberOfOrders = waiterOrdersPage.getNumberOfOrders();

        assertEquals(initialNumberOfOrders-1, finalNumberOfOrders);
    }
}
