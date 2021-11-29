package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactsForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
//    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void confirmAdding() {
    click(By.xpath("//input[@value='Add to']"));
  }
  private void confirmRemoving() {
    click(By.name("remove"));
  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupById(group.getId());
    confirmAdding();
    goToHomePage();
  }

  public Groups findGroupForAdding(ContactData contact, Groups groups) {
    Groups groupsInContact = contact.getGroups();
    groups.removeAll(groupsInContact);
    return groups;
  }

  private void goToHomePage() {
    click(By.linkText("home"));
  }

  public void addInGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
    click(By.xpath("(//input[@name='add'])"));
  }

  public void removeInGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    click(By.xpath("(//input[@name='remove'])"));
  }

  public void selectGroupList(GroupData group) {
    new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(group.getId()));;
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();
  }

  public void selectGroupById(int id) {
    click(By.cssSelector("select[name=\"to_group\"] > option[value='" + id + "']"));
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    contactCache = null;
    closeAlert();
    goToHomepage();
  }

  public void closeAlert () {
    wd.switchTo().alert().accept();
  }

  private Contacts contactCache = null;

  public void create(ContactData contactData) {
    addContact();
    fillContactsForm(contactData,true);
    submitContactCreation();
    contactCache = null;
    goToHomepage();
  }
  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactsForm(contact, true);
    submitContactModification();
    contactCache = null;
    goToHomepage();
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement element = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = element.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//td/input"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      int id = Integer.parseInt(element.findElement(By.xpath("./td/input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).
              withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).
            withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email)
            .withEmail2(email2).withEmail3(email3);

  }

}
