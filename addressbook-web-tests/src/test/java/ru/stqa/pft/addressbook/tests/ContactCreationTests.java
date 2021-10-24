package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoContactPage();
    ContactData contact = new ContactData(0, "Oleg", "Petrovich", "Ivanov", "Saint-Petersburg, Mayakovskaya street, 29, fl. 13", "79999099999", "test13@mail.ru");
    app.getContactHelper().createContact(new ContactData(0, "Oleg", "Petrovich", "Ivanov", "Saint-Petersburg, Mayakovskaya street, 29, fl. 13", "79999099999", "test13@mail.ru"));
    app.getContactHelper().goToHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

   before.add(contact);
   Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
   before.sort(byId);
   after.sort(byId);
   Assert.assertEquals(before, after);
 }
}


