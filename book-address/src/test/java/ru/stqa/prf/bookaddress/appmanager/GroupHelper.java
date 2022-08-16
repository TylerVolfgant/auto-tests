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
}
