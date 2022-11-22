package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class Contacts {
  private String firstName;
  private String lastName;
  private String address;
  private String mobilePhone;
  private String homePhone;
  private String workPhone;
  private String allPhones;

  private String email;
  private String email2;
  private String email3;
  private String allEmails;

  public String getAllEmails() {
    return allEmails;
  }

  public Contacts withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public Contacts withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  private String group;
  private int id=Integer.MAX_VALUE;


  public Contacts withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public Contacts withHomePhone(String  homePhone) {
    this.homePhone = homePhone;
    return this;
  }
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

  public Contacts withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public Contacts withEmail(String email) {
    this.email = email;
    return this;
  }
  public Contacts withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }


  public Contacts withEmail3(String email3) {
    this.email3 = email3;
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

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {

    return group;
  }
  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }

}
