package ru.stqa.prf.bookaddress.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.tests.TestBase;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("test_name","test_surname", "test"),true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("test_name_mod","test_surname_mod",null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }

}
