package ru.stqa.prf.bookaddress.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    // private WebDriver wb;
    public static String browser = "firefox";
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    public static WebDriver wb;
    public static void stop() {
        wb.quit();
    }

    public void init() {
        if (browser.equals("firefox")) {
          WebDriverManager.firefoxdriver().setup();
          wb = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
          WebDriverManager.chromedriver().setup();
          wb = new ChromeDriver();
        }
        wb.get("http://localhost:8080/addressbook/");
        groupHelper = new GroupHelper(wb);
        navigationHelper = new NavigationHelper(wb);
        sessionHelper = new SessionHelper(wb);
        sessionHelper.login("admin", "secret");
    }


    private boolean isElementPresent(By by) {
      try {
        wb.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        wb.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
