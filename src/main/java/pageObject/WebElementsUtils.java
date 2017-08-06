package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by User on 06.08.2017.
 */
public class WebElementsUtils extends AbstractPage{

    public WebElementsUtils(WebDriver driver) {
        super(driver);
    }

    public void jsExecutor(By locator, String script) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript(script, element);
    }

    public void simpleAction(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator));
    }

    public void complexAction(By locator, String keys) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).click().sendKeys(keys).build().perform();
    }
}
