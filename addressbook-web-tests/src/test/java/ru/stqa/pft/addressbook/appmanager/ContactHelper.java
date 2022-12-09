package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContacts(Contact contactForm, boolean creation) {
    type(By.name("firstname"), contactForm.getFirstName());
    type(By.name("lastname"), contactForm.getLastName());
    type(By.name("address"), contactForm.getAddress());
    type(By.name("home"), contactForm.getHomePhone());
    type(By.name("mobile"), contactForm.getMobilePhone());
    type(By.name("work"), contactForm.getWorkPhone());
    type(By.name("email"), contactForm.getEmail());
    //attach(By.name("photo"), contactsForm.getPhoto());
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


  public void createContact(Contact contact) {
    initNewContactsCreation();
    contactsCache=null;
    fillContacts(contact, true);
  }

  public void modify(Contact contact) {
    initContactsModificationById(contact.getId());
    fillContacts(contact, false);
    contactsCache=null;
    submitContactsModification();
  }

  public void deletion(Contact contact) {
    selectContactsById(contact.getId());
    initContactsDeletion();
    contactsCache=null;
    submitContactsDeletion();
  }


  public List<Contact> list() {
    List<Contact> contacts = new ArrayList<Contact>();
    List<WebElement> tableRows = wd.findElements(By.cssSelector("tbody tr[name=\"entry\"]"));
    for (WebElement tableRow : tableRows) {
      String lastName = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(tableRow.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new Contact().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail( "liza709@gmail.com"));
    }
    return contacts;
  }

  private Contacts contactsCache=null;
  public Contacts all() {
    if(contactsCache!=null){
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement> tableRows = wd.findElements(By.cssSelector("tbody tr[name=\"entry\"]"));
    for (WebElement tableRow : tableRows) {
      String lastName = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstName = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String allPhones = tableRow.findElement(By.cssSelector("td:nth-child(6)")).getText();
      String allEmails = tableRow.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String address = tableRow.findElement(By.cssSelector("td:nth-child(4)")).getText();
      int id = Integer.parseInt(tableRow.findElement(By.tagName("input")).getAttribute("value"));

      contactsCache.add(new Contact().
              withId(id).withFirstName(firstName).withLastName(lastName).
              withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactsCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contact infoFromEditForm(Contact contact) {
    initContactsModificationById(contact.getId());
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
    return new Contact().withId(contact.getId()).withFirstName(firstName).withLastName(lastname).
            withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withAddress(address);
  }

  public void addContactToFirstGroup(Contact contact, GroupData group) {
    selectContactsById(contact.getId());
    clickAddContactToGroup(group);
  }

  public void deleteContactFromGroup(Contact contact, GroupData group) {
    SelectGroup(group);
    selectContactsById(contact.getId());
    wd.findElement(By.name("remove")).click();
  }

  private void SelectGroup(GroupData group) {
    Select groupSelect = new Select(wd.findElement(By.name("group")));
    String groupSelectValue = String.valueOf(group.getId());
    groupSelect.selectByValue(groupSelectValue);
  }

  private void clickAddContactToGroup(GroupData group) {
    Select groupSelect = new Select(wd.findElement(By.name("to_group")));

    String groupSelectValue = String.valueOf(group.getId());
    groupSelect.selectByValue(groupSelectValue);

    wd.findElement(By.xpath("//input[@value='Add to']")).click();
  }
}
