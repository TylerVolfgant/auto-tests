package ru.stqa.prf.bookaddress.tests;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;

public class GroupCreationTests extends TestBase {


    @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test", "test1", "test2"));
//    app.getGroupHelper().initGroupCreation();
//    app.getGroupHelper().fillGroupForm(new GroupData("test", "test1", "test2"));
//    app.getGroupHelper().submitGroupCreation();
//    app.getGroupHelper().returnToGroupPage();
   /// wb.findElement(By.linkText("Logout")).click();
  }

}
