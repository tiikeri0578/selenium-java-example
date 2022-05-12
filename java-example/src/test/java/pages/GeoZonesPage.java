package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static pages.AdminLoginPage.ADMIN_URL;

public class GeoZonesPage extends Page{
    public GeoZonesPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @FindBy(className = "row")
    List<WebElement> rows;

    public GeoZonesPage open() {
        driver.get(ADMIN_URL + "?app=geo_zones&doc=geo_zones");
        wait.until(visibilityOfElementLocated(name("geo_zones_form")));
        return this;
    }

    public void zonePageTransition(int index){
        rows.get(index).findElement(cssSelector("a")).click();
        wait.until(visibilityOfElementLocated(id("table-zones")));
    }

    public int getCountriesCount(){
        return rows.size();
    }
}
