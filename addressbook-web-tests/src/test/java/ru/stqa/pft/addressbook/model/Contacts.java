package ru.stqa.pft.addressbook.model;

public class Contacts {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String number;
  private final String email;
  private final String group;

  public Contacts(String firstName, String lastName, String address, String number, String email,String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.number = number;
    this.email = email;
    this.group = group;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getNumber() {
    return number;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
