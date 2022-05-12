package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.String.valueOf;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ItemPage extends Page{
    public ItemPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @FindBy(name = "add_cart_product")
    public WebElement addToCartButton;

    @FindBy(linkText = "Checkout »")
    private WebElement linkToCart;

    public boolean isOptionsOnThisPage(){
       return isElementPresent(cssSelector("[name^=options]"));
    }

    public void selectFirstOption() {
        selectFromListByIndex(cssSelector("[name^=options]"), 1);
    }

    public void waitForCartUpdate(int itemsCount) {
        wait.until(attributeContains(cssSelector("span[class=quantity]"), "innerHTML", valueOf(itemsCount)));
    }

    public void cartPageTransition() {
        linkToCart.click();
        wait.until(visibilityOfElementLocated(id("box-checkout-cart")));
    }

}
