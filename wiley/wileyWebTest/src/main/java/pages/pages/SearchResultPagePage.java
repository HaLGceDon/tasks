package pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.pageElements.SearchResultPageElements;


/**
 * Метод описывает работу с элементами на странице с результатами поиска
 */
public class SearchResultPagePage extends SearchResultPageElements {

    public SearchResultPagePage(WebDriver driver) {
        super(driver);
    }


    public void checkProductItemCount(int expectedCount) {
        Assert.assertEquals(getProductItems().size(), expectedCount);
    }

    public void checkAddOrViewButtonPresent() {
        boolean buttonIsPresent;
        for (WebElement product : getProductItems()) {
            buttonIsPresent = getAddToCartButton(product).size() > 0 || getViewOnLibraryButton(product).size() > 0;
            Assert.assertTrue(buttonIsPresent);
        }
    }

    public void checkProductTitleContainsText(String text) {
        boolean textIsPresent;
        for (WebElement product : getProductItems()) {
            textIsPresent = getTitleWithText(product, text).size() > 0;
            Assert.assertTrue(textIsPresent);
        }
    }
}
