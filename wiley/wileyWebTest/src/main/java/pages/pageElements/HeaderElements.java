package pages.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;


/**
 * Метод описывает получение общих элементов Навигации и Поиска
 */
public class HeaderElements {

    public HeaderElements(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;


    private WebElement getHeaderContainer() {
        List<WebElement> containers = driver.findElements(By.xpath("//header[@id = 'main-header-container']"));
        Assert.assertEquals(containers.size(), 1);
        return containers.get(0);
    }

    private WebElement getNavigationSearchContainer() {
        List<WebElement> elements = getHeaderContainer().findElements(By.xpath(".//div[@class = 'main-navigation-search']/div[@class = 'container']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    private WebElement getHeaderNavbar() {
        List<WebElement> elements = getHeaderContainer().findElements(By.xpath(".//nav[@id = 'main-header-navbar']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    public WebElement getHeader(String headerText) {
        List<WebElement> elements = getHeaderNavbar().findElements(By.xpath(String.format(".//a[(@class = 'collapsed') and normalize-space (text()) = '%s']", headerText)));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected List<WebElement> getSubHeaderList(String headerText, String subHeaderText) {
        return getHeader(headerText).findElements(By.xpath(String.format("./parent::li//li[contains (@class, 'dropdown-item')]/a[normalize-space (text()) = '%s']", subHeaderText)));
    }

    protected int getSubHeaderCount(String headerText) {
        return getHeader(headerText).findElements(By.xpath("./parent::li//li[contains (@class, 'dropdown-item')]/a")).size();
    }

    public WebElement getSubHeader(String headerText, String subHeaderText) {
        List<WebElement> elements = getSubHeaderList(headerText, subHeaderText);
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected List<WebElement> getSecondSubHeaderList(String headerText, String subHeaderText, String secondSubHeaderText) {
        return getSubHeader(headerText, subHeaderText).findElements(By.xpath(String.format("./parent::li//li[contains (@class, 'dropdown-item')]/a[normalize-space (text()) = '%s']", secondSubHeaderText)));
    }

    protected int getSecondSubHeaderCount(String headerText, String subHeaderText) {
        return getSubHeader(headerText, subHeaderText).findElements(By.xpath("./parent::li//li[contains (@class, 'dropdown-item')]/a")).size();
    }

    protected WebElement getSearchField() {
        List<WebElement> elements = getNavigationSearchContainer().findElements(By.xpath(".//input[@id = 'js-site-search-input']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getSearchButton() {
        List<WebElement> elements = getNavigationSearchContainer().findElements(By.xpath(".//button[text() = 'Search']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected List<WebElement> getSearchResults() {
        return getNavigationSearchContainer().findElements(By.xpath(".//div[contains(@class, 'searchresults-item')]/a"));
    }
}
