package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.allowTypes(new Class[]{ ContactData.class });
    xStream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContactsFromXml")
  public void testContactCreation(ContactData contact) {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/photo.jpeg");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

 }
}


