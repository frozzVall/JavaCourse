package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;


public class TestsForNewContacts extends TestBase {


  @Test
  public void testSForNewContacts() throws Exception {
    app.getContactHelper().initNewContactsCreation();
    app.getContactHelper().fillContacts(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com","test1"), false);
    app.getNavigationHelper().returnToHomePage();
  }
}
