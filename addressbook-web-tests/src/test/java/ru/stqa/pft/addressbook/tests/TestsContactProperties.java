package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contact;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestsContactProperties extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.getContactsHelper().createContact(new Contact().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail("liza709@gmail.com"));
    }
    app.goTo().homePage();
  }

  @Test
  public void ContactsPropertiesTests() {
    app.goTo().homePage();
    Contact contact = app.getContactsHelper().all().iterator().next();
    Contact contactInfoFromEditForm = app.getContactsHelper().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(cleanedForAddress(contactInfoFromEditForm.getAddress())));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }
  private String mergePhones(Contact contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPhone2())
            .stream().
            filter((s) -> !s.equals("")).
            map(TestsContactProperties::cleanedForPhones).
            collect(Collectors.joining("\n"));
  }
  private String mergeEmails(Contact contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().
            filter((s) -> !s.equals("")).
            map(TestsContactProperties::cleanedForEmails).
            collect(Collectors.joining("\n"));
  }
  public static String cleanedForPhones(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-)(]", "");
  }
  public static String cleanedForAddress(String address){
    return address.replaceAll("\\s+$","");
  }
  public static String cleanedForEmails(String email) {
    return email.replaceAll("\\s", "");
  }
}


