package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
  private final Properties properties;
  public WebDriver wd;
  public JavascriptExecutor js;
  private  ContactHelper contactHelper;
  private  NavigationHelper navigationHelper ;
  private  GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private Browser browser;
  private DbHelper dbHelper;


  public ApplicationManager(Browser browser) {
    this.browser = browser;
    properties =new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target","local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

    dbHelper= new DbHelper();

    System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
    System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver.exe");
    System.setProperty("webdriver.edge.driver", "C:\\SeleniumDrivers\\msedgedriver.exe");
    if (browser.is(Browser.CHROME.browserName())){
      wd = new ChromeDriver();
    } else if (browser.is(Browser.FIREFOX.browserName())) {
      wd = new FirefoxDriver(new FirefoxOptions().setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"));
    }else if (browser.is(Browser.EDGE.browserName())){
      wd = new EdgeDriver(new EdgeOptions().setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe"));
    }
    js = (JavascriptExecutor) wd;
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));

  }


  public void stop() {
  wd.quit();
  }




  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contacts() {
    return contactHelper;
  }
  public DbHelper db(){
    return dbHelper;
  }
}
