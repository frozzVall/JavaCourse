package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
  public WebDriver wd;
  public JavascriptExecutor js;
  private  ContactHelper contactHelper;
  private  NavigationHelper navigationHelper ;
  private  GroupHelper groupHelper;
  private SessionHelper sessionHelper;

  public void init() {
    System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
    wd = new ChromeDriver();
    js = (JavascriptExecutor) wd;
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin","secret");
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

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
