package ru.stqa.prf.bookaddress.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    private final String browser;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    public static WebDriver wb;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

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
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            wb = new EdgeDriver();
        }
        wb.get("http://localhost:8080/addressbook/");
        groupHelper = new GroupHelper(wb);
        navigationHelper = new NavigationHelper(wb);
        sessionHelper = new SessionHelper(wb);
        sessionHelper.login("admin", "secret");
    }


//    private boolean isElementPresent(By by) {
//      try {
//        wb.findElement(by);
//        return true;
//      } catch (NoSuchElementException e) {
//        return false;
//      }
//    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
