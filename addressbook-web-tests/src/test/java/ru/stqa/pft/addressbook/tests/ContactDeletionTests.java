package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getNavigationHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(0, "Oleg", "Petrovich", "Ivanov", "Saint-Petersburg, Mayakovskaya street, 29, fl. 13", "79999099999", "test13@mail.ru"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    app.getContactHelper().closeAlert();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}

