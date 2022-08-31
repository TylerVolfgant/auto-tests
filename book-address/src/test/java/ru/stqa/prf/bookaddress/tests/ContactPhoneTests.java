package ru.stqa.prf.bookaddress.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase{
    @Test(enabled = true)
    public void testContactPhones(){
        app.goTo().gotoHomePage();
        if (app.Contact().All().size() == 0){
            app.Contact().createContact(new ContactData().withFirstname("test_name").withLastname("test_surname").withGroup("test").withHomePhone("111")
                    .withMobilePhone("222").withWorkPhone("+7 (333)"));
        }
        ContactData contact = app.Contact().All().iterator().next();
        ContactData contactInfoFromEditForm = app.Contact().infoFromEditForm(contact);

        assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));

    }
    public String cleaned (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
