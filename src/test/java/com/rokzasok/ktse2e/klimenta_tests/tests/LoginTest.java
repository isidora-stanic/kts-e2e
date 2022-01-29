package com.rokzasok.ktse2e.klimenta_tests.tests;

import com.rokzasok.ktse2e.klimenta_tests.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private WebDriver browser;

    private LoginPage login;

    @Before
    public void setupSelenium() {
        // instantiate browser
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver-isidora.exe");
        browser = new ChromeDriver();
        this.browser.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        // maximize window
        browser.manage().window().maximize();
        // navigate
        browser.navigate().to("http://localhost:4200");

        login = PageFactory.initElements(browser, LoginPage.class);
    }

    @Test
    public void loginInTest_withoutUsernameAndPassword(){
        login.loginClick();
        assertTrue(login.checkUsernameError("Username is required!"));
        assertTrue(login.checkPasswordError("Password is required!"));
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
    }

    @Test
    public void loginTest_withoutPassword(){
        login.setUsername("admin");
        login.loginClick();
        assertTrue(login.checkPasswordError("Password is required!"));
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
    }

    @Test
    public void loginTest_withoutUsername(){
        login.setPassword("admin");
        login.loginClick();
        assertTrue(login.checkUsernameError("Username is required!"));
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
    }

    @Test
    public void loginTest_invalidUsernameAndPassword() {
        login.setUsername("admin");
        login.setPassword("pogresna sifra");
        login.loginClick();
        assertTrue(login.checkError("Invalid username or password."));
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
    }

    @Test
    public void loginTest_OK() {
        login.setUsername("admin");
        login.setPassword("password");
        login.loginClick();
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
    }

    @After
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}