package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Contacts1;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestsForNewContacts extends TestBase {

  @BeforeMethod
  public void EnsurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testSForNewContacts() throws Exception {
    Contacts1 before= (Contacts1) app.contacts().all();
    Contacts contacts =new Contacts().
            withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail( "liza709@gmail.com");
    app.contacts().createContact(contacts);
    app.goTo().homePage();
    assertThat(app.contacts().count(), equalTo(before.size()+1));
    Contacts1 after= (Contacts1) app.contacts().all();


    assertThat(after, equalTo(before.
            withAdded(contacts.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));

  }
  @Test
  public void testBadSForNewContacts() throws Exception {
    Contacts1 before= (Contacts1) app.contacts().all();
    Contacts contacts =new Contacts().
            withFirstName("liza'").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail( "liza709@gmail.com");
    app.contacts().createContact(contacts);
    app.goTo().homePage();
    assertThat(app.contacts().count(), equalTo(before.size()));
    Contacts1 after= (Contacts1) app.contacts().all();
    assertThat(after, equalTo(before));

  }
}
