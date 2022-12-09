package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {
  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected final static ApplicationManager app = new ApplicationManager(GetBrowserByName(System.getProperty("browser")));


  private static Browser GetBrowserByName(String browserName) {
    if (browserName.equals(Browser.CHROME.browserName())) {
      return Browser.CHROME;
    } else if (browserName.equals(Browser.EDGE.browserName())) {
      return Browser.EDGE;
    } else if (browserName.equals(Browser.IE.browserName())) {
      return Browser.IE;
    } else if (browserName.equals(Browser.FIREFOX.browserName())) {
      return Browser.FIREFOX;
    } else if (browserName.equals(Browser.SAFARI.browserName())) {
      return Browser.SAFARI;
    } else return Browser.HTMLUNIT;
  }

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + "with parameters" + Arrays.asList(p));

  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test" + m.getName());

  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }
    public void verifyContactsInUI() {
      if (Boolean.getBoolean("verifyUI")) {
        Contacts dbContacts = app.db().contacts();
        Contacts uiContacts = app.getContactsHelper().all();
        assertThat(uiContacts, CoreMatchers.equalTo(dbContacts.stream()
                .map((g) -> new Contact().withId(g.getId()).withFirstName(g.getFirstName()).withLastName(g.getLastName()).withAddress(g.getAddress()).withMobilePhone(g.getMobilePhone()).withEmail(g.getEmail()))
                .collect(Collectors.toSet())));
      }
  }
}
