package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsPhoneTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size()==0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
    if (app.contacts().list().size()==0) {
      app.contacts().createContact(new Contacts().
              withFirstName("liza").withLastName("dlogyv").withAddress("uliza gorelika").withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857").withEmail( "liza709@gmail.com"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactsPhones(){
    app.goTo().homePage();
    Contacts contacts=app.contacts().all().iterator().next();
    Contacts contactInfoFromEditForm=app.contacts().infoFromEditForm(contacts);
    assertThat(contacts.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(Contacts contacts) {
    return Arrays.asList(contacts.getHomePhone(),contacts.getMobilePhone(),contacts.getWorkPhone())
            .stream().
            filter((s) -> ! s.equals("")).
            map(ContactsPhoneTest::cleaned).
            collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone ){
    return phone.replaceAll("\\s","").replaceAll("[-)(]","");
  }
}
