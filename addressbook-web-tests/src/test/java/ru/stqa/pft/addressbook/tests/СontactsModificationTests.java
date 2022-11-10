package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;

public class СontactsModificationTests extends TestBase {
  @Test
  public void testContactsModification(){
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com","test1"));
    }
    app.getNavigationHelper().returnToHomePage();
    List<Contacts> before=app.getContactHelper().getContactList();
    app.getContactHelper().initContactsModification(before.size()-1);
    Contacts contacts=new Contacts("liza",null,null,null,null,null);
    app.getContactHelper().fillContacts(contacts,false);
    app.getContactHelper().submitContactsModification();
    app.getNavigationHelper().returnToHomePage();
    List<Contacts> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size()-1);
    before.add(contacts);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
