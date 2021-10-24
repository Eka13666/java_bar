package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getNavigationHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(0, "Oleg", "Petrovich", "Ivanov", "Saint-Petersburg, Mayakovskaya street, 29, fl. 13", "79999099999", "test13@mail.ru"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Oleg", "Petrovich", "Ivanov", "Saint-Petersburg, Mayakovskaya street, 29, fl. 13", "79999099999", "test13@mail.ru");
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    Assert.assertEquals(before, after);
  }
}
