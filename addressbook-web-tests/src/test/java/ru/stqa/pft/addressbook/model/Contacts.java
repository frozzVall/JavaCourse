package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<Contact> {

  private Set<Contact> delegate;

  public Contacts(Contacts contacts) {
    this.delegate=new HashSet<Contact>(contacts.delegate);
  }

  public Contacts(Collection<Contact> contacts){
    this.delegate=new HashSet<Contact>(contacts);
  }

  public Contacts() {
    this.delegate=new HashSet<Contact>();
  }

  @Override
  protected Set<Contact> delegate() {
    return delegate;
  }

  public Contacts withAdded(Contact contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(Contact contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
