package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void addContact() {
    click(By.linkText("add new"));
  }

  public void logout() {
    click(By.linkText("Logout"));
  }

  public void goToHomepage() {
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactsForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());

  }

  public void selectContact (int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification () {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification () {
    click(By.xpath("//input[22]"));
  }

  public void deleteSelectedContacts () {
    click(By.xpath("//input[@value='Delete']"));

  }

  public void closeAlert () {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact) {
    addContact();
    fillContactsForm(contact);
    submitContactCreation();
    goToHomepage();
  }
  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//td/input"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String firstName = "Oleg";
      String lastName = "Ivanov";
      int id = Integer.parseInt(element.findElement(By.xpath("./td/input")).getAttribute("value"));
      ContactData contact = new ContactData(0, "Oleg", "Petrovich", "Ivanov", "Saint-Petersburg, Mayakovskaya street, 29, fl. 13", "79210000000", "test13@mail.ru");
      contacts.add(contact);
    }
    return contacts;
  }
}

