package ru.stqa.prf.bookaddress.tests;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validGroupsXML() throws IOException {
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
      String xml = "";
      String line = reader.readLine();
      while (line != null){
          xml += line;
          line = reader.readLine();
      }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
    @DataProvider
    public Iterator<Object[]> validGroupsJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json,new TypeToken<List<GroupData>>(){}.getType());//List<GroupData>.class
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
    @DataProvider
    public Iterator<Object[]> validGroupsCSV() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null){
            String [] split = line.split(";");
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
         return list.iterator();
    }

    @Test(dataProvider = "validGroupsJson")
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
