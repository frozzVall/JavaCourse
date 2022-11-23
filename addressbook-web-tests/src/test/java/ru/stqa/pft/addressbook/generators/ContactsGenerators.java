package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.Contacts;

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
    List<Contacts> groups=generateContacts(count);
    save(groups, new File(file));
  }

  private static void save(List<Contacts> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer =new FileWriter(file);
    for (Contacts contacts1: contacts){
      writer.write(String.format("%s;%s;%s;%s;%s\n", contacts1.getFirstName(),contacts1.getLastName(),
              contacts1.getAddress(),contacts1.getMobilePhone(),contacts1.getEmail()));
    }
    writer.close();
  }

  private static List<Contacts> generateContacts(int count) {
    List<Contacts> contacts=new ArrayList<Contacts>();
    for(int i=0;i<count; i++){
      contacts.add(new Contacts().withFirstName(String.format("Jin %s",i))
              .withLastName(String.format("Wang %s",i)).withAddress(String.format("ul gorelika %s",i)).withMobilePhone(String.format("+355776678 %s",i)).withEmail(String.format("jin1995@gmail.com",i)));

    }
    return contacts;
  }
}

