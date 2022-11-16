package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class СontactsModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size()==0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    if (app.contacts().list().size()==0) {
      app.contacts().createContact(new Contacts().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withNumber("+375291567859").withEmail( "liza709@gmail.com"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactsModification() {
    Set<Contacts> before = app.contacts().all();
    Contacts modifyContact=before.iterator().next();
    Contacts contacts = new Contacts().withId(modifyContact.getId()).withFirstName("liza").withLastName("null");
    app.contacts().modify(contacts);
    app.goTo().homePage();
    Set<Contacts> after = app.contacts().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifyContact);
    before.add(contacts);
    Assert.assertEquals(before, after);
  }
}
