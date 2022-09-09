package ru.stqa.prf.bookaddress.tests;

import org.testng.annotations.Test;
import ru.stqa.prf.bookaddress.model.GroupData;
import ru.stqa.prf.bookaddress.model.Groups;

import java.sql.*;

public class DBConnectionTest {
    @Test
    public void testDBConnection(){
        Connection conn = null;
        try{
            //conn = DriverManager.getConnection("jdbc:mariadb://172.22.0.3:3306/addressbook?user=addressbook&password=addressbook");
            conn = DriverManager.getConnection("jdbc:mysql://172.22.0.3:3306/addressbook?user=addressbook&password=addressbook");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id from group_list");
            Groups groups = new Groups();
            while (rs.next()){
                groups.add(new GroupData().withId(rs.getInt("group_id")));
            }
            rs.close();
            st.close();
            System.out.println(groups);
        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("SQLError: " + ex.getErrorCode());
        }
    }
}
