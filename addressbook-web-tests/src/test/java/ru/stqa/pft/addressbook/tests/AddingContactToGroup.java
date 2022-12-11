package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class AddingContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size()==0) {
      app.group().create(new GroupData().withName("CHECK"));
    }
    app.goTo().homePage();
    if (app.db().contacts().size()==0) {
      app.getContactsHelper().createContact(new Contact().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail( "liza709@gmail.com"));
    }
    app.goTo().homePage();
  }
  @Test
  public void testAddingContactsToGroup() {
    Contacts contactsBefore = app.db().contacts();
    Contact firstContactBefore = contactsBefore.stream().findFirst().orElse(null);
    Groups firstContactGroupsBefore = firstContactBefore.getGroups();

    GroupData groupToAdd = app.db().groups().stream()
           // .filter(i -> !firstContactGroupsBefore.contains(i))
            .findFirst()
            .orElse(null);

    app.getContactsHelper().addContactToGroup(firstContactBefore, groupToAdd);

    Contacts contactsAfter = app.db().contacts();
    Contact firstContactAfter = contactsAfter.stream().findFirst().orElse(null);
    Groups firstContactGroupsAfter = firstContactAfter.getGroups();
    Contacts contacts =app.db().contacts();
    Groups groups=app.db().groups();
    if(contacts.size()==1&&groups.size()==1
            &&(contacts.stream().findFirst().get().getGroups().contains(groupToAdd))) {
          Assert.assertEquals(
            firstContactGroupsBefore.stream().count(),
            firstContactGroupsAfter.stream().count());}
    else {
      Assert.assertEquals(
              firstContactGroupsBefore.stream().count(),
              firstContactGroupsAfter.stream().count()-1);}
      }
  }

