package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestsForNewContacts extends TestBase {
  @BeforeMethod
  public void EnsurePreconditions() {
    app.goTo().groupPage();
    if (app.db().contacts().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(Contacts.class);
      xStream.allowTypes(new Class[]{Contacts.class});
      List<Contacts> contacts = (List<Contacts>) xStream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<Contacts> contacts = gson.fromJson(json, new TypeToken<List<Contacts>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }
  @Test(dataProvider = "validContactsFromJson")
  public void testSForNewContacts(Contacts contacts)
          throws Exception {
    Contacts1 before = (Contacts1) app.db().contacts();
    File photo = new File("src/test/resources/1598554256-b6085814-390c-401f-b530-9b5c606a1feb.jpeg");
    app.contacts().createContact(contacts);
    app.goTo().homePage();
    assertThat(app.contacts().count(), equalTo(before.size() + 1));
    Contacts1 after = (Contacts1) app.db().contacts();


    assertThat(after, equalTo(before.
            withAdded(contacts.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
  public void testBadSForNewContacts() throws Exception {
    Contacts1 before = (Contacts1) app.db().contacts();
    Contacts contacts = new Contacts().
            withFirstName("liza'").withLastName("dlogyv").withAddress("uliza gorelika")
            .withHomePhone("+375447867789").withMobilePhone("+375291567859").withWorkPhone("+375291567857")
            .withEmail("liza709@gmail.com");
    app.contacts().createContact(contacts);
    app.goTo().homePage();
    assertThat(app.contacts().count(), equalTo(before.size()));
    Contacts1 after = (Contacts1) app.db().contacts();
    assertThat(after, equalTo(before));

  }
}
