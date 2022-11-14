package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactsDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size()==0) {
      app.group().create(new GroupData("test1", null, null));
    }
    app.goTo().homePage();
    if (app.contacts().list().size()==0) {
      app.contacts().createContact(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com", null));
    }
    app.goTo().homePage();
  }
  @Test
  public void testContactsDeletion() {
    List<Contacts> before=app.contacts().list();
    int index=before.size()-1;
    app.contacts().deletion(index);
    app.goTo().homePage();
    List<Contacts> after=app.contacts().list();
    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(index);
    Assert.assertEquals(before,after);
    }

}

