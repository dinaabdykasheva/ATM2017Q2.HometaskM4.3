package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Dina_Abdykasheva on 6/15/2017.
 */
public class AccountPage extends AbstractPage {
    private static final By ACCOUNT_ICON_LOCATOR = By.xpath(".//span [@class='gb_8a gbii']");
    private static final By WRITE_MAIL_BUTTON_LOCATOR = By.xpath(".//div[@class='T-I J-J5-Ji T-I-KE L3']");
    private static final By DRAFTS_FOLDER_LOCATOR = By.xpath(".//a[contains(text(), 'Черновики')]");
    private static final By SENT_MAIL_FOLDER_LOCATOR = By.xpath(".//a[contains(text(), 'Отправленные')]");
    private static final By EXIT_BUTTON_LOCATOR = By.xpath(".//a[contains(text(), 'Выйти')]");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public WriteMailPage clickWriteMailButton() {
        driver.findElement(WRITE_MAIL_BUTTON_LOCATOR).click();
        return new WriteMailPage(driver);
    }

    public boolean isAccountIconPresent() {
        return !driver.findElements(ACCOUNT_ICON_LOCATOR).isEmpty();
    }

    public DraftsFolderPage openDrafts() {
        driver.switchTo().defaultContent();
        driver.findElement(DRAFTS_FOLDER_LOCATOR).click();
        return new DraftsFolderPage(driver);
    }

    public SentFolderPage openSentMail() {
        driver.switchTo().defaultContent();
        driver.findElement(SENT_MAIL_FOLDER_LOCATOR).click();
        return new SentFolderPage(driver);
    }

    public AccountPage exitGMail() {
        driver.findElement(ACCOUNT_ICON_LOCATOR).click();
        driver.findElement(EXIT_BUTTON_LOCATOR).click();
        return this;
    }
}
