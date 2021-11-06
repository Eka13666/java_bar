package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.File;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/photo.jpeg");
    ContactData contact = new ContactData()
            .withFirstName("Oleg").withMiddleName("Petrovich").withLastName("Ivanov")
            .withAddress("Saint-Petersburg, Mayakovskaya street, 29, fl. 13").withMobilePhone("79999099999")
            .withEmail("test13@mail.ru").withPhoto(photo);
    app.contact().create(contact);
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
 }
}


