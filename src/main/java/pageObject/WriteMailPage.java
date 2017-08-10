package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Dina_Abdykasheva on 6/15/2017.
 */
public class WriteMailPage extends AbstractPage{
    private static final By TO_FIELD_LOCATOR = By.xpath(".//div[@class = 'fX aXjCH']");
    private static final By SUBJECT_FIELD_LOCATOR = By.name("subjectbox");
    private static final By BODY_FIELD_LOCATOR = By.xpath(".//div[@role='textbox']");
    private static final By CLOSE_WRITE_MAIL_WINDOW_LOCATOR = By.xpath(".//img[@class='Ha']");
    private static final By SEND_MAIL_BUTTON_LOCATOR = By.xpath(".//div[@class='T-I J-J5-Ji aoO T-I-atl L3']");
    private static final By DRAFTS_FOLDER_LOCATOR = By.xpath(".//a[contains(text(), 'Черновики')]");
    private static final By SAVING_LABEL_LOCATOR = By.xpath(".//span[@class = 'oG aOy']");
    private static final By TO_FIELD_IN_DRAFT_LOCATOR = By.xpath(".//span[@class='vN bfK a3q']");
    public WebElementsUtils highlightExecutor, moveToReceiver, fillReceiver;

    public WriteMailPage(WebDriver driver) {
        super(driver);
    }

    public DraftsFolderPage writeMailAndSaveToDraft(String to, String subject, String body) {
        fillReceiver.fillInField(TO_FIELD_LOCATOR, to);
        driver.findElement(SUBJECT_FIELD_LOCATOR).sendKeys(subject);
        driver.findElement(BODY_FIELD_LOCATOR).sendKeys(body);
        waitForElementPresent(ExpectedConditions.visibilityOfElementLocated(SAVING_LABEL_LOCATOR));
        highlightExecutor.executeJavaScript(SAVING_LABEL_LOCATOR, "arguments[0].style.border='3px solid green'");
        highlightExecutor.executeJavaScript(SAVING_LABEL_LOCATOR, "arguments[0].style.border='0px'");
        driver.findElement(CLOSE_WRITE_MAIL_WINDOW_LOCATOR).click();
        driver.findElement(DRAFTS_FOLDER_LOCATOR).click();
        return new DraftsFolderPage(driver);
    }

    public String getReceiver() {
        moveToReceiver.moveToField(TO_FIELD_LOCATOR);
        return driver.findElement(TO_FIELD_IN_DRAFT_LOCATOR).getAttribute("email");
    }

    public String getSubject() {
        return driver.findElement(SUBJECT_FIELD_LOCATOR).getAttribute("value");
    }

    public String getBody() {
        return driver.findElement(BODY_FIELD_LOCATOR).getText();
    }

    public AccountPage sendMail() {
        driver.findElement(SEND_MAIL_BUTTON_LOCATOR).click();
        return new AccountPage(driver);
    }

}
