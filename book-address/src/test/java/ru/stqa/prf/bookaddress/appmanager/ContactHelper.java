package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.Contacts;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


public class ContactHelper extends HelperBase{
    private Contacts contactCache = null;
    public ContactHelper(WebDriver wd){
        super(wd);
    }
    public void initContactCreation(){
        Click(By.linkText("add new"));
        Click(By.xpath("//input[@name='quickadd']"));
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

    public void submitAddTo(){
        Click(By.xpath("//input[@value='Add to']"));
    }
    public void initContactModification(){
        Click(By.cssSelector("img[alt='Edit']"));
    }

    public boolean isThereAContact() {
        //*[@id="search_count"]
        return isElementPresent(By.cssSelector("img[alt='Edit']"));
    }
    public void Accept(){
        wd.switchTo().alert().accept();
    }
    public void delete() throws InterruptedException {
        Click(By.xpath("//input[@value='Delete']"));
        Accept();
        Thread.sleep(1000);

    }
    public void deleteSelectedContact(ContactData contact) throws InterruptedException {
        returnToHomePage();
        selectContactById(contact.getId());
        delete();
        contactCache = null;
    }
    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, false);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }
    public void modifyContact( ContactData contact) throws InterruptedException {
//        selectContactById(contact.getId());
//        initContactModification();
        initContactModificationById(contact.getId());
        fillContactForm(contact,true);
        submitContactModification();
        Thread.sleep(700);
        contactCache = null;
        returnToHomePage();
    }
    public void addContactToGroup( ContactData contact) {
        returnToHomePage();
        selectContactById(contact.getId());
        dropRandomGroup();
        submitAddTo();
        contactCache = null;
        returnToHomePage();
    }

    public void addToGroup(ContactData contact, GroupData group ){
        returnToHomePage();
        selectContactById(contact.getId());
        Select s = new Select(wd.findElement(By.xpath("//select[@name='to_group']")));
        s.selectByVisibleText(group.getName());
        submitAddTo();
    }
    public String addRandomGroup(ContactData contact){
        returnToHomePage();
        selectContactById(contact.getId());
        Select s = new Select(wd.findElement(By.xpath("//select[@name='new_group']")));
        // getting the list of options in the dropdown with getOptions()
        List <WebElement> drops = s.getOptions();
        int size = drops.size();
        int randomNumber = ThreadLocalRandom.current().nextInt(0, size);
        String option = drops.get(randomNumber).getText();
        //System.out.println(option);
        return option;
    }
    public ContactData findUserWithoutGroup(Contacts contact, GroupData group ){
        Iterator<ContactData> iterator = contact.iterator();
        while (iterator.hasNext()){
            ContactData nextContact = iterator.next();
            if(nextContact.getGroups().isEmpty()){
                return nextContact;
            }
        }
        return null;
    }
    public ContactData findUserWithGroup(Contacts contact, GroupData group ){
        Iterator<ContactData> iterator = contact.iterator();
        while (iterator.hasNext()){
            ContactData nextContact = iterator.next();
            if(nextContact.getGroups().contains(group)){
                return nextContact;
            }
        }
        return null;
    }
    public boolean allUsersInAllGroups (Contacts contact, Groups allGroups ){
        Iterator<ContactData> iterator = contact.iterator();
        while (iterator.hasNext()){
            ContactData nextContact = iterator.next();
            if(nextContact.getGroups() != allGroups){
                return false;
            }
        }
        return true;
    }
    public String dropRandomGroup(){
        Select s = new Select(wd.findElement(By.xpath("//select[@name='to_group']")));
        // getting the list of options in the dropdown with getOptions()
        List <WebElement> drops = s.getOptions();
        int size = drops.size();
        int randomNumber = ThreadLocalRandom.current().nextInt(0, size);
        String option = drops.get(randomNumber).getText();
        //System.out.println(option);
        return option;
    }
    public void fillContactForm(ContactData contactData, boolean creation){
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        attach(By.name("photo"), contactData.getPhoto());
        if(creation){
            //if(contactData.getGroup() != null) {
            if(ContactData.getGroups().size() > 0){
                Assert.assertTrue(ContactData.getGroups().size() == 1);
                //selectFromDropDown("new_group", contactData.getGroup());
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
                //selectFromDropDown("new_group", addRandomGroup());
            }else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }
    }
    public Set<ContactData> All(){
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry")); //list of objects WebElement, find all elements with name 'entry'
        for(WebElement row : rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String[] phones = cells.get(5).getText().split("\n");
            //allPhones.split("\n");
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
        }
        return contacts;
    }
    public ContactData infoFromEditForm(ContactData contact){
        initContactModificationById(contact.getId());//choose contact by id
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

    }
    private void initContactModificationById(int id){
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        //cleck to edit
        cells.get(7).findElement(By.tagName("a")).click();
       /// wd.findElement(By.xpath(String.format("//input[@value='%s']./../../td[8]a",id))).click();
    }

    public int ContactCount() {
        //return wb.findElements(By.id("search_count")).size(); #search_count #content > label:nth-child(6) /html/body/div/div[4]/label
        return wd.findElements(By.name("selected[]")).size();
       /// return wb.findElement(By.cssSelector("search_count")).getAttribute("id");
    }
    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void selectGroup(GroupData groupList) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupList.getName());
    }

    public void deleteFromGroup(ContactData contact) {
        selectContactById(contact.getId());
        Click(By.name("remove"));
    }
}
