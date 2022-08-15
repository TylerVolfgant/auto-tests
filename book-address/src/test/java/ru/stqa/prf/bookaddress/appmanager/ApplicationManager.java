package ru.stqa.prf.bookaddress.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.prf.bookaddress.model.GroupData;

public class ApplicationManager {
    // private WebDriver wb;
    public static String browser = "firefox";
    public static WebDriver wb;

    public static void deleteSelectedGroups() {
      wb.findElement(By.name("delete")).click();
    }

    public static void selectGroup() {
      wb.findElement(By.name("selected[]")).click();
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
        }
        wb.get("http://localhost:8080/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
      wb.findElement(By.name("user")).clear();
      wb.findElement(By.name("user")).sendKeys(username);
      wb.findElement(By.name("pass")).click();
      wb.findElement(By.name("pass")).clear();
      wb.findElement(By.name("pass")).sendKeys(password);
      wb.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void returntoGroupPage() {
      wb.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
      wb.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupData groupData) {
      wb.findElement(By.name("group_name")).click();
      wb.findElement(By.name("group_name")).clear();
      wb.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wb.findElement(By.name("group_parent_id")).click();
      wb.findElement(By.name("group_header")).click();
      wb.findElement(By.name("group_header")).clear();
      wb.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wb.findElement(By.name("group_footer")).click();
      wb.findElement(By.name("group_footer")).clear();
      wb.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation() {
      wb.findElement(By.name("new")).click();
    }

    public void gotoGroupPage() {
      wb.findElement(By.linkText("groups")).click();
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
}
