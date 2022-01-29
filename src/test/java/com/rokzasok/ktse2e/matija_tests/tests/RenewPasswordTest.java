package com.rokzasok.ktse2e.matija_tests.tests;

import com.rokzasok.ktse2e.matija_tests.Utils;
import com.rokzasok.ktse2e.matija_tests.pages.LoginPage;
import com.rokzasok.ktse2e.matija_tests.pages.RenewPasswordPage;
import com.rokzasok.ktse2e.matija_tests.pages.WaiterMainPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RenewPasswordTest {
    private static final String BASE_URL = "http://localhost:4200";
    private static final String HASHED_USER_PASSWORD = "$2a$10$puaZa6SuasjiagmJJi6Dtecz7cxA3HuXJUzcqlana.SO.U22uXOJq";
    private static final String USER_USERNAME = "Benko";
    private static final String INCORRECT_HASHED_USER_PASSWORD = "incorrect_password_hash_1234567890";
    private static final String INCORRECT_USER_USERNAME = "SomeNonExistingUsername";
    private static final String OLD_USER_PASSWORD = "password";
    private static final String NEW_USER_PASSWORD = "new_password";

    private static final String INCORRECT_LINK_ERROR
            = "Password renewal failed: Expired or incorrect password renewal link";

    private static final String INCORRECT_USERNAME_ERROR
            = "Password renewal failed: User with such username not found";

    private WebDriver driver;

    private RenewPasswordPage renewPasswordPage;
    private LoginPage loginPage;
    private WaiterMainPage waiterMainPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver");

        driver = new FirefoxDriver();

        renewPasswordPage = new RenewPasswordPage(driver);
        loginPage = new LoginPage(driver);
        waiterMainPage = new WaiterMainPage(driver);

        this.driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws InterruptedException {
        this.driver.navigate().to(BASE_URL + "/user-actions/change-password/"
                + INCORRECT_HASHED_USER_PASSWORD);

        renewPasswordPage.setUsername(USER_USERNAME);
        renewPasswordPage.setPassword(NEW_USER_PASSWORD);
        renewPasswordPage.submitPasswordChange();

        assertEquals(INCORRECT_LINK_ERROR, renewPasswordPage.getErrorMessage());


        this.driver.navigate().to(BASE_URL + "/user-actions/change-password/" + HASHED_USER_PASSWORD);

        renewPasswordPage.setUsername(INCORRECT_USER_USERNAME);
        renewPasswordPage.setPassword(NEW_USER_PASSWORD);
        renewPasswordPage.submitPasswordChange();

        assertEquals(INCORRECT_USERNAME_ERROR, renewPasswordPage.getErrorMessage());


        this.driver.navigate().refresh();

        renewPasswordPage.setUsername(USER_USERNAME);
        renewPasswordPage.setPassword(NEW_USER_PASSWORD);
        renewPasswordPage.submitPasswordChange();

        Utils.waitForAlert(driver, 5);
        Alert success = driver.switchTo().alert();
        success.dismiss();

        loginPage.setUsername(USER_USERNAME);
        loginPage.setPassword(NEW_USER_PASSWORD);
        loginPage.doLogin();

        assertTrue(waiterMainPage.getUsername().contains(USER_USERNAME));
    }
}

