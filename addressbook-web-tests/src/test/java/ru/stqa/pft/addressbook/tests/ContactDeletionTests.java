package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }

}

