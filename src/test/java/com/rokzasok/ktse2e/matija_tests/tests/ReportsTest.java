package com.rokzasok.ktse2e.matija_tests.tests;

import com.rokzasok.ktse2e.matija_tests.pages.LoginPage;
import com.rokzasok.ktse2e.matija_tests.pages.ReportsPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReportsTest {
    private static final String BASE_URL = "http://localhost:4200";

    private WebDriver driver;

    private LoginPage loginPage;
    private ReportsPage reportsPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/resources/geckodriver");

        driver = new FirefoxDriver();

        loginPage = new LoginPage(driver);
        reportsPage = new ReportsPage(driver);

        driver.navigate().to(BASE_URL);
    }

    @Test
    public void test() {
        loginPage.setUsername("managerko");
        loginPage.setPassword("password");
        loginPage.doLogin();

        assertEquals(driver.getCurrentUrl(), BASE_URL + "/reports/show/monthly");

        int dishExpenses = reportsPage.getDishExpenses();
        int dishIncome = reportsPage.getDishIncome();
        int dishTotal = reportsPage.getDishTotal();
        
        assertEquals(dishTotal, dishExpenses + dishIncome);

        int drinkExpenses = reportsPage.getDrinkExpenses();
        int drinkIncome = reportsPage.getDrinkIncome();
        int drinkTotal = reportsPage.getDrinkTotal();

        assertEquals(drinkTotal, drinkExpenses + drinkIncome);

        int salaryExpenses = reportsPage.getSalaryExpenses();

        assertTrue(salaryExpenses <= 0);

        reportsPage.openReportIntervalMenu();
        reportsPage.selectWeeklyReports();

        assertEquals(driver.getCurrentUrl(), BASE_URL + "/reports/show/weekly");

        reportsPage.openReportIntervalMenu();
        reportsPage.selectMonthlyReports();

        assertEquals(driver.getCurrentUrl(), BASE_URL + "/reports/show/monthly");
    }
}
