package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.prf.bookaddress.model.ContactData;

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wb){
        super(wb);
    }
    public void initContactCreation(){
        Click(By.linkText("add new"));
        Click(By.xpath("//input[@name='quickadd']"));
    }
    public void fillContactForm(ContactData contactData, boolean creation){
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        if(creation){
            new Select(wb.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation(){
        Click(By.name("submit"));
    }
    public void returnToHomePage(){
        Click(By.linkText("home"));
    }
    public void submitContactModification(){
        Click(By.name("update"));
    }
    public void initContactModification(){
        Click(By.cssSelector("img[alt='Edit']"));
    }
}
