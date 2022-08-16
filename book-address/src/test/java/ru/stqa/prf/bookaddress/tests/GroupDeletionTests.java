package ru.stqa.prf.bookaddress.tests;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.appmanager.GroupHelper;
import ru.stqa.prf.bookaddress.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    //wb.findElement(By.linkText("Logout")).click();
  }


}