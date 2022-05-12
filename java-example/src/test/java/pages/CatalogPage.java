package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CatalogPage extends Page{
    public CatalogPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Add New Product')]")
    public WebElement addNewItemButton;

    @FindBy(linkText = "Rubber Ducks")
    private WebElement rubberDucksLink;

    public CatalogPage open(){
        driver.get(ADMIN_URL + "?app=catalog&doc=catalog");
        wait.until(visibilityOfElementLocated(className("dataTable")));
        return this;
    }

    public Boolean isNewItemPresentInCatalog(String itemName) {
        rubberDucksLink.click();
        wait.until(visibilityOfElementLocated(className("dataTable")));
        return driver.findElement(linkText(itemName)).isDisplayed();
    }
}
