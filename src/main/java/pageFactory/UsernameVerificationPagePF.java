package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class UsernameVerificationPagePF extends AbstractPagePF {
    @FindBy(name = "identifier")
    WebElement userNameInput;

    @FindBy(name = "identifier")
    WebElement nextButton;

    public UsernameVerificationPagePF(WebDriver driver) {
        super(driver);
    }

    public UsernameVerificationPagePF googleMailOpen() {
        driver.get("https://www.google.com/gmail");
        return this;
    }

    public UsernameVerificationPagePF fillUsername(String username) {
        userNameInput.sendKeys(username);
        return this;
    }

    public PasswordVerificationPagePF clickNextButton() {
        nextButton.click();
        return new PasswordVerificationPagePF(driver);
    }
}
