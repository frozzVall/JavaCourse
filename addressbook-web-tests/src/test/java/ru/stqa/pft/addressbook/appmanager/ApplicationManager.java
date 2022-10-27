package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.Contacts;

public class ApplicationManager {

  private final GroupHelper groupHelper = new GroupHelper();

  public void init() {
    System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
    groupHelper.wd = new ChromeDriver();
    groupHelper.js = (JavascriptExecutor) groupHelper.wd;
    login("admin","secret");
  }

  public void login(String usermane, String password) {
    groupHelper.wd.get("http://localhost/addressbook/");
    groupHelper.wd.findElement(By.name("user")).click();
    groupHelper.wd.findElement(By.name("user")).clear();
    groupHelper.wd.findElement(By.name("user")).sendKeys(usermane);
    groupHelper.wd.findElement(By.name("pass")).click();
    groupHelper.wd.findElement(By.name("pass")).clear();
    groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
    groupHelper.wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  public void gotoGroupPage() {
    groupHelper.wd.findElement(By.id("container")).click();
    groupHelper.returnToGroupPage();
  }

  public void stop() {
    groupHelper.wd.quit();
  }


  public boolean isElementPresent(By by) {
    try {
      groupHelper.wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      groupHelper.wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void returnToHomePage() {
    groupHelper.wd.findElement(By.linkText("home")).click();
  }

  public void fillContacts(Contacts contactsForm) {
    groupHelper.wd.findElement(By.name("firstname")).click();
    groupHelper.wd.findElement(By.name("firstname")).clear();
    groupHelper.wd.findElement(By.name("firstname")).sendKeys(contactsForm.getFirstName());
    groupHelper.wd.findElement(By.name("lastname")).click();
    groupHelper.wd.findElement(By.name("lastname")).clear();
    groupHelper.wd.findElement(By.name("lastname")).sendKeys(contactsForm.getLastName());
    groupHelper.wd.findElement(By.name("address")).click();
    groupHelper.wd.findElement(By.name("address")).clear();
    groupHelper.wd.findElement(By.name("address")).sendKeys(contactsForm.getAddress());
    groupHelper.wd.findElement(By.name("mobile")).click();
    groupHelper.wd.findElement(By.name("mobile")).clear();
    groupHelper.wd.findElement(By.name("mobile")).sendKeys(contactsForm.getNumber());
    groupHelper.wd.findElement(By.name("email")).click();
    groupHelper.wd.findElement(By.name("email")).clear();
    groupHelper.wd.findElement(By.name("email")).sendKeys(contactsForm.getEmail());
    groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void initNewContactsCreation() {
    groupHelper.wd.findElement(By.linkText("add new")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
}
