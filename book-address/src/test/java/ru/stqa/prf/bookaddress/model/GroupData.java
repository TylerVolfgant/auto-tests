package ru.stqa.prf.bookaddress.model;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
@XStreamAlias("group")
@Entity
@javax.persistence.Table(name="group_list")
public class GroupData {
    @XStreamOmitField
    @Id
    @Column(name="group_id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name="group_name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return id == groupData.id && Objects.equals(name, groupData.name) && Objects.equals(header, groupData.header) && Objects.equals(footer, groupData.footer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, header, footer);
    }

    @Expose
    @Column(name="group_header")
    @Type(type="text")
    private String header;
    @Expose
    @Column(name="group_footer")
    @Type(type="text")
    private String footer;



//    public GroupData(int id, String name, String header, String footer) {
//        this.id = id;
//        this.name = name;
//        this.header = header;
//        this.footer = footer;
//    }
//
//    public GroupData(String name, String header, String footer) {
//        this.id = 0;
//        this.name = name;
//        this.header = header;
//        this.footer = footer;
//    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() { return id; }
    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }
    public String getName() {
        return name;
    }
    public String getHeader() {
        return header;
    }
    public String getFooter() {
        return footer;
    }

}
