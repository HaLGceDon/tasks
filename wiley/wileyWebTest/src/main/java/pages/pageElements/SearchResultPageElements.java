package pages.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;


/**
 * Метод описывает получение элементов на странице с результатами поиска
 */
public class SearchResultPageElements {

    public SearchResultPageElements(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;


    private WebElement getSearchResultPage() {
        List<WebElement> containers = driver.findElements(By.xpath("//div[@id = 'search-result-page-row']"));
        Assert.assertEquals(containers.size(), 1);
        return containers.get(0);
    }

    protected List<WebElement> getProductItems() {
        return getSearchResultPage().findElements(By.xpath(".//section[@class = 'product-item']"));
    }

    protected List<WebElement> getTitleWithText(WebElement productItem, String text) {
        return productItem.findElements(By.xpath(String.format(".//h3[@class = 'product-title']//span[text() = '%s']", text)));
    }

    protected List<WebElement> getAddToCartButton(WebElement productItem) {
        return productItem.findElements(By.xpath(".//button[text() = 'Add to cart']"));
    }

    protected List<WebElement> getViewOnLibraryButton(WebElement productItem) {
        return productItem.findElements(By.xpath(".//a[contains(text(), 'View on Wiley Online Library')]"));
    }
}
