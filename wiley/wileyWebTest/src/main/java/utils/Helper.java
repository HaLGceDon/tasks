package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * В классе описаны методы для работы с WebDriver
 */
public class Helper {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public Helper() {
        String driverPath = Objects.requireNonNull(Helper.class.getClassLoader().getResource("driver/chromedriver.exe")).getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void close() {
        driver.close();
    }
}
