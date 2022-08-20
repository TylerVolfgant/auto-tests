package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.prf.bookaddress.model.GroupData;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wb) {
        super(wb);
    }

    public void deleteSelectedGroups() {
        Click(By.name("delete"));
    }

    public void selectGroup() {
        Click(By.name("selected[]"));
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
        return wb.findElements(By.name("selected[]")).size();
    }
}
