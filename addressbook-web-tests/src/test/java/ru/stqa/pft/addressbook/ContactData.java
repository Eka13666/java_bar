package ru.stqa.pft.addressbook;

public record ContactData(String firstName, String middleName, String lastName, String companyName, String address,
                          String mobilePhone, String email) {
}