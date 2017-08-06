package pageObject;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by User on 06.08.2017.
 */
public class DeleteSentMailTest extends AbstractPage {
    public AccountPage accountPage;
    public SentFolderPage sentFolderPage;
    public SentMailPage sentMailPage;

    public DeleteSentMailTest(WebDriver driver) {
        super(driver);
    }

    public DeleteSentMailTest() {
    }

    @Test(description = "loginToGMailTest", priority = 0)
    @Parameters({"username", "password"})
    public void loginToGMailTest(String username, String password) {
        accountPage = new LoginToGMailPage(driver).loginToGMail(username, password);
        boolean isAccountIconPresent = accountPage.isAccountIconPresent();
        Assert.assertTrue(isAccountIconPresent, "User isn't logged in");
    }

    @Test(description = "verifySentMail", dependsOnMethods = "loginToGMailTest")
    public void verifySentMail() {
        sentFolderPage = new AccountPage(driver).openSentMail();
        boolean isMailSent = sentFolderPage.isMailSent();
        Assert.assertTrue(isMailSent, "Mail wasn't sent");
    }

    @Test(description = "deleteSentMail", dependsOnMethods = "verifySentMail")
    public void deleteSentMail() {
        sentMailPage = new SentFolderPage(driver).openSentMail().deleteSentMail();
        boolean isMailDeleted = sentMailPage.isMailDeleted();
        Assert.assertTrue(isMailDeleted, "Mail wasn't deleted");

    }
}

