package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class СontactsModificationTests extends TestBase {
  @Test
  public void testContactsModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getNavigationHelper().returnToHomePage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com","test1"));
    }
    app.getNavigationHelper().returnToHomePage();
    List<Contacts> before=app.getContactHelper().getContactList();
    app.getContactHelper().initContactsModification(before.size()-1);
    Contacts contacts=new Contacts("liza","loyv",null,null,null,null);
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
