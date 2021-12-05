package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.cssSelector;

public class LoginHelper extends HelperBase {

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void loginFromUI() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    click(cssSelector("input[type='submit']"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(cssSelector("input[type='submit']"));
  }
}
