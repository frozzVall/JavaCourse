package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class TestsForNewContacts extends TestBase {

  @BeforeMethod
  public void EnsurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size()==0){
      app.group().create(new GroupData("1", null, null));
    }
    app.goTo().homePage();
  }

  @Test
  public void testSForNewContacts() throws Exception {
    List<Contacts> before=app.contacts().list();
    Contacts contacts =new Contacts("liza1", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com","test1");
    app.contacts().createContact(contacts);
    app.goTo().homePage();
    List<Contacts> after=app.contacts().list();
    Assert.assertEquals(after.size(),before.size()+1);


    before.add(contacts);
    Comparator<? super Contacts> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);

  }
}
