package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.Contact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactsGenerators {
  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-f", description ="Target file")
  public String file;
  @Parameter(names = "-d", description ="Data format")
  public String format;



  public static void main(String[] args) throws IOException {
    ContactsGenerators generators=new ContactsGenerators();
    JCommander jCommander = new JCommander(generators);
    try{
      jCommander.parse(args);}
    catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generators.run();
  }

  private void run() throws IOException {
    List<Contact> contacts=generateContacts(count);
    if (format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    }else if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    }else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format"+ format);
    }
  }

  private void saveAsJson(List<Contact> contacts, File file) throws IOException {
    Gson gson =new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json =gson.toJson(contacts);
    try (Writer writer =new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<Contact> contacts, File file) throws IOException {
    XStream xStream=new XStream();
    xStream.processAnnotations(Contact.class);
    String xml =xStream.toXML(contacts);
    try (Writer writer =new FileWriter(file)) {
      writer.write(xml);
    }
  }


  private static void saveAsCsv(List<Contact> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (Contact contact1 : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s\n", contact1.getFirstName(), contact1.getLastName(),
                contact1.getAddress(), contact1.getMobilePhone(), contact1.getEmail()));
      }
    }
  }

  private static List<Contact> generateContacts(int count) {
    List<Contact> contacts=new ArrayList<Contact>();
    for(int i=0;i<count; i++){
      contacts.add(new Contact().withFirstName(String.format("Jin %s",i))
              .withLastName(String.format("Wang %s",i)).withAddress(String.format("ul gorelika %s",i)).withMobilePhone(String.format("+355776678 %s",i)).withEmail(String.format("jin1995@gmail.com",i)));

    }
    return contacts;
  }
}

