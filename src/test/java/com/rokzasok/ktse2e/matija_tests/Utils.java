package com.rokzasok.ktse2e.matija_tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class Utils {
    public static void waitForAlert(WebDriver driver, int seconds) throws InterruptedException {
        for (int i = 0; i < seconds; i++) {
            try {
                Alert alert = driver.switchTo().alert();
                break;
            }
            catch(NoAlertPresentException e) {
                Thread.sleep(1000);
            }
        }
    }
}
