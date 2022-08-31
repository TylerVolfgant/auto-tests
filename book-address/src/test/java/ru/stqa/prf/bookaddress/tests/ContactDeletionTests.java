package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;

public class ContactDeletionTests extends TestBase {
    @Test(enabled = true)
    public void testContactDeletion(){
        app.goTo().gotoHomePage();
        //if (!app.Contact().isThereAContact()){
        if (app.Contact().All().size() == 0){
                //app.Contact().createContact(new ContactData("test_name","test_surname", "test"),true);
                app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withGroup("test"));
            }
        //}
        int before = app.Contact().getContactCount();
        app.Contact().initContactModification();
        app.Contact().deleteSelectedContact();
        app.Contact().returnToHomePage();
        int after = app.Contact().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
