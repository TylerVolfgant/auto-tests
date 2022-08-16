package ru.stqa.prf.bookaddress.tests;

import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.GroupData;

public class GroupModificationTests extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1",null,"test3"));
        app.getGroupHelper().submitGroupModificaion();
        app.getGroupHelper().returnToGroupPage();
    }
}
