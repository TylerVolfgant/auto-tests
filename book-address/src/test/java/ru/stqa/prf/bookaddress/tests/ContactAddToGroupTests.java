package ru.stqa.prf.bookaddress.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.Contacts;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.io.File;

public class ContactAddToGroupTests extends TestBase {

    int maxId;
    @BeforeMethod
    public void preconditionContacts(){
        if(app.db().contacts().size() == 0){
            app.goTo().gotoHomePage();
            app.Contact().createContact(new ContactData()
                    .withFirstname("Ibragim").withLastname("Nechepenko").withMobilePhone("852")
                    .withWorkPhone("963").withHomePhone("741").withPhoto(new File("src/test/resources/green-check.png")));
        }
        app.goTo().gotoHomePage();
    }
    @BeforeMethod
    public void preconditionGroups(){
        if(app.db().groups().size() == 0){
            app.goTo().gotoGroupPage();
            app.Group().create(new GroupData().withName("test 1"));
        }
        app.goTo().gotoGroupPage();
    }
    @Test(enabled = true)

    public void testContactAddToGroup(){
        //in this group we will add contact
        GroupData target = app.db().groups().iterator().next();
        // get all contacts
        Contacts allUsersBefore = app.db().contacts();
        int i = 0;

        ContactData modifiedContact = new ContactData();
        for(ContactData contactsBefore : allUsersBefore){
            if(contactsBefore.getGroups().size() == 0){
                modifiedContact = contactsBefore;
                app.Contact().addToGroup(modifiedContact, target);
                break;
            }
            i+=1;
            //if all contacts was add to all groups, we will create new group
            if (i == allUsersBefore.size()){
                app.goTo().gotoHomePage();
                ContactData newCont = new ContactData().withFirstname("Newby");
                app.Contact().createContact(newCont);
                app.goTo().gotoHomePage();
                Contacts allUserAfter = app.db().contacts();
                for (ContactData contactsAfter : allUserAfter){
                    if(contactsAfter.getId() > maxId){
                        modifiedContact = contactsAfter;
                        app.goTo().gotoHomePage();
                        app.Contact().addToGroup(modifiedContact,target);

                    }
                }
            }
        }
        Groups groupBefore = modifiedContact.ActionWithGroup(target, true).getGroups();
        Contacts allUsersAfter = app.db().contacts();
        int userId = modifiedContact.getId();
        Groups newGroupsList = allUsersAfter.stream().filter(g -> g.getId() == userId).findFirst().get().getGroups();
        MatcherAssert.assertThat(groupBefore, CoreMatchers.equalTo(newGroupsList));
    }

}
