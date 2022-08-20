package ru.stqa.prf.bookaddress.tests;

import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.tests.TestBase;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation(){
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(new ContactData("test_name","test_surname", "test"),true);
//        app.getContactHelper().initContactCreation();
//        app.getContactHelper().fillContactForm(new ContactData("test_name","test_surname", "test"), true);
//        app.getContactHelper().submitContactCreation();
//        app.getContactHelper().returnToHomePage();
    }
}
