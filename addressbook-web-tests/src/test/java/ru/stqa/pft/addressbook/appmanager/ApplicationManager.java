package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

public class ApplicationManager {
  public WebDriver wd;
  public JavascriptExecutor js;
  private  ContactHelper contactHelper;
  private  NavigationHelper navigationHelper ;
  private  GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private Browser browser;


  public ApplicationManager(Browser browser) {
    this.browser = browser;
  }


  public void init() {
    System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
    System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver.exe");
    System.setProperty("webdriver.edge.driver", "C:\\SeleniumDrivers\\msedgedriver.exe");
    if (browser.equals(Browser.CHROME)){
      wd = new ChromeDriver();
    } else if (browser.equals(Browser.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"));
    }else if (browser.equals(Browser.EDGE)){
      wd = new EdgeDriver(new EdgeOptions().setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe"));
    }
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
