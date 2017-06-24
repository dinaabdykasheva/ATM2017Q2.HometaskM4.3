package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class SentFolderPagePF extends AbstractPagePF {
    @FindBy(xpath = ".//span[contains(text(), 'mentoring task')]")
    WebElement sentMail;

    public SentFolderPagePF(WebDriver driver) {
        super(driver);
    }

    public boolean isMailSent() {
        return sentMail.isDisplayed();
    }
}
