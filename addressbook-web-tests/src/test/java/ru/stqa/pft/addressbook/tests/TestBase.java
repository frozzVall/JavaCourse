package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;


public class TestBase {

  protected final static ApplicationManager app = new ApplicationManager(GetBrowserByName(System.getProperty("browser")));

  private static Browser GetBrowserByName(String browserName){
    if (browserName.equals(Browser.CHROME.browserName())){
      return Browser.CHROME;
    }
    else if (browserName.equals(Browser.EDGE.browserName())){
      return Browser.EDGE;
    }
    else if (browserName.equals(Browser.IE.browserName())){
      return Browser.IE;
    }
    else if (browserName.equals(Browser.FIREFOX.browserName())){
      return Browser.FIREFOX;
    }
    else if (browserName.equals(Browser.SAFARI.browserName())){
      return Browser.SAFARI;
    }
    else return  Browser.HTMLUNIT;
  }

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }
}
