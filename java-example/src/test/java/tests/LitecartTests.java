package tests;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.junit.dataprovider.UseDataProvider;
import entities.Customer;
import entities.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(DataProviderRunner.class)
public class LitecartTests extends TestBase {

    @Test
    public void menuItemsTest() {
        app.adminLogin();
        int menuSize = app.getMenuSize();

        for (int i = 0; i < menuSize; i++) {
            app.menuItemClick(i);
            assertTrue(app.isTitlePresent());

            int subMenuSize = app.getSubMenuSize();
            if (subMenuSize > 0) {
                for (int k = 0; k < subMenuSize; k++) {
                    app.subMenuItemClick(k);
                    assertTrue(app.isTitlePresent());
                }
            }
        }
    }

    @Test
    public void stickersCheckTest() {
        List<WebElement> productItems = app.getAllProducts();

        for (WebElement productItem : productItems) {
            if (app.isStickerPresent(productItem)) {
                int stickersCount = app.getStickersCount(productItem);
                assertEquals(1, stickersCount);
            }
        }
    }

    @Test
    public void countriesSortTest() {
        ArrayList<String> countriesFromPage = app.getCountriesList();
        assertTrue(isListOrdered(countriesFromPage));

        ArrayList<String> urls = app.getCountryUrls();
        for (String url : urls) {
            ArrayList<String> geoZonesFromPages = app.getZonesList(url);
            assertTrue(isListOrdered(geoZonesFromPages));
        }
    }

    @Test
    public void zonesSortTest() {
        int count = app.getCountriesCount();

        for (int i = 0; i < count; i++) {
            ArrayList<String> zonesFromPages = app.getZonesList(i);
            assertTrue(isListOrdered(zonesFromPages));
            app.backToPreviousPage();
        }
    }

    @UseDataProvider(value = "validCustomer", location = DataProviders.class)
    @ParameterizedTest
    public void newUserRegTest(Customer customer) {
        app.registerNewCustomer(customer);
        String customerEmail = customer.getUserEmail();
        String customerPassword = customer.getUserPassword();
        app.customerLogout();

        app.customerLogin(customerEmail, customerPassword);
        app.customerLogout();
    }

    @UseDataProvider(value = "validItem", location = DataProviders.class)
    @ParameterizedTest
    public void itemAddTest(Item item) {
        app.addNewItem(item);
        String itemName = item.getItemName();

        assertTrue(app.isNewItemPresentInCatalog(itemName));
    }

    @Test
    public void cartTest() {
        final int count = 3;
        for (int i = 1; i < count + 1; i++) {
            app.addItemToCart(0);
            app.waitForCartUpdate(i);
            if (i != count) {
                app.backToPreviousPage();
            }
        }

        app.cartPageTransition();
        int rowCount = app.getItemsCount();
        if (rowCount > 1) {
            for (int i = rowCount; i > 0; i--) {
                int currentSize;
                app.deleteItemFromCart();
                if (app.isCartNotEmpty()) {
                    currentSize = app.getItemsCount();
                    while (currentSize == i) {
                        app.implicitlyWaiter();
                        currentSize = app.getItemsCount();
                    }
                }
            }
        } else {
            app.deleteItemFromCart();
        }
    }
}
