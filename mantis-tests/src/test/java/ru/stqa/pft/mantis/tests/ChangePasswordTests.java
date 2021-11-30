package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChange() throws MessagingException, IOException, InterruptedException {
    UserData selectedUser = app.db().users().iterator().next();
    String newPassword = "newpass13";
    app.login().loginFromUI();
    app.goTo().manageUsersPage();
    app.user().resetPassword(selectedUser.getId());
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, selectedUser.getEmail());
    app.registration().finish(confirmationLink, newPassword);
    app.newSession().login(selectedUser.getLogin(), newPassword);
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
