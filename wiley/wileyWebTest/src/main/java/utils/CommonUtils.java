package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

/**
 * В классе описаны общие методы
 */
public class CommonUtils {

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;


    //  -- Private methods  --------------------------------------------------------------------------------------------

    /**
     * Получение модального окна
     */
    private List<WebElement> getModal() {
        return driver.findElements(By.xpath(".//div[@class = 'modal-dialog']/div[@class = 'modal-content']"));
    }

    /**
     * Получение кнопки "YES" в модальном окне
     */
    private WebElement getYesButton() {
        List<WebElement> elements = getModal().get(0).findElements(By.xpath(".//button[text() = 'YES']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }


    //  -- Public methods  ---------------------------------------------------------------------------------------------

    /**
     * Нажатие "YES" в модальном окне, если оно присутсвует на странице
     */
    public void clickYesInModal() {
        if (getModal().size() == 1) {
            getYesButton().isDisplayed();
            getYesButton().click();
        }
    }

    /**
     * Наводит указатель мыши на выбранный элемент
     */
    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
}
