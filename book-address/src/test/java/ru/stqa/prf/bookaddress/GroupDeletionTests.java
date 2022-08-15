package ru.stqa.prf.bookaddress;
import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returntoGroupPage();
    //wb.findElement(By.linkText("Logout")).click();
  }


}