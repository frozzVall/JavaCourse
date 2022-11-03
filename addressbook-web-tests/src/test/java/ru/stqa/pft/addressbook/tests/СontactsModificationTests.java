package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

public class СontactsModificationTests extends TestBase {
  @Test
  public void testContactsModification(){
    app.getContactHelper().initContactsModification();
    app.getContactHelper().fillContacts(new Contacts("alina", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com",null),false);
    app.getContactHelper().submitContactsModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
