package ru.stqa.prf.bookaddress.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {


    @Test
  public void testGroupCreation() {
    app.goTo().gotoGroupPage();
    Groups before = app.Group().All();
    GroupData group = new GroupData().withName("test2");
    app.Group().create(group);
    Groups after = app.Group().All();
    assertThat(after.size(), equalTo(before.size() + 1));
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());

    assertThat(after, equalTo( before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}
