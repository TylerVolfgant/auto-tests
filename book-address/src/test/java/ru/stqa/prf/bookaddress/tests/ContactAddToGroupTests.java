package ru.stqa.prf.bookaddress.tests;

import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;

import java.io.File;

import static ru.stqa.prf.bookaddress.tests.TestBase.app;

public class ContactAddToGroupTests extends TestBase {
    @Test(enabled = true)

    public void testContactAddToGroup(){
        File photo = new File("src/test/resources/green-check.png");
        if (app.db().contacts().size() == 0){
            app.goTo().gotoGroupPage();
            app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withHomePhone("111").withGroup("test 1")
                    .withMobilePhone("222").withWorkPhone("333").withPhoto(photo));//,true);
        }
        app.goTo().gotoHomePage();
        ContactData contact = app.Contact().All().iterator().next();
        app.Contact().addContactToGroup(contact);
    }


}
