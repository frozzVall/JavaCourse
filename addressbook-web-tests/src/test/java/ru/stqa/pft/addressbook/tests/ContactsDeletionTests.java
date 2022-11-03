package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactsDeletionTests extends TestBase {
  @Test
  public void testContactsDeletion() {
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com","test1"));
    }
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().selectContactsForDeletion();
    app.getContactHelper().initContactsDeletion();
    app.getContactHelper().submitContactsDeletion();
  }
}
