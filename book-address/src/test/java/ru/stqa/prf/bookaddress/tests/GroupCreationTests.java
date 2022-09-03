package ru.stqa.prf.bookaddress.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
//      list.add(new Object[]{new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
//      list.add(new Object[]{new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
//      list.add(new Object[]{new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
      String line = reader.readLine();
      while (line != null){
          String [] split = line.split(";");
          list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
          line = reader.readLine();
      }
      return list.iterator();
    }
    @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().gotoGroupPage();
    Groups before = app.Group().All();
    //GroupData group = new GroupData().withName("test2");
    app.Group().create(group);
    Groups after = app.Group().All();
    assertThat(after.size(), equalTo(before.size() + 1));
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

    assertThat(after, equalTo( before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}
