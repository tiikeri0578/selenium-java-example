package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.Keys.ENTER;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MainPage extends Page {
    private static final String BASE_URL = "http://localhost/litecart";

    public MainPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    public MainPage open() {
        driver.get(BASE_URL);
        wait.until(visibilityOfElementLocated(id("box-latest-products")));
        return this;
    }

    @FindBy(css = "div [id=box-most-popular] >  div [class=link]")
    private List<WebElement> mostPopularItems;

    @FindBy(css = "li.product")
    private List<WebElement> allItems;

    @FindBy(name = "email")
    public WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(css = "div.box a[href$=logout]")
    public WebElement logoutButton;

    public void itemPageTransition(int itemIndex) {
        mostPopularItems.get(itemIndex).click();
        wait.until(visibilityOfElementLocated(id("box-product")));
    }

    public List<WebElement> getAllItems() {
        return allItems;
    }

    public Boolean isStickerPresent(WebElement item) {
        return item.findElements(className("sticker")).size() > 0;
    }

    public int getStickersCount(WebElement item) {
        return item.findElements(className("sticker")).size();
    }

    public void customerLogin(String email, String password){
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password + ENTER);
        wait.until(visibilityOfElementLocated(id("box-account")));
    }
}
