package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @Test(enabled = true)
    public void testContactCreation(){
        app.goTo().gotoHomePage();
        //int before = app.Contact().getContactCount();
        Contacts before = app.db().contacts();
        //String group = app.Contact().addRandomGroup();
       // System.out.println("group ++++++" + group);
        System.out.println("before ===== " + before);
       /// app.Contact().createContact(new ContactData("test_name","test_surname", "test"),true);
        File photo = new File("src/test/resources/green-check.png");
        app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withHomePhone("111").withGroup("test 0")
                .withMobilePhone("222").withWorkPhone("333").withPhoto(photo));//,true);///("test_name","test_surname", "test"),true);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
    }
}
