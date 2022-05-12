package tests;

import com.tngtech.junit.dataprovider.DataProvider;
import entities.Customer;
import entities.Item;

import java.io.File;
import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static tests.Utils.*;

public class DataProviders {

    @DataProvider
    public static Object[][] validCustomer() {
        String firstName = genENString(2, 5);
        String lastName = genENString(2, 8);
        String phoneNumber = genPhoneNumber("+1");
        String address = genENString(4, 10) + genInt(1, 40);
        String postcode = genInt(10000, 99999).toString();
        String city = genENString(4, 7);
        String userEmail = genEmail();
        String userPassword = genInt(100, 1000).toString();
        return new Object[][] {
                { Customer.newEntity()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withPhoneNumber(phoneNumber)
                        .withAddress(address)
                        .withPostcode(postcode)
                        .withCity(city)
                        .withCountry("United states")
                        .withUserEmail(userEmail)
                        .withUserPassword(userPassword)
                        .build()
                }
        };
    }

    @DataProvider
    public static Object[][] validItem(){
        BigDecimal purchasePrice = genSumWithCent(10, 1000);
        BigDecimal usdPrice = purchasePrice.add(TEN);
        BigDecimal eurPrice = usdPrice.add(ONE);
        String itemName = genENString(4, 8);
        String code = genInt(100, 99999).toString();
        String quantity = genInt(100, 1000).toString();
        return new Object[][] {
                {Item.newEntity()
                        .withItemName(itemName)
                        .withCode(code)
                        .withPhoto(new File("src/test/resources/duck1.jpg"))
                        .withQuantity(quantity)
                        .withPurchasePrice(purchasePrice.toString())
                        .withPriceUSD(usdPrice.toString())
                        .withPriceEUR(eurPrice.toString())
                        .build()
                }
        };
    }
}
