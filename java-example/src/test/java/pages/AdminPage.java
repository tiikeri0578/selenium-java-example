package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AdminPage extends Page{
    public AdminPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@id='box-apps-menu']/li")
    public List<WebElement> menuItems;

    @FindBy(xpath = "//ul[@class='docs']/li")
    public List<WebElement> subMenuItems;

    public Boolean isTitlePresent() {
        return isElementPresent(tagName("h1"));
    }
}
