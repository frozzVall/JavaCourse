package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


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
    Set<Contacts> before=app.contacts().all();
    Contacts contacts =new Contacts().
            withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withNumber("+375291567859").withEmail( "liza709@gmail.com");
    app.contacts().createContact(contacts);
    app.goTo().homePage();
    Set<Contacts> after=app.contacts().all();
    Assert.assertEquals(after.size(),before.size()+1);

    contacts.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
    before.add(contacts);
    Assert.assertEquals(before,after);

  }
}
