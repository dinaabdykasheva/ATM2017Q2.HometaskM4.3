package pageObject;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by User on 06.08.2017.
 */
public class SetLabelToMailTest extends BaseTest {
    public AccountPage accountPage;
    public SentFolderPage sentFolderPage;
    public SentMailPage sentMailPage;

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

    @Test(description = "setLabeltoMail", dependsOnMethods = "verifySentMail")
    public void setLabelToMailTest(){
        sentMailPage = new SentFolderPage(driver).openSentMail().setLabel();
        boolean isLabelSet = sentMailPage.isLabelSet();
        Assert.assertTrue(isLabelSet, "Label to mail wasn't set");
    }
}
