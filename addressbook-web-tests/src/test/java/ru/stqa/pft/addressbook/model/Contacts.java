package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class Contacts {
  private String firstName;
  private String lastName;
  private String address;
  private String number;
  private String email;
  private String group;
  private int id=Integer.MAX_VALUE;

  public Contacts withId(int id) {
    this.id = id;
    return this;
  }
  public Contacts withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public Contacts withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public Contacts withAddress(String address) {
    this.address = address;
    return this;
  }

  public Contacts withNumber(String number) {
    this.number = number;
    return this;
  }

  public Contacts withEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contacts contacts = (Contacts) o;

    if (id != contacts.id) return false;
    return Objects.equals(firstName, contacts.firstName);
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + id;
    return result;
  }

  public Contacts withGroup(String group) {
    this.group = group;
    return this;
  }


  @Override
  public String toString() {
    return "Contacts{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", id='" + id + '\'' +
            '}';
  }

  public int getId() {
    return id;
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
