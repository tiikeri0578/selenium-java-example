package pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AdminLoginPage extends Page {

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public AdminLoginPage open() {
        driver.get(ADMIN_URL);
        wait.until(presenceOfElementLocated(name("login")));
        return this;
    }

    public AdminLoginPage enterAdminName(String adminName) {
        type(name("username"), adminName);
        return this;
    }

    public AdminLoginPage enterAdminPassword(String adminPassword) {
        typeWithConfirm(name("password"), adminPassword);
        wait.until(visibilityOfElementLocated(id("box-apps-menu")));
        return this;
    }
}
