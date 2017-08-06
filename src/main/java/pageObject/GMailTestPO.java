package pageObject;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Dina_Abdykasheva on 6/15/2017.
 */
public class GMailTestPO extends  AbstractPage{

    public DraftsFolderPage writeMail, openDraftFolder;
    public WriteMailPage openSavedDraft;
    public SentFolderPage sendMail;
    public LoginToGMailPage exitGMail;
    public AccountPage accountPage;

    public GMailTestPO(WebDriver driver) {
        super(driver);
    }

    public GMailTestPO() {}

    @Test(description = "loginToAccountTest", priority = 0)
    @Parameters({"username", "password"})
    public void loginToAccountTest(String username, String password) {
        accountPage = new LoginToGMailPage(driver).loginToGMail(username, password);
        boolean isAccountIconPresent = accountPage.isAccountIconPresent();
        Assert.assertTrue(isAccountIconPresent, "User isn't logged in");
    }

    @Test(description = "SaveToDraftTest", dependsOnMethods = "loginToAccountTest")
    @Parameters({"recipient", "subject", "body"})
    public void saveToDraftTest(String recipient, String subject, String body) {
        writeMail = new AccountPage(driver).clickWriteMailButton().writeMailAndSaveToDraft(recipient, subject, body);
        boolean isDraftMailSaved = writeMail.isDraftMailDisplayed();
        Assert.assertTrue(isDraftMailSaved, "Mail isn't saved in drafts");
    }

    @Test(description = "VerifySavedDraftReceiverTest", dependsOnMethods = "saveToDraftTest")
    @Parameters({"recipient"})
    public void verifySavedDraftReceiverTest(String recipient) {
        openSavedDraft = new DraftsFolderPage(driver).openDraftMail();
        String receiver = openSavedDraft.getReceiver();
        Assert.assertEquals(recipient, receiver, "Receiver isn't valid");
    }

    @Test(description = "VerifySavedDraftSubjectTest", dependsOnMethods = "verifySavedDraftReceiverTest")
    @Parameters({"subject"})
    public void verifySavedDraftSubjectTest(String subject) {
        String mailSubject = new WriteMailPage(driver).getSubject();
        Assert.assertEquals(subject, mailSubject, "Subject isn't valid");
    }

    @Test(description = "VerifySavedDraftBodyTest", dependsOnMethods = "verifySavedDraftSubjectTest")
    @Parameters({"body"})
    public void verifySavedDraftBodyTest(String body) {
        String mailBody = new WriteMailPage(driver).getBody();
        Assert.assertEquals(body, mailBody, "Body isn't valid");
    }

    @Test(description = "isMailSent", dependsOnMethods = {"verifySavedDraftReceiverTest", "verifySavedDraftSubjectTest", "verifySavedDraftBodyTest"})
    public void isMailSent() {
        sendMail = new WriteMailPage(driver).sendMail().openSentMail();
        boolean isMailSent = sendMail.isMailSent();
        Assert.assertTrue(isMailSent, "Mail wasn't sent");
    }

    @Test(description = "MailIsDeletedFromDraftsTest", dependsOnMethods = "isMailSent")
    public void mailIsDeletedFromDraftsTest() {
        openDraftFolder = new AccountPage(driver).openDrafts();
        boolean isMailDeletedFromDrafts = openDraftFolder.isDraftMailDisplayed();
        Assert.assertFalse(isMailDeletedFromDrafts, "Mail isn't deleted from drafts");
    }

    @Test(description = "ExitGMailTest", dependsOnMethods = "isMailSent")
    public void exitGMailTest() {
        exitGMail = new AccountPage(driver).exitGMail();
        boolean isUserLoggedOff = exitGMail.isUserLoggedOff();
        Assert.assertTrue(isUserLoggedOff, "User wasn't logged off");
    }
}
