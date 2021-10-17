package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().gotoContactPage();
    if (! app.getNavigationHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Oleg", "Petrovich", "Ivanov", "Test13", "Saint-Petersburg, Mayakovskaya street, 29, fl. 13", "79210000000", "test13@mail.ru", null));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    app.getContactHelper().closeAlert();
  }
}

