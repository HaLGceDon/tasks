package pages.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.pageElements.HeaderElements;


/**
 * Метод описывает работу с общими элементами Навигации и Поиска
 */
public class Header extends HeaderElements {

    public Header(WebDriver driver) {
        super(driver);
    }


    public void checkSubHeadersCount(String header, int expectedCount) {
        int actualCount = getSubHeaderCount(header);
        String errorMessage = String.format("In header '%s' found '%d' sub-header, expected - '%d'", header, actualCount, expectedCount);
        Assert.assertEquals(actualCount, expectedCount, errorMessage);
    }

    public void checkSubHeadersCount(String header, String subHeader, int expectedCount) {
        int actualCount = getSecondSubHeaderCount(header, subHeader);
        String errorMessage = String.format("In header '%s' - '%s' found '%d' sub-header, expected - '%d'", header, subHeader, actualCount, expectedCount);
        Assert.assertEquals(actualCount, expectedCount, errorMessage);
    }

    public void checkHeaderIsPresent(String header, String subHeader) {
        String errorMessage = String.format("Sub-header '%s' don't present in header '%s'", subHeader, header);
        Assert.assertEquals(getSubHeaderList(header, subHeader).size(), 1, errorMessage);
    }

    public void checkHeaderIsPresent(String header, String subHeader, String secondSubHeader) {
        String errorMessage = String.format("Sub-header '%s' don't present in header '%s' - '%s'", secondSubHeader, header, subHeader);
        Assert.assertEquals(getSecondSubHeaderList(header, subHeader, secondSubHeader).size(), 1, errorMessage);
    }

    public void fillSearchField(String text) {
        getSearchField().sendKeys(text);
    }

    public void clickSearchButton() {
        getSearchButton().isDisplayed();
        getSearchButton().click();
    }

    public void checkSearchResultDisplayed() {
        for (WebElement element : getSearchResults()) {
            Assert.assertTrue(element.isDisplayed());
        }
    }
}
