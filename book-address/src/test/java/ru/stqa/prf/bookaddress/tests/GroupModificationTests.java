package ru.stqa.prf.bookaddress.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.getNavigationHelper().gotoGroupPage();;
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
    }
    @Test
    public void testGroupModification(){
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int indx = before.size() -1;
        GroupData group = new GroupData( before.get(indx).getId(),"test1","test2","test3" );
        app.getGroupHelper().modifyGroup(indx, group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

//        before.remove(before.size() -1);
//        before.add(group);
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
//        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(before, after);
    }


}
