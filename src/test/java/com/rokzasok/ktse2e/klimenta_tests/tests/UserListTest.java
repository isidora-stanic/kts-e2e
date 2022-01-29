package com.rokzasok.ktse2e.klimenta_tests.tests;

import com.rokzasok.ktse2e.klimenta_tests.pages.AddUserPage;
import com.rokzasok.ktse2e.klimenta_tests.pages.LoginPage;
import com.rokzasok.ktse2e.klimenta_tests.pages.UserListPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserListTest {

    private WebDriver browser;

    private LoginPage login;
    private UserListPage userListPage;
    private AddUserPage addUserPage;

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
        userListPage = PageFactory.initElements(browser, UserListPage.class);
        addUserPage = PageFactory.initElements(browser, AddUserPage.class);

    }


    @Test
    public void test_manager() {
        login.setUsername("managerko");
        login.setPassword("password");
        login.loginClick();
        userListPage.usersManagerButtonClick();
        //assertEquals("http://localhost:4200/users", browser.getCurrentUrl());

        //--------------------------------------------------------------------------------------ADD
        userListPage.add();
        assertEquals("http://localhost:4200/add-edit-user", browser.getCurrentUrl());

        addUserPage.checkType();
        addUserPage.selectCook();
        addUserPage.setFirstName("Ana");
        addUserPage.setLastName("Anic");
        addUserPage.setEmail("manager@maildrop.cc");
        addUserPage.setPhoneNumber("065866999");
        addUserPage.setSalary("700");
        addUserPage.setAddress("Tvrdoski put 7");
        addUserPage.setUsername("sara");
        addUserPage.setImage("-");
        addUserPage.submitClick();

        assertTrue(addUserPage.checkAddMessage("An account with this email or username already exists."));

        addUserPage.setEmail("blaka@maildrop.cc");
        addUserPage.submitClick();
        userListPage.usersManagerButtonClick();
        //
        assertTrue(userListPage.checkName("Ana Anic"));
        assertTrue(userListPage.checkEmail("blaka@maildrop.cc"));
        assertTrue(userListPage.checkNumber("065866999"));
        assertTrue(userListPage.checkAddress("Tvrdoski put 7"));
        assertTrue(userListPage.checkType("COOK"));
        assertTrue(userListPage.checkSalary("700"));

        //--------------------------------------------------------------------------------------EDIT
        userListPage.edit();
        addUserPage.checkType();
        addUserPage.selectCook();
        addUserPage.setFirstName("Tatjana");
        addUserPage.setLastName("Tatjanic");
        addUserPage.setEmail("manager@maildrop.cc");
        addUserPage.setPhoneNumber("065866999");
        addUserPage.setSalary("9900");
        addUserPage.setAddress("Bulevar Despota Stefana 7");
        addUserPage.submitClick();
        assertTrue(addUserPage.checkMessage("An account with this email already exists."));

        addUserPage.setEmail("minja@gmail.com");
        addUserPage.submitClick();
        userListPage.usersManagerButtonClick();
        assertTrue(userListPage.checkName("Tatjana Tatjanic"));
        assertTrue(userListPage.checkEmail("minja@gmail.com"));
        assertTrue(userListPage.checkNumber("065866999"));
        assertTrue(userListPage.checkAddress("Bulevar Despota Stefana 7"));
        assertTrue(userListPage.checkType("COOK"));
        //assertTrue(userListPage.checkSalary("9900"));
        //-----------------------------------------------------------------------------------DELETE
        userListPage.delete();
        assertTrue(userListPage.checkNumberOfElements(7));

        addUserPage.logoutClick();
    }


    @Test
    public void test_admin() {
        login.setUsername("admin");
        login.setPassword("password");
        login.loginClick();
        userListPage.usersButtonClick();
        assertEquals("http://localhost:4200/users", browser.getCurrentUrl());

        //--------------------------------------------------------------------------------------ADD
        userListPage.add();
        assertEquals("http://localhost:4200/add-edit-user", browser.getCurrentUrl());

        addUserPage.checkType();
        addUserPage.selectDirector();
        addUserPage.setFirstName("Ana");
        addUserPage.setLastName("Anic");
        addUserPage.setEmail("manager@maildrop.cc");
        addUserPage.setPhoneNumber("065866999");
        addUserPage.setSalary("700");
        addUserPage.setAddress("Tvrdoski put 7");
        addUserPage.setUsername("lala");
        addUserPage.setImage("fhhfh");
        addUserPage.submitClick();

        assertTrue(addUserPage.checkAddMessage("An account with this email or username already exists."));

        addUserPage.setEmail("ana@maildrop.cc");
        addUserPage.submitClick();
        userListPage.usersButtonClick();
        //
        assertTrue(userListPage.checkName("Ana Anic"));
        assertTrue(userListPage.checkEmail("ana@maildrop.cc"));
        assertTrue(userListPage.checkNumber("065866999"));
        assertTrue(userListPage.checkAddress("Tvrdoski put 7"));
        assertTrue(userListPage.checkType("DIRECTOR"));
        assertTrue(userListPage.checkSalary("700"));

        //--------------------------------------------------------------------------------------EDIT
        userListPage.edit();
        addUserPage.checkType();
        addUserPage.selectDirector();
        addUserPage.setFirstName("Tatjana");
        addUserPage.setLastName("Tatjanic");
        addUserPage.setEmail("manager@maildrop.cc");
        addUserPage.setPhoneNumber("065866999");
        addUserPage.setSalary("9900");
        addUserPage.setAddress("Bulevar Despota Stefana 7");
        addUserPage.submitClick();
        assertTrue(addUserPage.checkMessage("An account with this email already exists."));

        addUserPage.setEmail("taca@gmail.com");
        addUserPage.submitClick();
        assertTrue(userListPage.checkName("Tatjana Tatjanic"));
        assertTrue(userListPage.checkEmail("taca@gmail.com"));
        assertTrue(userListPage.checkNumber("065866999"));
        assertTrue(userListPage.checkAddress("Bulevar Despota Stefana 7"));
        assertTrue(userListPage.checkType("DIRECTOR"));
        //assertTrue(userListPage.checkSalary("9900din"));
        //-----------------------------------------------------------------------------------DELETE
        userListPage.delete();
        assertTrue(userListPage.checkNumberOfElements(4));

        addUserPage.logoutClick();
    }

    @After
    public void closeSelenium() {
        // Shutdown the browser
        //browser.quit();
    }
}
