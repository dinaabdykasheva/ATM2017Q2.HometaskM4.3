package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 06.08.2017.
 */
public class SentMailPage extends AbstractPage {
    private static final By DELETE_MAIL_LOCATOR = By.xpath(".//div[@class = 'T-I J-J5-Ji nX T-I-ax7 T-I-Js-Gs ar7 T-I-JW']");
    private static final By MAIL_IS_DELETED_LOCATOR = By.xpath(".//span[contains(text(), 'Цепочка отправлена в корзину.')]");
    private static final By SET_LABEL_LOCATOR = By.xpath(".//div[contains(text(), 'Ярлыки')]");
    private static final By SELECT_LABEL_LOCATOR = By.xpath(".//div[contains(text(), 'test')]");
    private static final By LABEL_IS_SET_LOCATOR = By.xpath(".//div[contains(text(), 'Цепочка писем помечена ярлыком \"test\".')]");

    public SentMailPage(WebDriver driver) {
        super(driver);
    }

    public SentMailPage deleteSentMail() {
        driver.findElement(DELETE_MAIL_LOCATOR).click();
        return this;
    }

    public boolean isMailDeleted() {
        return isElementPresent(MAIL_IS_DELETED_LOCATOR);
    }

    public SentMailPage setLabel() {
        driver.findElement(SET_LABEL_LOCATOR);
        driver.switchTo().activeElement();
        driver.findElement(SELECT_LABEL_LOCATOR).click();
        return this;
    }

    public boolean isLabelSet() {
        return isElementPresent(LABEL_IS_SET_LOCATOR);
    }
}
