package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;


public class TestsForNewContacts extends TestBase{


  @Test
  public void testSForNewContacts() throws Exception {
    initNewContactsCreation();
    fillContacts(new Contacts("liza", "dlogyv", "uliza gorelika", "+375291567859", "liza709@gmail.com"));
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    returnToHomePage();
  }
}
