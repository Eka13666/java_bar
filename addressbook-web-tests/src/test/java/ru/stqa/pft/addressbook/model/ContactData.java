package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  public int id;
  public String firstName;
  public String middleName;
  public String lastName;
  public String address;
  public String mobilePhone;
  public String email;

  public ContactData(String firstName, String middleName, String lastName, String address, String mobilePhone, String email) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.email = email;
  }
  public ContactData(int id, String firstName, String middleName, String lastName, String address, String mobilePhone, String email) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.address = address;
    this.mobilePhone = mobilePhone;
    this.email = email;
  }

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

  public String getEmail() {
    return email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
