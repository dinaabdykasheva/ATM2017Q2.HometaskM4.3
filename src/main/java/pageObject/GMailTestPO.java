package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dina_Abdykasheva on 6/15/2017.
 */
public class GMailTestPO {
    private WebDriver driver;

    @BeforeClass(description = "StartBrowser")
    private void startBrowser() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(description = "loginToAccountTest", priority = 0, dataProvider = "LoginToAccountDataProvider")
    @Parameters({"username", "password"})
    public void loginToAccountTest(String username, String password) {
        AccountPage accountPage = new LoginToGMailPage(driver).loginToGMail(username, password);
        boolean isAccountIconPresent = accountPage.isAccountIconPresent();
        Assert.assertTrue(isAccountIconPresent, "User isn't logged in");
    }

    @DataProvider(name = "LoginToAccountDataProvider")
    public Object[][] loginToAccountDataProvider() {
        return new Object[][] {
                {"test.da.10062017", "testtest01"}
        };
    }

    @Test(description = "SaveToDraftTest", dependsOnMethods = "loginToAccountTest", dataProvider = "SaveToDraftDataProvider")
    @Parameters({"recipient", "subject", "body"})
    public void saveToDraftTest(String recipient, String subject, String body) {
        DraftsFolderPage writeMail = new AccountPage(driver).clickWriteMailButton().writeMailAndSaveToDraft(recipient, subject, body);
        boolean isDraftMailSaved = writeMail.isDraftMailDisplayed();
        Assert.assertTrue(isDraftMailSaved, "Mail isn't saved in drafts");
    }

    @DataProvider(name = "SaveToDraftDataProvider")
    public Object[][] saveToDraftDataProvider() {
        return new Object[][] {
                {"dina_abdykasheva@mail.ru", "mentoring task", "body text"}
        };
    }

    @Test(description = "VerifySavedDraftReceiverTest", dependsOnMethods = "saveToDraftTest", dataProvider = "VerifySavedDraftReceiverDataProvider")
    @Parameters({"recipient"})
    public void verifySavedDraftReceiverTest(String recipient) {
        WriteMailPage openSavedDraft = new DraftsFolderPage(driver).openDraftMail();
        String receiver = openSavedDraft.getReceiver();
        Assert.assertEquals(recipient, receiver, "Receiver isn't valid");
    }

    @DataProvider(name = "VerifySavedDraftReceiverDataProvider")
    public Object[][] verifySavedDraftReceiverDataProvider() {
        return new Object[][] {
                {"dina_abdykasheva@mail.ru"}
        };
    }

    @Test(description = "VerifySavedDraftSubjectTest", dependsOnMethods = "verifySavedDraftReceiverTest", dataProvider = "VerifySavedDraftSubjectDataProvider")
    @Parameters({"subject"})
    public void verifySavedDraftSubjectTest(String subject) {
        String mailSubject = new WriteMailPage(driver).getSubject();
        Assert.assertEquals(subject, mailSubject, "Subject isn't valid");
    }

    @DataProvider(name = "VerifySavedDraftSubjectDataProvider")
    public Object[][] verifySavedDraftSubjectReceiverDataProvider() {
        return new Object[][] {
                {"mentoring task"}
        };
    }

    @Test(description = "VerifySavedDraftBodyTest", dependsOnMethods = "verifySavedDraftSubjectTest", dataProvider = "VerifySavedDraftBodyDataProvider")
    @Parameters({"body"})
    public void verifySavedDraftBodyTest(String body) {
        String mailBody = new WriteMailPage(driver).getBody();
        Assert.assertEquals(body, mailBody, "Body isn't valid");
    }

    @DataProvider(name = "VerifySavedDraftBodyDataProvider")
    public Object[][] verifySavedDraftBodyDataProvider() {
        return new Object[][] {
                {"body text"}
        };
    }

    @Test(description = "isMailSent", dependsOnMethods = {"verifySavedDraftReceiverTest", "verifySavedDraftSubjectTest", "verifySavedDraftBodyTest"})
    public void isMailSent() {
        SentFolderPage sendMail = new WriteMailPage(driver).sendMail().openSentMail();
        boolean isMailSent = sendMail.isMailSent();
        Assert.assertTrue(isMailSent, "Mail wasn't sent");
    }

    @Test(description = "MailIsDeletedFromDraftsTest", dependsOnMethods = "isMailSent")
    public void mailIsDeletedFromDraftsTest() {
        DraftsFolderPage openDraftFolder = new AccountPage(driver).openDrafts();
        boolean isMailDeletedFromDrafts = openDraftFolder.isDraftMailDisplayed();
        Assert.assertFalse(isMailDeletedFromDrafts, "Mail isn't deleted from drafts");
    }

    @Test(description = "ExitGMailTest", dependsOnMethods = "isMailSent")
    public void exitGMailTest() {
        LoginToGMailPage exitGMail = new AccountPage(driver).exitGMail();
        boolean isUserLoggedOff = new LoginToGMailPage(driver).isUserLoggedOff();
        Assert.assertTrue(isUserLoggedOff, "User wasn't logged off");
    }

    @AfterClass(description = "closeDriver")
    public void closeDriver() {
        driver.close();
    }
}
