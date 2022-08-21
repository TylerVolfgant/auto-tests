package ru.stqa.prf.bookaddress.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;
import java.util.Set;

public class GroupCreationTests extends TestBase {


    @Test
  public void testGroupCreation() {
    app.goTo().gotoGroupPage();
    Set<GroupData> before = app.Group().All();
    GroupData group = new GroupData().withName("test2");
    app.Group().create(group);
    Set<GroupData> after = app.Group().All();
    Assert.assertEquals(after.size(), before.size() + 1);
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);

  }
}
