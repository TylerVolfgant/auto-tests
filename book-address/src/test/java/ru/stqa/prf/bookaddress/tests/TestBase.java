package ru.stqa.prf.bookaddress.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.prf.bookaddress.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
  //  public void setUp() throws Exception {
  //    WebDriverManager.firefoxdriver();
  //    wb = new FirefoxDriver();
  //    wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  //    wb.get("http://localhost:8080/addressbook/");
  //    login("admin", "secret");
  //  }
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        ApplicationManager.stop();
    }

}
