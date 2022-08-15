package ru.stqa.prf.bookaddress.tests;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.appmanager.ApplicationManager;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupPage();
    ApplicationManager.selectGroup();
    ApplicationManager.deleteSelectedGroups();
    app.returntoGroupPage();
    //wb.findElement(By.linkText("Logout")).click();
  }


}