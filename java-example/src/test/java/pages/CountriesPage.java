package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CountriesPage extends Page{
    public CountriesPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    public CountriesPage open() {
        driver.get(ADMIN_URL + "?app=countries&doc=countries");
        wait.until(visibilityOfElementLocated(name("countries_form")));
        return this;
    }

    @FindBy(className = "row")
    List<WebElement> rows;

    public ArrayList<String> getCountries() {
        return (ArrayList<String>) rows.stream()
                .map(c -> c.findElements(tagName("td")).get(4).getText())
                .collect(toList());
    }

    public ArrayList<String> getUrls(){
        ArrayList<String> urls = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(tagName("td"));
            int count = parseInt(cells.get(5).getText());
            if (count > 0) {
                urls.add(cells.get(4).findElement(cssSelector("a")).getAttribute("href"));
            }
        }
        return urls;
    }

    public void countryPageTransition(String url){
        driver.get(url);
        wait.until(visibilityOfElementLocated(id("table-zones")));
    }
}
