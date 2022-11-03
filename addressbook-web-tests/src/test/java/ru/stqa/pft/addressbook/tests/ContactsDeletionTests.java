package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactsDeletionTests extends TestBase {
  @Test
  public void testContactsDeletion() {
    app.getContactHelper().selectContactsForDeletion();
    app.getContactHelper().initContactsDeletion();
    app.getContactHelper().submitContactsDeletion();
  }
}
