package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteFromGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test3"));
    }
    if (!app.contact().isThereAContact())
      app.contact().create(new ContactData().withFirstName("Oleg")
              .withMiddleName("Petrovich").withLastName("Serov")
              .withAddress("Saint-Petersburg, Mayakovskaya street").withMobilePhone("79999099999")
              .withWorkPhone("111").withHomePhone("333").withEmail("test13@mail.ru").withEmail2("test2@mail.ru")
              .withEmail3("test3@mail.ru"));
    app.goTo().HomePage();
  }

  @Test
  public void testContactDeletingFromGroup() {
    Groups allGroups = app.db().groups();
    Contacts allContacts = app.db().contacts();
    Contacts contactsForRemoving = new Contacts();
    GroupData selectedGroup;
    ContactData deletedContactFromGroup;
    for (ContactData contactData : allContacts) {
      Groups groups = contactData.getGroups();
      if (groups.size() >= 1) {
        contactsForRemoving.add(contactData);
      }
    }
    if (contactsForRemoving.size() == 0) {
      ContactData addContactInGroup = allContacts.iterator().next();
      selectedGroup = allGroups.iterator().next();
      app.contact().addInGroup(addContactInGroup, selectedGroup);
      app.contact().goToHomepage();
      contactsForRemoving.add(addContactInGroup.inGroup(selectedGroup));
    }
    deletedContactFromGroup = contactsForRemoving.iterator().next();
    selectedGroup = deletedContactFromGroup.getGroups().iterator().next();
    app.contact().selectGroupList(selectedGroup);
    app.contact().removeInGroup(deletedContactFromGroup, selectedGroup);
    app.contact().goToHomepage();
    app.contact().selectGroupList(selectedGroup);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), allContacts.size());
    for (ContactData contact : after) {
      if (contact.getId() == deletedContactFromGroup.getId()) {
        assertThat(deletedContactFromGroup.getGroups().without(selectedGroup), equalTo(contact.getGroups()));
      }
    }
  }
}