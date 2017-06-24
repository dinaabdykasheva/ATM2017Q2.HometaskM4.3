package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class AccountPagePF extends AbstractPagePF {
    @FindBy(xpath = ".//span [@class='gb_8a gbii']")
    WebElement accountIcon;

    @FindBy(xpath = ".//div[@class='T-I J-J5-Ji T-I-KE L3']")
    WebElement writeMailButton;

    @FindBy(xpath = ".//a[contains(text(), 'Черновики')]")
    WebElement draftsFolder;

    @FindBy(xpath = ".//a[contains(text(), 'Отправленные')]")
    WebElement sentFolder;

    @FindBy(xpath = ".//a[contains(text(), 'Выход')]")
    WebElement exitButton;

    public AccountPagePF(WebDriver driver) {
        super(driver);
    }

    public WriteMailPagePF clickWriteMailButton() {
        writeMailButton.click();
        return new WriteMailPagePF(driver);
    }

    public boolean isElementPresent() {
        return accountIcon.isDisplayed();
    }

    public DraftsFolderPagePF openDrafts() {
        draftsFolder.click();
        return new DraftsFolderPagePF(driver);
    }

    public SentFolderPagePF openSentMail() {
        sentFolder.click();
        return new SentFolderPagePF(driver);
    }

    public AccountPagePF exitGMail() {
        accountIcon.click();
        exitButton.click();
        return this;
    }

}
