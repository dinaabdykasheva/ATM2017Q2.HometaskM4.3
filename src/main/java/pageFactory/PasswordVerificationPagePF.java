package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class PasswordVerificationPagePF extends AbstractPagePF {
    @FindBy(name = "password")
    WebElement passwordInput;

    @FindBy(xpath = ".//*[@id='passwordNext']/div[2]")
    WebElement nextButton;

    public PasswordVerificationPagePF(WebDriver driver) {
        super(driver);
    }

    public PasswordVerificationPagePF fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public AccountPagePF clickNextButton() {
        nextButton.click();
        return new AccountPagePF(driver);
    }
}
