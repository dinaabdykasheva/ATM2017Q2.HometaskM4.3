package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dina_Abdykasheva on 6/15/2017.
 */
public class LoginToGMailPage extends AbstractPage{
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10;
    private static final By USERNAME_INPUT_LOCATOR = By.name("identifier");
    private static final By NEXT_BUTTON_LOCATOR = By.id("identifierNext");
    private static final By PASSWORD_INPUT_LOCATOR = By.name("password");
    private static final By NEXT_BUTTON_LOCATOR1 = By.id("passwordNext");
    private static final By LOGIN_PAGE_LOCATOR = By.xpath(".//*[@class= 'sfYUmb']");

    public LoginToGMailPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage loginToGMail(String username, String password) {
        driver.get("https://www.google.com/gmail");
        driver.findElement(USERNAME_INPUT_LOCATOR).sendKeys(username);
        driver.findElement(NEXT_BUTTON_LOCATOR).click();
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        WebElement element = driver.findElement(NEXT_BUTTON_LOCATOR1);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        return new AccountPage(driver);
    }

    public boolean isUserLoggedOff() {
        return driver.findElement(LOGIN_PAGE_LOCATOR).isDisplayed();
    }

    protected void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON_LOCATOR));
    }
}
