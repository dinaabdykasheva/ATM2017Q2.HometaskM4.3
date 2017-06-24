package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class DraftsFolderPage extends AbstractPage {
    private static final By DRAFT_MAIL_LOCATOR = By.xpath(".//span[contains(text(), 'mentoring task')]");

    public DraftsFolderPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDraftMailDisplayed() {
        return driver.findElement(DRAFT_MAIL_LOCATOR).isDisplayed();
    }

    public WriteMailPage openDraftMail() {
        driver.findElement(DRAFT_MAIL_LOCATOR).click();
        return new WriteMailPage(driver);
    }
}
