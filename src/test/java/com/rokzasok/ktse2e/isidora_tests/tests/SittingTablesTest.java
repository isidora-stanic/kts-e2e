package com.rokzasok.ktse2e.isidora_tests.tests;

import com.rokzasok.ktse2e.isidora_tests.pages.*;
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

public class SittingTablesTest {

    private static final String BASE_URL = "http://localhost:4200";
    private WebDriver driver;

    private LoginPage loginPage;
    private TablesPage tablesPage;
    private UsersPage usersPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver-isidora.exe");

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        tablesPage = new TablesPage(driver);
        usersPage = new UsersPage(driver);

        driver.navigate().to(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @Test
    public void testWaiterClickOnTable() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("potrcko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO WAITER TABLES PAGE */

        tablesPage.clickFirstTable();

        assertTrue(this.driver.getCurrentUrl().contains(BASE_URL + "/orders/create/at/"));

        driver.navigate().back();

    }

    @Test
    public void testAdminClickOnTableCheckForm() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("admin");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO USERS PAGE */

        usersPage.goToTableEditor();
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/admin-tables");

        /* TODO ADMIN TABLES PAGE */

        tablesPage.clickFirstTable();

        // todo check data in form and on table

        String sTableName = tablesPage.getSelectedTableName();
        String sTableNameInForm = tablesPage.getNameINValue();

        assertEquals(sTableName, sTableNameInForm);
    }

    @Test
    public void testAdminEditFormAllSemanticCases() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("admin");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO USERS PAGE */

        usersPage.goToTableEditor();
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/admin-tables");

        /* TODO ADMIN TABLES PAGE */

        tablesPage.clickFirstTable();

        // todo change one table

        String tableName = "Table 55";
        String xValue = "1";
        String yValue = "1";

        tablesPage.setNameIN(tableName);

        assertTrue(tablesPage.warnIsPresent("unsaved"));

        tablesPage.setXIN(xValue);
        tablesPage.setYIN(yValue);
        tablesPage.clickSave();

        //assertFalse(tablesPage.warnIsPresent("unsaved"));

        // todo change another table to have same name, try to save, check message - same name

        tablesPage.clickNotSelectedTable();

        tablesPage.setNameIN(tableName);

        assertTrue(tablesPage.warnIsPresent("unsaved"));
        assertTrue(tablesPage.warnIsPresent("same name"));
        assertThrows(TimeoutException.class, () -> tablesPage.clickSave(), "Ne moze se sacuvati sto sa istim imenom kao neki drugi sto");

        // todo change another table to have same name, same coordinates, try to save, check messages - same name, same coord

        tablesPage.setXIN(xValue);
        tablesPage.setYIN(yValue);

        assertTrue(tablesPage.warnIsPresent("unsaved"));
        assertTrue(tablesPage.warnIsPresent("same name"));
        assertTrue(tablesPage.warnIsPresent("same coord"));
        assertThrows(TimeoutException.class, () -> tablesPage.clickSave(), "Ne moze se sacuvati sto sa istim koordinatama kao neki drugi sto");

        // todo change another table to have different name, same coordinates, try to save, check messages - same coord
        tablesPage.setNameIN("Another " + tableName);

        assertTrue(tablesPage.warnIsPresent("unsaved"));
        //assertFalse(tablesPage.warnIsPresent("same name"));
        assertTrue(tablesPage.warnIsPresent("same coord"));
        assertThrows(TimeoutException.class, () -> tablesPage.clickSave(), "Ne moze se sacuvati sto sa istim imenom kao neki drugi sto");

        // todo change another table to have different name, different coordinates, try to save, check messages - no messages

        tablesPage.setXIN("2");
        tablesPage.setYIN("2");

        assertTrue(tablesPage.warnIsPresent("unsaved"));
        //assertFalse(tablesPage.warnIsPresent("same name"));
        //assertFalse(tablesPage.warnIsPresent("same coord"));
        tablesPage.clickSave();

        assertFalse(tablesPage.warnIsPresent("unsaved"));

    }

    @Test
    public void testAdminEditFormAllSyntaxCases() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("admin");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO USERS PAGE */

        usersPage.goToTableEditor();
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/admin-tables");

        /* TODO ADMIN TABLES PAGE */

        tablesPage.clickFirstTable();

        // todo change one table

        String tableName = "New Table 55";
        String xValue = "1";
        String yValue = "1";

        // todo no name, x, y < 0

        tablesPage.setNameIN(" ");
        assertTrue(tablesPage.warnIsPresent("name"));
        tablesPage.setXIN("-3");
        assertTrue(tablesPage.warnIsPresent("x"));
        tablesPage.setYIN("-3");
        assertTrue(tablesPage.warnIsPresent("y"));
        assertThrows(TimeoutException.class, () -> tablesPage.clickSave(), "Ne moze se sacuvati sto sa praznim nevalidnim poljima");

        // todo x, y > max values (10, 5)

        tablesPage.setNameIN(tableName);
        assertTrue(tablesPage.warnIsPresent("unsaved"));
        assertFalse(tablesPage.warnIsPresent("name"));

        tablesPage.setXIN("16");
        assertTrue(tablesPage.warnIsPresent("x"));
        tablesPage.setYIN("16");
        assertTrue(tablesPage.warnIsPresent("y"));
        assertThrows(TimeoutException.class, () -> tablesPage.clickSave(), "Ne moze se sacuvati sto sa vecim koordinatama od maksimalnih");

        // todo happy flow :)
        tablesPage.setXIN(xValue);
        assertFalse(tablesPage.warnIsPresent("x"));
        tablesPage.setYIN(yValue);
        assertFalse(tablesPage.warnIsPresent("y"));
        tablesPage.clickSave();

    }

    @After
    public void tearUp() {
        driver.quit();
    }
}
