package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactsDeletionTests extends TestBase {
  @Test
  public void testContactsDeletion() {
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com","test1"));
    }
    app.getNavigationHelper().returnToHomePage();
    List<Contacts> before=app.getContactHelper().getContactList();
    app.getContactHelper().selectContactsForDeletion(before.size()-1);
    app.getContactHelper().initContactsDeletion();
    app.getContactHelper().submitContactsDeletion();
    app.getNavigationHelper().returnToHomePage();
    List<Contacts> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(before.size()-1);
    Assert.assertEquals(before,after);
    }
}

