package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.support.PageFactory.initElements;

public class GeoZonePage extends Page{
    public GeoZonePage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    @FindBy(id = "table-zones")
    private WebElement table;

    public ArrayList<String> getZonesList(){
        List<WebElement> zoneRows = table.findElements(tagName("tr"));
        zoneRows.remove(0);
        zoneRows.remove(zoneRows.size() - 1);
        return (ArrayList<String>) zoneRows.stream().map(z -> z.findElements(tagName("td")).get(2)
                        .findElement(cssSelector("option[selected=selected]")).getText()).collect(toList());
    }
}
