package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class WriteMailPagePF extends AbstractPagePF {

    @FindBy(xpath = ".//div[@class = 'fX aXjCH']")
    WebElement toField;

    @FindBy(name = "subjectbox")
    WebElement subjectField;

    @FindBy(xpath = ".//div[@role='textbox']")
    WebElement bodyField;

    @FindBy(xpath = ".//img[@class='Ha']")
    WebElement closeWriteMailWindowButton;

    @FindBy(xpath = ".//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
    WebElement sendMailButton;

    public WriteMailPagePF(WebDriver driver) {
        super(driver);
    }

    public WriteMailPagePF fillToField(String to) {
        toField.sendKeys(to);
        return this;
    }

    public WriteMailPagePF fillSubjectField(String subject) {
        subjectField.sendKeys(subject);
        return this;
    }

    public WriteMailPagePF fillBodyField(String body) {
        bodyField.sendKeys(body);
        return this;
    }

    public WriteMailPagePF clickCloseButton() {
        closeWriteMailWindowButton.click();
        return this;
    }

    public String getReceiver() {
        return toField.getAttribute("email");
    }

    public String getSubject() {
        return subjectField.getAttribute("value");
    }

    public String getBody() {
        return bodyField.getText();
    }

    public AccountPagePF sendMail() {
        sendMailButton.click();
        return new AccountPagePF(driver);
    }
}
