package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.Contacts;


public class TestsForNewContacts extends TestBase {


  @Test
  public void testSForNewContacts() throws Exception {
    app.initNewContactsCreation();
    app.fillContacts(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com"));
    app.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    app.returnToHomePage();
  }
}
