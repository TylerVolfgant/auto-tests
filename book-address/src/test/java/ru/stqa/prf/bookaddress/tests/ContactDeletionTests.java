package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.Contacts;
import ru.stqa.prf.bookaddress.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void Precondition(){
        File photo = new File("src/test/resources/green-check.png");
        if (app.db().contacts().size() == 0){
            app.goTo().gotoHomePage();
            app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withHomePhone("111")
                            .inGroup(new GroupData().withHeader("").withFooter("").withName("test 0"))
                   // .withGroup("test 0")
                    .withMobilePhone("222").withWorkPhone("333").withPhoto(new File("src/test/resources/green-check.png")));
        }
    }
    @Test(enabled = true)
    public void testContactDeletion() throws InterruptedException {

        Contacts before = app.db().contacts();
        System.out.println("before ++++++ " + before.size());
        ContactData deletedCont = before.iterator().next();
        app.Contact().deleteSelectedContact(deletedCont);
        app.goTo().gotoHomePage();
        Contacts after = app.db().contacts();
        System.out.println("after ++++++ " + after.size());
        assertThat(after.size(), equalTo((before.size() - 1)));
        assertThat(after, equalTo(before.without(deletedCont)));
        verifyContactUI();
    }
}
