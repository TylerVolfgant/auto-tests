package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test(enabled = true)
    public void testContactCreation(){
        app.goTo().gotoHomePage();
        int before = app.Contact().getContactCount();
       /// app.Contact().createContact(new ContactData("test_name","test_surname", "test"),true);
        app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withGroup("test").withHomePhone("111")
                .withMobilePhone("222").withWorkPhone("333"));///("test_name","test_surname", "test"),true);
        int after = app.Contact().getContactCount();
        Assert.assertEquals(after, before + 1);
    }
}
