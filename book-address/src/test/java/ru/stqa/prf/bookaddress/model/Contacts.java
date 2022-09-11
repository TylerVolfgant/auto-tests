package ru.stqa.prf.bookaddress.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {
    private Set<ContactData> delegate;
    public Contacts(Contacts contacts){
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<ContactData>(contacts);
    }
    public Contacts(){
        this.delegate = new HashSet<ContactData>();
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

}
