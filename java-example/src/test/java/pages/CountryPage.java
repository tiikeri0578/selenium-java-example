package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.support.PageFactory.initElements;

public class CountryPage extends Page{
    public CountryPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @FindBy(id = "table-zones")
    WebElement table;

    public ArrayList<String> getGeoZones() {
        List<WebElement> zoneRows = table.findElements(tagName("tr"));
        zoneRows.remove(0);
        ArrayList<String> zonesFromPages = (ArrayList<String>) zoneRows.stream().map(z -> z.findElements(tagName("td"))
                        .get(2).getText()).collect(toList());
        zonesFromPages.remove(zonesFromPages.size() - 1);
        return zonesFromPages;
    }
}
