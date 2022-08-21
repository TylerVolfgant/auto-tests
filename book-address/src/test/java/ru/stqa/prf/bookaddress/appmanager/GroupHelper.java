package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.prf.bookaddress.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void deleteSelectedGroups() {
        Click(By.name("delete"));
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void returnToGroupPage() {
        Click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        Click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        Click(By.name("group_parent_id"));
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        Click(By.name("new"));
    }

    public void initGroupModification() {
        Click(By.name("edit"));
    }

    public void submitGroupModificaion() {
        Click(By.name("update"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        //List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
       List<WebElement> elements = wd.findElements(By.name("selected[]"));
        for (WebElement element : elements){
            String name = element.getText();
            //String id = element.findElement(By.tagName("input")).getAttribute("value");
            int id = Integer.parseInt(element.getAttribute("value"));
            GroupData group = new GroupData( id, name, null, null);
            groups.add(group);
        }
        return groups;
    }
    public void modifyGroup(int indx, GroupData group) {
        selectGroup(indx);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModificaion();
        returnToGroupPage();
    }
}
