package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class DeletingContactFromGroup extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("CHECK"));
    }
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.getContactsHelper().createContact(new Contact().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail("liza709@gmail.com"));
    }
  }
  @Test
  public void testDeletingContactFromGroup() {
    Groups groupsBefore =  app.db().groups();
    GroupData firstGroupWithContactsBefore = groupsBefore.stream().filter(i -> i.getContacts().stream().count() > 0).findFirst().get();
    int groupId = firstGroupWithContactsBefore.getId();
    Contacts contactsBefore = firstGroupWithContactsBefore.getContacts();

    Contact contactToDelete = contactsBefore.stream().findFirst().orElse(null);
    app.getContactsHelper().deleteContactFromGroup(contactToDelete, firstGroupWithContactsBefore);

    Groups groupsAfter = app.db().groups();
    GroupData firstGroupAfter = groupsAfter.stream().filter(i -> i.getId() == groupId).findFirst().get();
    Contacts contactsAfter = firstGroupAfter.getContacts();

    Assert.assertEquals(
            contactsBefore.stream().count(),
            contactsAfter.stream().count() + 1);
  }
}
