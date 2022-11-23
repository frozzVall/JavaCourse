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
    type(By.name("home"), contactsForm.getHomePhone());
    type(By.name("mobile"), contactsForm.getMobilePhone());
    type(By.name("work"), contactsForm.getWorkPhone());
    type(By.name("email"), contactsForm.getEmail());
    attach(By.name("photo"), contactsForm.getPhoto());
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
    contactsCache=null;
    fillContacts(contacts, true);
  }

  public void modify(Contacts contacts) {
    initContactsModificationById(contacts.getId());
    fillContacts(contacts, false);
    contactsCache=null;
    submitContactsModification();
  }

  public void deletion(Contacts contact) {
    selectContactsById(contact.getId());
    initContactsDeletion();
    contactsCache=null;
    submitContactsDeletion();
  }


  public List<Contacts> list() {
    List<Contacts> contacts = new ArrayList<Contacts>();
    List<WebElement> tableRows = wd.findElements(By.cssSelector("tbody tr[name=\"entry\"]"));
    for (WebElement tableRow : tableRows) {
      String lastName = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(tableRow.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new Contacts().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail( "liza709@gmail.com"));
    }
    return contacts;
  }

  private  Contacts1 contactsCache=null;
  public Contacts1 all() {
    if(contactsCache!=null){
      return new Contacts1(contactsCache);
    }
    contactsCache = new Contacts1();
    List<WebElement> tableRows = wd.findElements(By.cssSelector("tbody tr[name=\"entry\"]"));
    for (WebElement tableRow : tableRows) {
      String lastName = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String allPhones = tableRow.findElement(By.cssSelector("td:nth-child(6)")).getText();
      String allEmails = tableRow.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String address = tableRow.findElement(By.cssSelector("td:nth-child(4)")).getText();
      int id = Integer.parseInt(tableRow.findElement(By.tagName("input")).getAttribute("value"));

      contactsCache.add(new Contacts().
              withId(id).withFirstName(firstName).withLastName(lastName).
              withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts1(contactsCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts infoFromEditForm(Contacts contacts) {
    initContactsModificationById(contacts.getId());
    String firstName=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    String phone2=wd.findElement(By.name("phone2")).getAttribute("value");
    String email=wd.findElement(By.name("email")).getAttribute("value");
    String email2=wd.findElement(By.name("email2")).getAttribute("value");
    String email3=wd.findElement(By.name("email3")).getAttribute("value");
    String address=wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new Contacts().withId(contacts.getId()).withFirstName(firstName).withLastName(lastname).
            withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withAddress(address);
  }
}
