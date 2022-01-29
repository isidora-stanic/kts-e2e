package com.rokzasok.ktse2e.isidora_tests.tests;

import com.rokzasok.ktse2e.isidora_tests.pages.AddTablePage;
import com.rokzasok.ktse2e.isidora_tests.pages.LoginPage;
import com.rokzasok.ktse2e.isidora_tests.pages.TablesPage;
import com.rokzasok.ktse2e.isidora_tests.pages.UsersPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddTableTest {

    private static final String BASE_URL = "http://localhost:4200";
    private WebDriver driver;

    private LoginPage loginPage;
    private TablesPage tablesPage;
    private UsersPage usersPage;
    private AddTablePage addTablePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver-isidora.exe");

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        tablesPage = new TablesPage(driver);
        usersPage = new UsersPage(driver);
        addTablePage = new AddTablePage(driver);

        driver.navigate().to(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    public void deleteOneTable() {
        tablesPage.deleteFirstTable();
    }

    public void deleteAllTables() {
        while (true) {
            try {
                this.driver.navigate().refresh();
                deleteOneTable();
            }
            catch (Exception e) {
                break;
            }
        }
    }

    public void deleteMoreTables(int num) {
        int i = 0;
        while (i < num) {
            try {
                this.driver.navigate().refresh();
                deleteOneTable();
                i++;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public void addOneTable(String name, String x, String y) {
        tablesPage.clickAdd();
        addTablePage.setNameIN(name);
        addTablePage.setXIN(x);
        addTablePage.setYIN(y);
        addTablePage.clickSave();
    }

    public void addAllTables() {
        int count = 0;
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 6; y++) {
                count++;
                String name = "Sto " + String.valueOf(count);

                addOneTable(name, String.valueOf(x), String.valueOf(y));
            }
        }
    }

    public void addMoreTables(int mx, int my) {
        int count = 0;
        for (int x = 0; x < mx; x++) {
            for (int y = 0; y < my; y++) {
                count++;
                String name = "Sto " + String.valueOf(count);

                addOneTable(name, String.valueOf(x), String.valueOf(y));
            }
        }
    }

    @Test
    public void testAdminAddAllTablesDeleteAllTables() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("admin");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO USERS PAGE */

        usersPage.goToTableEditor();
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/admin-tables");

        /* TODO ADD TABLE PAGE */
        deleteMoreTables(9);
        //addAllTables();
        addMoreTables(3,3);
        deleteMoreTables(9);
    }

    @Test
    public void testAdminAddNewTableAllCases() throws InterruptedException {
        /* TODO LOGIN PAGE */
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/login");

        loginPage.setUsername("admin");
        loginPage.setPassword("password");
        loginPage.doLogin();

        /* TODO USERS PAGE */

        usersPage.goToTableEditor();
        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/admin-tables");

        /* TODO ADMIN TABLES PAGE */

        tablesPage.clickAdd();

        /* TODO ADD TABLE PAGE */

        assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/add-table");

        String tableName = "New Table 55";
        String xValue = "3";
        String yValue = "4";

        // todo no name, x, y < 0

        addTablePage.setNameIN(" ");
        assertTrue(addTablePage.warnIsPresent("name"));
        //assertFalse(addTablePage.warnIsPresent("same name"));
        addTablePage.setXIN("");
        addTablePage.setXIN("-3");
        assertTrue(addTablePage.warnIsPresent("x"));
        addTablePage.setYIN("-3");
        assertTrue(addTablePage.warnIsPresent("y"));
        assertThrows(TimeoutException.class, () -> addTablePage.clickSave(), "Ne moze se sacuvati sto sa praznim nevalidnim poljima");

        // todo same name, x, y valid

        addTablePage.setNameIN(tableName);
        assertFalse(addTablePage.warnIsPresent("name"));
        //assertTrue(addTablePage.warnIsPresent("same name"));
        addTablePage.setXIN("0");
        assertFalse(addTablePage.warnIsPresent("x"));
        addTablePage.setYIN("0");
        assertFalse(addTablePage.warnIsPresent("y"));

        assertThrows(TimeoutException.class, () -> addTablePage.clickSave(), "Ne moze se sacuvati sto sa istim imenom kao neki postojeci");

        // todo valid name, x, y > max

        addTablePage.setNameIN(tableName + "67");
        assertFalse(addTablePage.warnIsPresent("name"));
        assertFalse(addTablePage.warnIsPresent("same name"));
        addTablePage.setXIN("16");
        assertTrue(addTablePage.warnIsPresent("x"));
        addTablePage.setYIN("16");
        assertTrue(addTablePage.warnIsPresent("y"));

        assertThrows(TimeoutException.class, () -> addTablePage.clickSave(), "Ne moze se sacuvati sto sa vecim koordinatama od maksimalnih");

        // todo happy flow :)
        addTablePage.setXIN(xValue);
        assertFalse(addTablePage.warnIsPresent("x"));
        addTablePage.setYIN(yValue);
        assertFalse(addTablePage.warnIsPresent("y"));
        addTablePage.clickSave();

        /* TODO ADMIN TABLES PAGE */

        //assertEquals(this.driver.getCurrentUrl(), BASE_URL + "/admin-tables");

    }

    @After
    public void tearUp() {
        driver.quit();
    }
}
