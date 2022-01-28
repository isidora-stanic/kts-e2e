package com.rokzasok.ktse2e.matija_tests.tests;

import com.rokzasok.ktse2e.matija_tests.Utils;
import com.rokzasok.ktse2e.matija_tests.pages.LoginPage;
import com.rokzasok.ktse2e.matija_tests.pages.RenewPasswordPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class RenewPasswordTest {
    private static final String BASE_URL = "http://localhost:4200";
    private static final String HASHED_USER_PASSWORD = "$2a$10$puaZa6SuasjiagmJJi6Dtecz7cxA3HuXJUzcqlana.SO.U22uXOJq";
    private static final String USER_USERNAME = "Benko";
    private static final String OLD_USER_PASSWORD = "password";
    private static final String NEW_USER_PASSWORD = "new_password";

    private WebDriver driver;

    private RenewPasswordPage renewPasswordPage;
    private LoginPage loginPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver");

        driver = new FirefoxDriver();
        renewPasswordPage = new RenewPasswordPage(driver);
        loginPage = new LoginPage(driver);

        this.driver.navigate().to(BASE_URL + "/change-password/" + HASHED_USER_PASSWORD);
    }

    @Test
    public void test() throws InterruptedException {
        renewPasswordPage.setUsername(USER_USERNAME);
        renewPasswordPage.setPassword(NEW_USER_PASSWORD);
        renewPasswordPage.submitPasswordChange();

        Utils.waitForAlert(driver, 5);
        Alert success = driver.switchTo().alert();
        success.dismiss();

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername(USER_USERNAME);
        loginPage.setPassword(NEW_USER_PASSWORD);
        loginPage.doLogin();

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/waiter-tables");
    }
}

