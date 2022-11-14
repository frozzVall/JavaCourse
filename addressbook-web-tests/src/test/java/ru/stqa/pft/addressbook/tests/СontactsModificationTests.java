package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class СontactsModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size()==0) {
      app.group().create(new GroupData("test1", null, null));
    }
    app.goTo().homePage();
    if (app.contacts().list().size()==0) {
      app.contacts().createContact(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com", null));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactsModification() {
    List<Contacts> before = app.contacts().list();
    int index = before.size() - 1;
    Contacts contacts = new Contacts(before.get(before.size() - 1).getId(), "liza", "loyv", null, null, null, null);
    app.contacts().modifyContacts(index, contacts);
    app.goTo().homePage();
    List<Contacts> after = app.contacts().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contacts);
    Comparator<? super Contacts> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
