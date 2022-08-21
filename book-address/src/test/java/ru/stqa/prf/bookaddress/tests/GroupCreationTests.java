package ru.stqa.prf.bookaddress.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before = app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test_new","test2","test3" );
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);
    //Assert.assertEquals(after.size(), before.size() + 1);

//    int max = 0;
//    for(GroupData g : after){
//      if (g.getId() > max){
//        max = g.getId();
//      }
//    }
//      Comparator<? super GroupData> byId = new Comparator<GroupData>() {
//        @Override
//        public int compare(GroupData t0, GroupData t1) {
//          return Integer.compare(t0.getId(),t1.getId());
//        }
//      };
   //   after.stream().max(byId).get().getId();
    //int max1 = after.stream().max((t0,t1) -> Integer.compare(t0.getId(), t1.getId())).get().getId();
//    group.setId(after.stream().max((t0,t1) -> Integer.compare(t0.getId(), t1.getId())).get().getId());
//    before.add(group);
//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);
//    Assert.assertEquals(before, after);

  }
}
