package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContacts(Contacts contactsForm, boolean creation) {
    type(By.name("firstname"), contactsForm.getFirstName());
    type(By.name("lastname"), contactsForm.getLastName());
    type(By.name("address"), contactsForm.getAddress());
    type(By.name("mobile"), contactsForm.getNumber());
    type(By.name("email"), contactsForm.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void initNewContactsCreation() {
    click(By.linkText("add new"));
  }

  public void initContactsModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactsModification() {
    click((By.name("update")));
  }

  public void selectContactsForDeletion(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactsDeletion() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }

  public void submitContactsDeletion() {
    wd.switchTo().alert().accept();
  }


  public void createContact(Contacts contacts) {
   initNewContactsCreation();
   fillContacts(contacts,true);
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<Contacts> getContactList() {
    List <Contacts> contacts =new ArrayList<Contacts>();
    List <WebElement> tableRows = wd.findElements(By.cssSelector("tbody tr[name=\"entry\"]"));
    for (WebElement tableRow: tableRows ){
      String lastName = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(tableRow.findElement(By.tagName("input")).getAttribute("value"));
      Contacts contact = new Contacts(id,firstName,lastName,null,null,null,null);
      contacts.add(contact);
    }
    return contacts;

  }

}
