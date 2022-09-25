package ru.stqa.prf.bookaddress.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.Contacts;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.io.File;

public class ContactAddToRandGroupTests extends TestBase {
    @Test(enabled = true)

    public void testContactAddToGroup(){
        if (app.db().contacts().size() == 0){
            app.goTo().gotoGroupPage();
            app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withHomePhone("111").withGroup("test 1")
                    .withMobilePhone("222").withWorkPhone("333").withPhoto(new File("src/test/resources/green-check.png")));//,true);
        }
        app.goTo().gotoHomePage();
        ContactData contact = app.Contact().All().iterator().next();
        app.Contact().addContactToGroup(contact);

        GroupData target = app.db().groups().iterator().next();
        Groups groupBefore = contact.ActionWithGroup(target, true).getGroups();
        Contacts allUsersAfter = app.db().contacts();
        int userId = contact.getId();
        Groups newGroupsList = allUsersAfter.stream().filter(g -> g.getId() == userId).findFirst().get().getGroups();
        MatcherAssert.assertThat(groupBefore, CoreMatchers.equalTo(newGroupsList));
    }

}
