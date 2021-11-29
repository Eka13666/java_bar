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


public class ContactAddToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().HomePage();
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
    app.contact().goToHomepage();
  }


  @Test
  public void testContactAddToGroup() {
    Groups allGroups = app.db().groups();
    Contacts allContacts = app.db().contacts();
    Contacts contactsForAdding = new Contacts();
    GroupData selectedGroup;
    for (ContactData contactData : allContacts) {
      Groups groups = contactData.getGroups();
      if (groups.size() < allGroups.size()) {
        contactsForAdding.add(contactData);
      }
    }
    if (contactsForAdding.size() == 0) {
      GroupData newGroup = new GroupData().withName("test3");
      app.goTo().groupPage();
      app.group().create(newGroup);
      allGroups = app.db().groups();
      contactsForAdding = allContacts;
      app.goTo().groupPage();
      ContactData addedContactToGroup = contactsForAdding.iterator().next();
      selectedGroup = app.contact().findGroupForAdding(addedContactToGroup, allGroups).iterator().next();
      app.contact().addInGroup(addedContactToGroup, selectedGroup);
      app.contact().goToHomepage();
      app.contact().selectGroupList(selectedGroup);
      Contacts after = app.db().contacts();
      assertEquals(after.size(), allContacts.size());
      for (ContactData contact : after) {
        if (contact.getId() == addedContactToGroup.getId()) {
          assertThat(addedContactToGroup.getGroups().withAdded(selectedGroup), equalTo(contact.getGroups()));
        }
      }
    }
  }
}