package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dina_Abdykasheva on 6/15/2017.
 */
public class LoginToGMailPage extends AbstractPage{
    private static final By USERNAME_INPUT_LOCATOR = By.name("identifier");
    private static final By NEXT_BUTTON_LOCATOR = By.id("identifierNext");
    private static final By PASSWORD_INPUT_LOCATOR = By.name("password");
    private static final By NEXT_BUTTON_LOCATOR1 = By.id("passwordNext");
    private static final By LOGIN_PAGE_LOCATOR = By.xpath(".//*[@class= 'sfYUmb']");
    public WebElementsUtils loginJSExecutor;

    public LoginToGMailPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage loginToGMail(String username, String password) {
        driver.get("https://www.google.com/gmail");
        driver.findElement(USERNAME_INPUT_LOCATOR).sendKeys(username);
        driver.findElement(NEXT_BUTTON_LOCATOR).click();
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        loginJSExecutor.executeJavaScript(NEXT_BUTTON_LOCATOR1, "arguments[0].click()");
        return new AccountPage(driver);
    }

    public boolean isUserLoggedOff() {
        return isElementPresent(LOGIN_PAGE_LOCATOR);
    }

}
