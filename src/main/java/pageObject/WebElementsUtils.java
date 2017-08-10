package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by User on 06.08.2017.
 */
public class WebElementsUtils{

    static WebDriver driver;

    public static void executeJavaScript(By locator, String script) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript(script, element);
    }

    public static void moveToField(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator));
    }

    public static void fillInField(By locator, String keys) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).click().sendKeys(keys).build().perform();
    }
}
