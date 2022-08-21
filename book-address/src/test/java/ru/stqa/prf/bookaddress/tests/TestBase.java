package ru.stqa.prf.bookaddress.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.prf.bookaddress.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager("firefox");

    //@BeforeMethod(alwaysRun = true)
    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    //@AfterMethod(alwaysRun = true)
    @AfterSuite
    public void tearDown() throws Exception {
        ApplicationManager.stop();
    }

}
