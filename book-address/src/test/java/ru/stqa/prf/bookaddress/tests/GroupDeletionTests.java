package ru.stqa.prf.bookaddress.tests;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.appmanager.GroupHelper;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    //wb.findElement(By.linkText("Logout")).click();
  }


}