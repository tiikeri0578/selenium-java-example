package app;

import entities.Customer;
import entities.Item;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class Application {

    private static final String SOLD_OUT_STATUS = "Temporary sold out";
    public static final String CURRENCY_CODE = "US Dollars";
    private final WebDriver driver;
    private final MainPage mainPage;
    private final ItemPage itemPage;
    private final CartPage cartPage;
    private final AdminLoginPage adminLoginPage;
    private final AdminPage adminPage;
    private final CountriesPage countriesPage;
    private final CountryPage countryPage;
    private final GeoZonesPage geoZonesPage;
    private final GeoZonePage geoZonePage;
    private final CustomerRegistrationPage customerRegistrationPage;
    private final CatalogPage catalogPage;
    private final AddNewItemPage addNewItemPage;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        itemPage = new ItemPage(driver);
        cartPage = new CartPage(driver);
        adminLoginPage = new AdminLoginPage(driver);
        adminPage = new AdminPage(driver);
        countriesPage = new CountriesPage(driver);
        countryPage = new CountryPage(driver);
        geoZonesPage = new GeoZonesPage(driver);
        geoZonePage = new GeoZonePage(driver);
        customerRegistrationPage = new CustomerRegistrationPage(driver);
        catalogPage = new CatalogPage(driver);
        addNewItemPage = new AddNewItemPage(driver);
    }

    public void adminLogin() {
        String adminName = "admin";
        String adminPassword = "admin";
        adminLoginPage.open().enterAdminName(adminName).enterAdminPassword(adminPassword);
    }

    public int getMenuSize() {
        return adminPage.menuItems.size();
    }

    public void menuItemClick(int itemIndex) {
        adminPage.menuItems.get(itemIndex).click();
    }

    public int getSubMenuSize() {
        return adminPage.subMenuItems.size();
    }

    public void subMenuItemClick(int itemIndex) {
        adminPage.subMenuItems.get(itemIndex).click();
    }

    public Boolean isTitlePresent() {
        return adminPage.isTitlePresent();
    }

    public List<WebElement> getAllProducts() {
        return mainPage.open().getAllItems();
    }

    public Boolean isStickerPresent(WebElement item) {
        return mainPage.isStickerPresent(item);
    }

    public int getStickersCount(WebElement item) {
        return mainPage.getStickersCount(item);
    }

    public void backToPreviousPage() {
        driver.navigate().back();
    }

    public void addItemToCart(int itemIndex) {
        mainPage.open().itemPageTransition(itemIndex);
        if (itemPage.isOptionsOnThisPage()) itemPage.selectFirstOption();
        itemPage.addToCartButton.click();
    }

    public void waitForCartUpdate(int itemsCount) {
        itemPage.waitForCartUpdate(itemsCount);
    }

    public void cartPageTransition() {
        itemPage.cartPageTransition();
    }

    public int getItemsCount() {
        return cartPage.itemsTableRow.size();
    }

    public void implicitlyWaiter() {
        driver.manage().timeouts().implicitlyWait(ofSeconds(1));
    }

    public void deleteItemFromCart() {
        cartPage.deleteItemFromCart();
    }

    public Boolean isCartNotEmpty() {
        return cartPage.isCartNotEmpty();
    }

    public ArrayList<String> getCountriesList() {
        adminLogin();
        return countriesPage.open().getCountries();
    }

    public ArrayList<String> getCountryUrls() {
        return countriesPage.getUrls();
    }

    public ArrayList<String> getZonesList(String url) {
        countriesPage.countryPageTransition(url);
        return countryPage.getGeoZones();
    }

    public int getCountriesCount() {
        adminLogin();
        return geoZonesPage.open().getCountriesCount();
    }

    public ArrayList<String> getZonesList(int index) {
        geoZonesPage.zonePageTransition(index);
        return geoZonePage.getZonesList();
    }

    public void customerLogout() {
        mainPage.logoutButton.click();
    }

    public void customerLogin(String email, String password) {
        mainPage.customerLogin(email, password);
    }

    public void registerNewCustomer(Customer customer) {

        customerRegistrationPage.open();
        customerRegistrationPage.firstNameInput.sendKeys(customer.getFirstName());
        customerRegistrationPage.lastNameInput.sendKeys(customer.getLastName());
        customerRegistrationPage.addressInput.sendKeys(customer.getAddress());
        customerRegistrationPage.postCodeInput.sendKeys(customer.getPostcode());
        customerRegistrationPage.cityInput.sendKeys(customer.getCity());
        customerRegistrationPage.selectCountry();
        customerRegistrationPage.emailInput.sendKeys(customer.getUserEmail());
        customerRegistrationPage.phoneInput.sendKeys(customer.getPhoneNumber());
        customerRegistrationPage.passwordInput.sendKeys(customer.getUserPassword());
        customerRegistrationPage.passwordConfirmInput.sendKeys(customer.getUserPassword());
        customerRegistrationPage.createAccountButton.click();
    }

    public void addNewItem(Item item) {
        adminLogin();
        catalogPage.open().addNewItemButton.click();
        addNewItemPage.isGeneralSelected();
        addNewItemPage.enabledStatusRadio.click();
        addNewItemPage.itemNameInput.sendKeys(item.getItemName());
        addNewItemPage.itemCodeInput.sendKeys(item.getCode());
        addNewItemPage.rubberDucksCheckbox.click();
        addNewItemPage.quantityInput.sendKeys(item.getQuantity());
        addNewItemPage.setSoldOutStatus(SOLD_OUT_STATUS);
        addNewItemPage.attachItemImage(item.getPhoto());
        addNewItemPage.toPricesTabTransition();
        addNewItemPage.purchasePriceInput.sendKeys(item.getPurchasePrice());
        addNewItemPage.setCurrencyCode(CURRENCY_CODE);
        addNewItemPage.usdPriceInput.sendKeys(item.getPriceUSD());
        addNewItemPage.eurPriceInput.sendKeys(item.getPriceEUR());
        addNewItemPage.saveButton.click();
    }

    public Boolean isNewItemPresentInCatalog(String itemName) {
        return catalogPage.open().isNewItemPresentInCatalog(itemName);
    }

    public void quit() {
        driver.quit();
    }
}
