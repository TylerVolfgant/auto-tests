package ru.stqa.prf.bookaddress.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;
import java.util.Set;

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
    Set<GroupData> before = app.Group().All();
    GroupData deletedGroup = before.iterator().next();
    app.Group().deletion(deletedGroup);
    Set<GroupData> after = app.Group().All();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
    //wb.findElement(By.linkText("Logout")).click();
  }




}