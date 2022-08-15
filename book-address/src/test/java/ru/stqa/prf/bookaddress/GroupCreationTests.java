package ru.stqa.prf.bookaddress;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


    @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test", "test1", "test2"));
    submitGroupCreation();
    returntoGroupPage();
   /// wb.findElement(By.linkText("Logout")).click();
  }

}
