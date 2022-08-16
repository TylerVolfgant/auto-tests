package ru.stqa.prf.bookaddress.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.prf.bookaddress.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager("firefox");

    @BeforeMethod(alwaysRun = true)

    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        ApplicationManager.stop();
    }

}
