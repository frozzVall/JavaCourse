package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class TestsForNewContacts extends TestBase {


  @Test
  public void testSForNewContacts() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("1", null, null));
    }
    app.getNavigationHelper().returnToHomePage();
    List<Contacts> before=app.getContactHelper().getContactList();
    Contacts contacts =new Contacts("liza1", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com","test1");
    app.getContactHelper().createContact(contacts);
    app.getNavigationHelper().returnToHomePage();
    List<Contacts> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size()+1);


    before.add(contacts);
    Comparator<? super Contacts> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);

  }
}
