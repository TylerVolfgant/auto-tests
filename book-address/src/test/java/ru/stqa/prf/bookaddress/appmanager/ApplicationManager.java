package ru.stqa.prf.bookaddress.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final String browser;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
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
        wb.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
        wb.get("http://localhost:8080/addressbook/");
        groupHelper = new GroupHelper(wb);
        navigationHelper = new NavigationHelper(wb);
        sessionHelper = new SessionHelper(wb);
        contactHelper = new ContactHelper(wb);
        sessionHelper.login("admin", "secret");
    }

    public GroupHelper Group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper Contact() {
        return contactHelper;
    }
}
