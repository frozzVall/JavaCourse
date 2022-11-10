package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class Contacts {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String number;
  private final String email;
  private final String group;

  private final String id;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contacts contacts = (Contacts) o;

    if (!Objects.equals(firstName, contacts.firstName)) return false;
    return Objects.equals(lastName, contacts.lastName);
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Contacts{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }



  public Contacts(String id, String firstName, String lastName, String address, String number, String email, String group) {
    this.id=id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.number = number;
    this.email = email;
    this.group = group;
  }

  public Contacts( String firstName, String lastName, String address, String number, String email, String group) {
    this.id=null;
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
