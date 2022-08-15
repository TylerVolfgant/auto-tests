package ru.stqa.prf.bookaddress.tests;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;

public class GroupCreationTests extends TestBase {


    @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test", "test1", "test2"));
    app.submitGroupCreation();
    app.returntoGroupPage();
   /// wb.findElement(By.linkText("Logout")).click();
  }

}
