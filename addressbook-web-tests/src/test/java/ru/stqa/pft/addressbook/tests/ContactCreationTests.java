package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().addContact();
    app.getContactHelper().fillContactsForm(new ContactData("Oleg", "Petrovich", "Ivanov", "Test13", "Saint-Petersburg, Mayakovskaya street, 29, fl. 13", "79210000000", "test13@mail.ru", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().goToHomepage();
  }
}


