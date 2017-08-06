package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dina_Abdykasheva on 6/15/2017.
 */
public class AbstractPage {

    @BeforeClass(description = "StartBrowser")
    public void startBrowser() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public WebDriver driver;

    public AbstractPage() {
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void waitForElementPresent(ExpectedCondition<WebElement> expectedConditions) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(expectedConditions);
    }

    @AfterClass(description = "closeDriver")
    public void closeDriver() {
        driver.close();
    }
}
