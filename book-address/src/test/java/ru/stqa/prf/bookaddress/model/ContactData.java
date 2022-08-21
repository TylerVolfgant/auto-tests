package ru.stqa.prf.bookaddress.model;

import java.util.Objects;

public class ContactData {
    private final String firstname;
    private final String secondname;
    private String group;


    public ContactData(String firstname, String secondname, String group) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.group = group;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return secondname;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(secondname, that.secondname) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, secondname, group);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
