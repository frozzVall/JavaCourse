package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contacts")
@Entity
@Table (name ="addressbook")
public class Contact {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id=Integer.MAX_VALUE;
  @Expose
  @Column(name="firstname")
  private String firstName;
  @Expose
  @Column(name="lastname")
  private String lastName;
  @Expose
  @Column(name="address")
  @Type(type="text")
  private String address;
  @Expose
  @Column(name="mobile")
  @Type(type="text")
  private String mobilePhone;
  @Expose
  @Column(name="home")
  @Type(type="text")
  private String homePhone;
  @Expose
  @Column(name="work")
  @Type(type="text")
  private String workPhone;
  @Expose
  @Transient
  private String allPhones;
  @Expose
  @Column(name="phone2")
  @Type(type="text")
  private String phone2;
  @Expose
  @Column(name="email")
  @Type(type="text")
  private String email;
  @Expose
  @Column(name="email2")
  @Type(type="text")
  private String email2;
  @Expose
  @Column(name="email3")
  @Type(type="text")
  private String email3;
  @Expose
  @Transient
  private String allEmails;

  @Column(name="photo")
  @Type(type="text")
  @Transient
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name ="address_in_groups",
          joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="group_id"))
  private Set<GroupData> groups= new HashSet<GroupData>();

  public File getPhoto() {
    return new File(photo);
  }

  public Contact withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public Contact withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public Contact withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public Contact withPhone2(String phone2) {
    this.phone2 =phone2;
    return this;
  }
  public Contact withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public Contact withHomePhone(String  homePhone) {
    this.homePhone = homePhone;
    return this;
  }
  public Contact withId(int id) {
    this.id = id;
    return this;
  }
  public Contact withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public Contact withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public Contact withAddress(String address) {
    this.address = address;
    return this;
  }

  public Contact withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public Contact withEmail(String email) {
    this.email = email;
    return this;
  }
  public Contact withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }


  public Contact withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contact contact = (Contact) o;

    if (id != contact.id) return false;
    return Objects.equals(firstName, contact.firstName);
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + id;
    return result;
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

  public Groups getGroups() {
    return new Groups(groups);
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
  public String getPhone2() {
    return phone2;
  }

}
