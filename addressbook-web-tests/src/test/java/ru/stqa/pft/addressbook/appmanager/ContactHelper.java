package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactHelper {
  private WebDriver wd;
  private JavascriptExecutor js;

  public ContactHelper(WebDriver wd) {
    this.wd=wd;
  }

  public void fillContacts(Contacts contactsForm) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactsForm.getFirstName());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactsForm.getLastName());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(contactsForm.getAddress());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(contactsForm.getNumber());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contactsForm.getEmail());
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void initNewContactsCreation() {
    wd.findElement(By.linkText("add new")).click();
  }
}
