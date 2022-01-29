package com.rokzasok.ktse2e.klimenta_tests.tests;

import com.rokzasok.ktse2e.klimenta_tests.pages.CookOrdersPage;
import com.rokzasok.ktse2e.klimenta_tests.pages.LoginPage;
import com.rokzasok.ktse2e.klimenta_tests.pages.NewCookOrders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CookOrdersTest {
    private WebDriver browser;

    private LoginPage login;
    private NewCookOrders newCookOrders;
    private CookOrdersPage cookOrders;

    @Before
    public void setupSelenium() {
        // instantiate browser
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver-isidora.exe");
        browser = new ChromeDriver();
        this.browser.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        // maximize window
        browser.manage().window().maximize();
        // navigate
        browser.navigate().to("http://localhost:4200/");

        login = PageFactory.initElements(browser, LoginPage.class);
        newCookOrders = PageFactory.initElements(browser, NewCookOrders.class);
        cookOrders = PageFactory.initElements(browser, CookOrdersPage.class);
        login.setUsername("kuvarko");
        login.setPassword("password");
        login.loginClick();
    }


    @Test
    public void accept_complete_order_test(){
        newCookOrders.newCookOrders();
        newCookOrders.acceptFirstOrder(2);
        assertTrue(newCookOrders.checkNumberOfElements(2));

        newCookOrders.acceptFirstOrder(1);
        assertTrue(newCookOrders.checkNumberOfElements(1));

        newCookOrders.acceptFirstOrder(0);
        assertTrue(newCookOrders.checkNumberOfElements(0));
        assertTrue(newCookOrders.checkMessage());

        cookOrders.myOrdersButtonClick();
        assertEquals("http://localhost:4200/orders/order-list/3", browser.getCurrentUrl());

        cookOrders.completeFirstOrder(2);
        assertTrue(cookOrders.checkNumberOfElements(2));
        cookOrders.completeFirstOrder(1);
        assertTrue(cookOrders.checkNumberOfElements(1));
        cookOrders.completeFirstOrder(0);
        assertTrue(cookOrders.checkNumberOfElements(1)); //jer ce tabela 2 postati tabela 1

        //cookOrders.completeFirstOrder(0);
        //assertTrue(newCookOrders.checkMessage());
    }

    @After
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
