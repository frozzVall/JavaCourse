package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {



  public SessionHelper(WebDriver wd) {
    super(wd);
  }
  public void login(String usermane, String password) {
    wd.get("http://localhost/addressbook/");
    type(By.name("user"),usermane);
    type(By.name("pass"),password);
   click(By.xpath("//input[@value='Login']"));
  }
  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }
}
