package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Contacts1;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsDeletionTests extends TestBase {
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
  public void testContactsDeletion() {
    Contacts1 before=app.db().contacts();
    Contacts deletedContact=before.iterator().next();
    app.contacts().deletion(deletedContact);
    app.goTo().homePage();
    assertThat(app.contacts().count(), equalTo(before.size()-1));
    Contacts1 after=app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    }

}

