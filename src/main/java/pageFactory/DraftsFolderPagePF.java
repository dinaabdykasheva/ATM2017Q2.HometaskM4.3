package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class DraftsFolderPagePF extends AbstractPagePF {

    @FindBy(xpath = ".//span[contains(text(), 'mentoring task')]")
    WebElement draftMail;

    public DraftsFolderPagePF(WebDriver driver) {
        super(driver);
    }

    public boolean isDraftMailSaved() {
        return draftMail.isDisplayed();
    }

    public boolean isDraftMailDeleted() {
        return !draftMail.isDisplayed();
    }

    public WriteMailPagePF openDraftMail() {
        draftMail.click();
        return new WriteMailPagePF(driver);
    }
}
