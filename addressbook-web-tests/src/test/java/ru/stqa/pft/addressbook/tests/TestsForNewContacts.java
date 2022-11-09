package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


public class TestsForNewContacts extends TestBase {


  @Test
  public void testSForNewContacts() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("1", null, null));
    }
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().createContact(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com","test1"));
    app.getNavigationHelper().returnToHomePage();
  }
}
