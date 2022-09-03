package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;

import java.io.File;

public class ContactCreationTests extends TestBase {
    @Test(enabled = true)
    public void testContactCreation(){
        app.goTo().gotoHomePage();
        int before = app.Contact().getContactCount();
       /// app.Contact().createContact(new ContactData("test_name","test_surname", "test"),true);
        File photo = new File("src/test/resources/green-check.png");
        app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withHomePhone("111")
                .withMobilePhone("222").withWorkPhone("333").withPhoto(photo),true);///("test_name","test_surname", "test"),true);
        int after = app.Contact().getContactCount();
        Assert.assertEquals(after, before + 1);
    }
}
