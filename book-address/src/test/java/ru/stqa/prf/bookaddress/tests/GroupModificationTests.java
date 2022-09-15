package ru.stqa.prf.bookaddress.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        //app.goTo().gotoGroupPage();
//        if (app.Group().All().size() == 0){
//            app.Group().create(new GroupData().withName("test1"));
//        }
        if (app.db().groups().size() == 0){
            app.goTo().gotoGroupPage();
            app.Group().create(new GroupData().withName("test1"));
        }
    }
    @Test
    public void testGroupModification(){
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.goTo().gotoGroupPage();
        app.Group().modify(group);
        Groups after = app.db().groups();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(group).withAdded(modifiedGroup)));
    }


}
