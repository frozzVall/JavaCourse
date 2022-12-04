package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts1 extends ForwardingSet<Contacts> {

  private Set<Contacts> delegate;

  public Contacts1(Contacts1 contacts1) {
    this.delegate=new HashSet<Contacts>(contacts1.delegate);
  }
  public Contacts1(Collection<Contacts> contacts1){
    this.delegate=new HashSet<Contacts>(contacts1);
  }

  public Contacts1() {
    this.delegate=new HashSet<Contacts>();
  }

  @Override
  protected Set<Contacts> delegate() {
    return delegate;
  }
  public Contacts1 withAdded(Contacts contacts){
    Contacts1 contacts1 = new Contacts1(this);
    contacts1.add(contacts);
    return contacts1;
  }
  public Contacts1 without(Contacts contacts){
    Contacts1 contacts1 = new Contacts1(this);
    contacts1.remove(contacts);
    return contacts1;
  }
}
