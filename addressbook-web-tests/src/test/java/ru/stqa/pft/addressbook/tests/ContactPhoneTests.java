package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (! app.goTo().isThereAContact()) {
      app.contact().create(new ContactData().withFirstName("Oleg").withLastName("Ivanov").withMobilePhone("79990999999")
              .withHomePhone("8123567543").withWorkPhone("6665666").withAddress("Saint-Petersburg, Mayakovskaya street, 29, fl. 13").withEmail("email"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
   return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(), contact.getWorkPhone())
           .stream().filter((s) -> ! s.equals(""))
           .map(ContactPhoneTests::cleaned)
           .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}