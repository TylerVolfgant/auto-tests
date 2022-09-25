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

public class RemoveContactFromGroupTests extends TestBase {

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
    @Test
    public void testRemoveContactFromGroup(){
        GroupData modifiedGroup = app.db().groups().iterator().next();
        //create midifiedContact
        ContactData modifContact = new ContactData();
        // get list of all contacts
        Contacts contactsBefore = app.db().contacts();
        int i = 0;//number of users
        //search contact with group
        for(ContactData contact : contactsBefore){
            if(contact.getGroups().size() != 0){
                Groups groupList;
                groupList = contact.getGroups();
                for (GroupData group : groupList){
                    if(group.getId() == modifiedGroup.getId()){
                        modifContact = contact;
                    }
                    break;
                }
                break;
            }
            i +=1; //number of users
        }
        if (i == contactsBefore.size()){
            //search contact without group and add group
            for (ContactData cont : contactsBefore){
                if(cont.getGroups().size() == 0){
                    modifContact = cont;
                    app.Contact().addToGroup(modifContact,modifiedGroup);
                    break;
                }
            }
        }
        app.goTo().gotoHomePage();
        app.Contact().selectGroup(modifiedGroup);
        app.Contact().deleteFromGroup(modifContact);

        Groups groupBefore = modifContact.ActionWithGroup(modifiedGroup,false).getGroups();
        //check groups in our contact, how will be deleted
        Contacts contactsAfter = app.db().contacts();
        int userId = modifContact.getId();
        Groups newGroupList = contactsAfter.stream().filter(g -> g.getId() == userId).findFirst().get().getGroups();
        MatcherAssert.assertThat(groupBefore, CoreMatchers.equalTo(newGroupList));
    }
}
