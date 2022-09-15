package ru.stqa.prf.bookaddress.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().gotoGroupPage();;
    if (app.Group().All().size() == 0){
      app.Group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.Group().deletion(deletedGroup);
    Groups after = app.db().groups();
    Assert.assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedGroup)));
    //wb.findElement(By.linkText("Logout")).click();
  }




}