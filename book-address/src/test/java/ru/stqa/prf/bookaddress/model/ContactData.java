package ru.stqa.prf.bookaddress.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private String firstname;
    private String lastname;
    private String group;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, group);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", secondname='" + lastname + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
