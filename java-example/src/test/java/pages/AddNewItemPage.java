package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AddNewItemPage extends Page {
    public AddNewItemPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @FindBy(xpath = "//label[contains(text(), 'Enabled')]")
    public WebElement enabledStatusRadio;

    @FindBy(name = "name[en]")
    public WebElement itemNameInput;

    @FindBy(name = "code")
    public WebElement itemCodeInput;

    @FindBy(xpath = "//input[@data-name='Rubber Ducks']")
    public WebElement rubberDucksCheckbox;

    @FindBy(name = "quantity")
    public WebElement quantityInput;

    @FindBy(linkText = "Prices")
    private WebElement pricesTabLink;

    @FindBy(name = "purchase_price")
    public WebElement purchasePriceInput;

    @FindBy(name = "prices[USD]")
    public WebElement usdPriceInput;

    @FindBy(name = "prices[EUR]")
    public WebElement eurPriceInput;

    @FindBy(name = "save")
    public WebElement saveButton;

    public void setSoldOutStatus(String status) {
        selectFromList(name("sold_out_status_id"), status);
    }

    public void setCurrencyCode(String currencyCode) {
        selectFromList(name("purchase_price_currency_code"), currencyCode);
    }

    public void attachItemImage(File photo) {
        attach(cssSelector("input[type=file]"), photo);
    }

    public Boolean isGeneralSelected() {
        return driver.findElement(linkText("General")).isSelected();
    }

    public void toPricesTabTransition() {
        pricesTabLink.click();
        wait.until(visibilityOfElementLocated(name("purchase_price")));
    }
}
