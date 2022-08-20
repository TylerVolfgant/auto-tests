package ru.stqa.prf.bookaddress.tests;

import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("test_name","test_surname", "test"),true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().returnToHomePage();
    }
}
