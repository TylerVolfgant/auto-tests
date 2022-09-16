package ru.stqa.prf.bookaddress.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.prf.bookaddress.appmanager.ApplicationManager;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.Contacts;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));//("firefox");

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        ApplicationManager.stop();
    }
    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
       logger.info("Start test " + m.getName() + "with params" + Arrays.asList(p));
    }
    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p){
        logger.info("Stop test " + m.getName() + "with params" + Arrays.asList(p));
    }
    public void verifyGroupUI(){
        if(Boolean.getBoolean("verifyUI")){
            Groups DBGroups = app.db().groups();
            Groups UIGroups = app.Group().All();
            assertThat(UIGroups, equalTo(DBGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
    public void verifyContactUI(){
        if(Boolean.getBoolean("verifyUI")){
            Contacts DBContacts = app.db().contacts();
            Set<ContactData> UIContacts = app.Contact().All();
            assertThat(UIContacts, equalTo(DBContacts.stream()
                    .map((g) -> new ContactData().withId(g.getId()).withFirstname(g.getFirstname()).withLastname(g.getLastname()))
                    .collect(Collectors.toSet())));
        }
    }
}
