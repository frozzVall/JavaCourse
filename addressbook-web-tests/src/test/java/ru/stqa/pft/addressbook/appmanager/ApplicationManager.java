package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
  private SessionHelper sessionHelper;
  public WebDriver wd;

  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public void init() {
    System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
    wd = new ChromeDriver();
   navigationHelper = new NavigationHelper(wd);
   groupHelper = new GroupHelper(wd);
   groupHelper.js = (JavascriptExecutor) wd;
   sessionHelper =new SessionHelper(wd);
   sessionHelper.login("admin","secret");
  }

  private void login(String usermane, String password) {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(usermane);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  public void stop() {
    wd.quit();
  }



  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
