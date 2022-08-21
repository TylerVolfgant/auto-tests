package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;

public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation(){
        app.goTo().gotoHomePage();
        int before = app.getContactHelper().getContatCount();
        System.out.println("before = " + before);
        app.getContactHelper().createContact(new ContactData("test_name","test_surname", "test"),true);
        int after = app.getContactHelper().getContatCount();
        Assert.assertEquals(after, before + 1);
    }
}
