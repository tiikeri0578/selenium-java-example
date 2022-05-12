package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @FindBy(name = "remove_cart_item")
    public List<WebElement> deleteFromCartButtons;

    @FindBy(css = "td[class=item]")
    public List<WebElement> itemsTableRow;

    public void deleteItemFromCart(){
        wait.until(visibilityOf(deleteFromCartButtons.get(0))).click();
    }

    public boolean isCartNotEmpty(){
        return isElementPresent(cssSelector("td[class=item]"));
    }

}
