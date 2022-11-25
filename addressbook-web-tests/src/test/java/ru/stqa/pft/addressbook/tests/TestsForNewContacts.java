package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Contacts1;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestsForNewContacts extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
      String line;
      while ((line =reader.readLine())!= null) {
        if (line.contains(";")) {
          String[] split = line.split(";");
          list.add(new Object[]{new Contacts().withFirstName(split[0]).withLastName(split[1]).withAddress(split[2]).withMobilePhone(split[3]).withEmail(split[4])});
        }
      }

    }
    return list.iterator();
  }
  @BeforeMethod
  public void EnsurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @Test(dataProvider = "validContacts")
  public void testSForNewContacts(Contacts contacts)
          throws Exception {
    Contacts1 before = (Contacts1) app.contacts().all();
    File photo = new File("src/test/resources/1598554256-b6085814-390c-401f-b530-9b5c606a1feb.jpeg");
    app.contacts().createContact(contacts);
    app.goTo().homePage();
    assertThat(app.contacts().count(), equalTo(before.size() + 1));
    Contacts1 after = (Contacts1) app.contacts().all();


    assertThat(after, equalTo(before.
            withAdded(contacts.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test
  public void testBadSForNewContacts() throws Exception {
    Contacts1 before = (Contacts1) app.contacts().all();
    Contacts contacts = new Contacts().
            withFirstName("liza'").withLastName("dlogyv").withAddress("uliza gorelika")
            .withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857")
            .withEmail("liza709@gmail.com");
    app.contacts().createContact(contacts);
    app.goTo().homePage();
    assertThat(app.contacts().count(), equalTo(before.size()));
    Contacts1 after = (Contacts1) app.contacts().all();
    assertThat(after, equalTo(before));

  }
}
