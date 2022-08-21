package ru.stqa.prf.bookaddress.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.GroupData;
import java.util.Set;

public class GroupModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().gotoGroupPage();;
        if (app.Group().All().size() == 0){
            app.Group().create(new GroupData().withName("test1"));
        }
    }
    @Test
    public void testGroupModification(){
        //List<GroupData> before = app.Group().GList();
        Set<GroupData> before = app.Group().All();
        GroupData modifiedGroup = before.iterator().next();
        //int indx = before.size() -1;
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");

        app.Group().modify(group);
        ///List<GroupData> after = app.Group().GList();
        Set<GroupData> after = app.Group().All();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
       // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
//        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        before.sort(byId);
//        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
