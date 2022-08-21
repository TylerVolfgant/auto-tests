package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.prf.bookaddress.model.GroupData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void deleteSelectedGroups() {
        Click(By.name("delete"));
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

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupPage();
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Set<GroupData> All() {
        Set<GroupData> groups = new HashSet<GroupData>();
        List<WebElement> elements = wd.findElements(By.name("selected[]"));
        for (WebElement element : elements){
            String new_name = null;
            String name = element.getAttribute("title");
            Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
            Matcher matcher = pattern.matcher(name);
            if (matcher.find())
            {
                new_name = matcher.group(1);
            }
            int id = Integer.parseInt(element.getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(new_name));
        }
        return groups;
    }
    public void modify( GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModificaion();
        returnToGroupPage();
    }

    public void deletion(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        returnToGroupPage();
    }

    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
}
