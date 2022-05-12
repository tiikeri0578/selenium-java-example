package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CustomerRegistrationPage extends Page{
    public CustomerRegistrationPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    private static final String REG_PAGE_URL = "http://localhost/litecart/en/create_account";

    public CustomerRegistrationPage open() {
        driver.get(REG_PAGE_URL);
        wait.until(visibilityOfElementLocated(id("main")));
        return this;
    }

    @FindBy(name = "firstname")
    public WebElement firstNameInput;

    @FindBy(name = "lastname")
    public WebElement lastNameInput;

    @FindBy(name = "address1")
    public WebElement addressInput;

    @FindBy(name = "postcode")
    public WebElement postCodeInput;

    @FindBy(name = "city")
    public WebElement cityInput;

    @FindBy(name = "email")
    public WebElement emailInput;

    @FindBy(className = "selection")
    public WebElement countriesList;

    @FindBy(name = "phone")
    public WebElement phoneInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(name = "confirmed_password")
    public WebElement passwordConfirmInput;

    @FindBy(name = "create_account")
    public WebElement createAccountButton;

    public void selectCountry() {
        countriesList.click();
        typeWithConfirm(cssSelector("input[class=select2-search__field]"), "United states");
    }
}
