package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Contacts1;

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
  public void initContactsModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactsModification() {
    click((By.name("update")));
  }

  public void selectContacts(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void selectContactsById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }

  public void initContactsDeletion() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }

  public void submitContactsDeletion() {
    wd.switchTo().alert().accept();
  }


  public void createContact(Contacts contacts) {
    initNewContactsCreation();
    fillContacts(contacts, true);
  }

  public void modify(Contacts contacts) {
    initContactsModificationById(contacts.getId());
    fillContacts(contacts, false);
    submitContactsModification();
  }

  public void deletion(int index) {
    selectContacts(index);
    initContactsDeletion();
    submitContactsDeletion();
  }
  public void deletion(Contacts contact) {
    selectContactsById(contact.getId());
    initContactsDeletion();
    submitContactsDeletion();
  }
  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<Contacts> list() {
    List<Contacts> contacts = new ArrayList<Contacts>();
    List<WebElement> tableRows = wd.findElements(By.cssSelector("tbody tr[name=\"entry\"]"));
    for (WebElement tableRow : tableRows) {
      String lastName = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(tableRow.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new Contacts().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withNumber("+375291567859").withEmail("liza709@gmail.com"));
    }
    return contacts;
  }

  public Contacts1 all() {
    Contacts1 contacts = new Contacts1();
    List<WebElement> tableRows = wd.findElements(By.cssSelector("tbody tr[name=\"entry\"]"));
    for (WebElement tableRow : tableRows) {
      String lastName = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(tableRow.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new Contacts()
              .withId(id).withFirstName(firstName).withLastName(lastName).withAddress("uliza gorelika").withNumber("+375291567859").withEmail("liza709@gmail.com"));
    }
    return contacts;
  }

}
