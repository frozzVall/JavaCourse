package ru.stqa.pft.addressbook;

public class Contacts {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String number;
  private final String email;

  public Contacts(String firstName, String lastName, String address, String number, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.number = number;
    this.email = email;
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
}
