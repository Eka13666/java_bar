package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (!app.goTo().isThereAContact()) {
      app.contact().create(new ContactData().withFirstName("Oleg").withLastName("Ivanov").withMobilePhone("79990999999")
              .withHomePhone("8123567543").withWorkPhone("6665666").withAddress("Saint-Petersburg, Mayakovskaya street, 29, fl. 13").withEmail("email"));
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(mergeAdress(contactInfoFromEditForm)));
  }

  private String mergeAdress(ContactData contact) {
    return Arrays.asList(contact.getAddress()).stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }
}
