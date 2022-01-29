package com.rokzasok.ktse2e.klimenta_tests.tests;

import com.rokzasok.ktse2e.klimenta_tests.pages.BartenderOrdersPage;
import com.rokzasok.ktse2e.klimenta_tests.pages.LoginPage;
import com.rokzasok.ktse2e.klimenta_tests.pages.NewBartenderOrdersPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BartenderOrdersTest {
    private WebDriver browser;

    private LoginPage login;
    private BartenderOrdersPage bartenderOrdersPage;
    private NewBartenderOrdersPage newBartenderOrdersPage;

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
        bartenderOrdersPage = PageFactory.initElements(browser, BartenderOrdersPage.class);
        newBartenderOrdersPage = PageFactory.initElements(browser, NewBartenderOrdersPage.class);
        login.setUsername("Gonko");
        login.setPassword("password");
        login.loginClick();
    }


    @Test
    public void accept_complete_order_test(){
        newBartenderOrdersPage.newCookOrders();
        newBartenderOrdersPage.acceptFirstOrder(0);
        assertTrue(newBartenderOrdersPage.checkNumberOfElements(0));
        assertTrue(newBartenderOrdersPage.checkMessage());

        bartenderOrdersPage.myOrdersButtonClick();
        bartenderOrdersPage.completeFirstOrder(0);
        assertTrue(bartenderOrdersPage.checkNumberOfElements(0));
        assertTrue(newBartenderOrdersPage.checkMessage());
    }

    @After
    public void closeSelenium() {
        // Shutdown the browser
        //browser.quit();
    }
}
