package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;

import java.io.File;

public class ContactModificationTests extends TestBase {

    @Test(enabled = true)

    public void testContactModification(){
        File photo = new File("src/test/resources/green-check.png");
        app.goTo().gotoHomePage();
        //if (!app.Contact().isThereAContact()){
            if (app.Contact().All().size() == 0){
            //app.Contact().createContact(new ContactData("test_name","test_surname", "test"),true);
            app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withHomePhone("111").withWorkPhone("222").withMobilePhone("333"),true);
        }
        int before = app.Contact().getContactCount();
        app.Contact().initContactModification();
        //app.Contact().fillContactForm(new ContactData("test_name_mod","test_surname_mod",null), false);
        app.Contact().fillContactForm(new ContactData().withFirstname("test_name_mod").withLastname("test_surname_mod")
                .withHomePhone("156").withWorkPhone("178").withMobilePhone("258").withPhoto(photo),false);
        app.Contact().submitContactModification();
        app.Contact().returnToHomePage();
        int after = app.Contact().getContactCount();
        Assert.assertEquals(after, before );
    }

}
