package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContacts(Contacts contactsForm) {
    type(By.name("firstname"), contactsForm.getFirstName());
    type(By.name("lastname"), contactsForm.getLastName());
    type(By.name("address"), contactsForm.getAddress());
    type(By.name("mobile"), contactsForm.getNumber());
    type(By.name("email"), contactsForm.getEmail());
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initNewContactsCreation() {
    click(By.linkText("add new"));
  }
}
