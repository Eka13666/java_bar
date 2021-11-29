package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstName("Oleg")
              .withMiddleName("Petrovich").withLastName("Serov")
              .withAddress("Saint-Petersburg, Mayakovskaya street").withMobilePhone("79999099999")
              .withWorkPhone("111").withHomePhone("333").withEmail("test13@mail.ru").withEmail2("test2@mail.ru")
              .withEmail3("test3@mail.ru").inGroup(groups.iterator().next()));
    }
  }

  @Test

  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Oleg")
            .withMiddleName("Petrovich").withLastName("Ivanov")
            .withAddress("Saint-Petersburg, Ligovskyi 29").withMobilePhone("79999099999")
            .withWorkPhone("111").withHomePhone("333").withEmail("test13@mail.ru").withEmail2("test2@mail.ru")
            .withEmail3("test3@mail.ru");
    app.goTo().HomePage();
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
