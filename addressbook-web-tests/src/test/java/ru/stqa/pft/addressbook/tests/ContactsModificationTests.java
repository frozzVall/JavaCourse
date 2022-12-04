package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Contacts1;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size()==0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    if (app.db().contacts().size()==0) {
      app.contacts().createContact(new Contacts().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail( "liza709@gmail.com"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactsModification() {
    Contacts1 before = app.db().contacts();
    Contacts modifyContact=before.iterator().next();
    Contacts contacts = new Contacts().withId(modifyContact.getId()).withFirstName("liza").withLastName("null");
    app.contacts().modify(contacts);
    app.goTo().homePage();
    assertThat(app.contacts().count(), equalTo(before.size()));
    Contacts1 after = app.db().contacts();

    assertThat(after, equalTo(before.without(modifyContact).withAdded(contacts)));


  }
}
