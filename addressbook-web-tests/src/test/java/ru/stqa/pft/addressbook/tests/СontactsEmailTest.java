package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class СontactsEmailTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    if (app.contacts().list().size() == 0) {
      app.contacts().createContact(new Contacts().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika")
              .withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857")
              .withEmail("liza709@gmail.com").withEmail2("709@gmail.com").withEmail3("li9@gmail.com"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactsEmail() {
    app.goTo().homePage();
    Contacts contacts = app.contacts().all().iterator().next();
    Contacts contactInfoFromEditForm = app.contacts().infoFromEditForm(contacts);
    assertThat(contacts.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(Contacts contacts) {
    return Arrays.asList(contacts.getEmail(), contacts.getEmail2(), contacts.getEmail3())
            .stream().
            filter((s) -> !s.equals("")).
            map(СontactsEmailTest::cleaned).
            collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "");
  }
}
