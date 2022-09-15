package ru.stqa.prf.bookaddress.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;
@Entity
@Table(name = "addressbook")
public class ContactData {
@Id
@Column(name="id")
    private int id;
@Column(name="firstname")
    private String firstname;
@Column(name="lastname")
    private String lastname;
@Transient
    private String group;
@Column(name="home")
@Type(type="text")
    private String homePhone;
@Column(name="mobile")
@Type(type="text")
    private String mobilePhone;
@Column(name="work")
@Type(type="text")
    private String workPhone;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Column(name="photo")
@Type(type="text")
    private String photo;

//    public ContactData(String firstname, String lastname, String group) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.group = group;
//    }
    public String getFirstname(){
        return firstname;
    }
    public ContactData withFirstname(String firstname){
        this.firstname = firstname;
        return this;
    }
    public String getLastname(){
        return lastname;
    }
    public ContactData withLastname(String lastname){
        this.lastname = lastname;
        return this;
    }
    public String getWorkPhone(){ return workPhone;}

    public ContactData withWorkPhone(String workPhone){
        this.workPhone = workPhone;
        return this;
    }
    public String getMobilePhone(){ return mobilePhone;}

    public ContactData withMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
        return this;
    }
    public String getHomePhone(){ return homePhone;}
    public ContactData withHomePhone(String homePhone){
        this.homePhone = homePhone;
        return this;
    }
    public int getId() { return id; }

    public ContactData withId(int id){
        this.id = id;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public ContactData withGroup(String group){
        this.group = group;
        return this;
    }

    public File getPhoto() {
        return new File(photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(group, that.group) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, group, homePhone, mobilePhone, workPhone, photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

}
