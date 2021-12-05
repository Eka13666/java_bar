package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsersPage() {
    if (isElementPresent(By.linkText("Manage Accounts"))) {
      return;
    }
    click(By.xpath("//span[contains(text(),'Управление')]/.."));
    click(By.linkText("Управление пользователями"));
  }
}
