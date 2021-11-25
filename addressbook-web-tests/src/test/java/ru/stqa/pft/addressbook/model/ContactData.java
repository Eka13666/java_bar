package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  public String firstName;

  @Expose
  @Column(name = "middlename")
  public String middleName;

  @Expose
  @Column(name = "lastname")
  public String lastName;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  public String address;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  public String mobilePhone;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  public String homePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  public String workPhone;

  @Transient
  public String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  public String email;

  @Column(name = "email2")
  @Type(type = "text")
  public String email2;

  @Column(name = "email3")
  @Type(type = "text")
  public String email3;

  @Transient
  public String allEmails;


/*   @Expose
   @Column(name = "photo")
   @Type(type = "text")
   private String photoPath;*/

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
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

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }


  public String getAllEmails() {
    return allEmails;
  }

  public int getId() {
    return id;
  }

/*  public File getPhoto() {
    if (photoPath == null) {
      return null;
    } else {
      File photo = new File(photoPath);
      return photo;
    }
  }
  public ContactData withPhoto(String photoPath) {
    this.photoPath = photoPath;
    return this;
  }*/

  public Groups getGroups() {
    return new Groups(groups);
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(homePhone, that.homePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, middleName, lastName, address, mobilePhone, homePhone, workPhone, email, email2, email3);
  }
}
