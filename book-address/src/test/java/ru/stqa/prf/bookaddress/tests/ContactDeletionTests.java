package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
    @Test(enabled = true)
    public void testContactDeletion(){
        File photo = new File("src/test/resources/green-check.png");
        if (app.db().contacts().size() == 0){
        app.goTo().gotoHomePage();
        //if (app.Contact().All().size() == 0){
                //app.Contact().createContact(new ContactData("test_name","test_surname", "test"),true);
                app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withHomePhone("111").withGroup("test 0")
                        .withMobilePhone("222").withWorkPhone("333").withPhoto(photo));//,true);
            }
        Contacts before = app.db().contacts();
        ContactData deletedCont = before.iterator().next();
        app.Contact().deleteSelectedContact(deletedCont);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedCont)));
    }
}
