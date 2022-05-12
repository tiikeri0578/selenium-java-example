package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.ENTER;

public class Page {
    protected static WebDriver driver;
    protected WebDriverWait wait;
    protected static final String ADMIN_URL = "http://localhost/litecart/admin/";

    public Page(WebDriver driver) {
        Page.driver = driver;
        wait = new WebDriverWait(driver, ofSeconds(30));
    }

    protected boolean isElementPresent(By locator) {
        List<WebElement> list = driver.findElements(locator);
        return list.size() > 0;
    }

    protected void selectFromListByIndex(By locator, int index) {
        Select select = new Select(driver.findElement(locator));
        select.selectByIndex(index);
    }

    protected void selectFromList(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    protected void attach(By locator, File file) {
        if (file != null) {
            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected static void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    protected static void typeWithConfirm(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text + ENTER);
            }
        }
    }

    private static void click(By locator) {
        driver.findElement(locator).click();
    }
}
