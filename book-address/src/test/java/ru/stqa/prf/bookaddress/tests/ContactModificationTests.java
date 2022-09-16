package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.Contacts;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
    File photo = new File("src/test/resources/green-check.png");
    @BeforeMethod
    public void Precondition(){

        if (app.db().contacts().size() == 0){
            app.goTo().gotoHomePage();
            app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withHomePhone("111").withGroup("test 0")
                    .withMobilePhone("222").withWorkPhone("333").withPhoto(photo));
        }
    }
    @Test(enabled = true)

    public void testContactModification(){
        String mod = "_mod";
        int randNumb = ThreadLocalRandom.current().nextInt(0, 10);
        Contacts before = app.db().contacts();
        app.goTo().gotoHomePage();
        ContactData contact = before.iterator().next();
        ContactData modcontact  = new ContactData().withId(contact.getId()).withFirstname("test_name" + mod).withLastname("test_lastname" + mod).withGroup(null)
                .withHomePhone("111" + randNumb).withMobilePhone("222" + randNumb).withWorkPhone("333" + randNumb).withPhoto(photo);
        app.goTo().gotoHomePage();
        app.Contact().modifyContact(modcontact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(contact).withAdded(modcontact)));
        verifyContactUI();
    }

}
