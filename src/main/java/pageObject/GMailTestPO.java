package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

    @Test(description = "loginToAccountTest", priority = 0)
    public void loginToAccountTest() {
        AccountPage accountPage = new LoginToGMailPage(driver).loginToGMail("test.da.10062017" ,"testtest01");
        boolean isAccountIconPresent = accountPage.isAccountIconPresent();
        Assert.assertTrue(isAccountIconPresent, "User isn't logged in");
    }

    @Test(description = "SaveToDraftTest", priority = 1)
    public void saveToDraftTest() {
        DraftsFolderPage writeMail = new AccountPage(driver).clickWriteMailButton().writeMailAndSaveToDraft("dina_abdykasheva@mail.ru", "mentoring task", "body text");
        boolean isDraftMailSaved = new DraftsFolderPage(driver).isDraftMailDisplayed();
        Assert.assertTrue(isDraftMailSaved, "Mail isn't saved in drafts");
    }

    @Test(description = "VerifySavedDraftTestAndSendEmail", priority = 2)
    public void verifySavedDraftTestAndSentEmail() {
        WriteMailPage openSavedDraft = new DraftsFolderPage(driver).openDraftMail();
        String receiver = openSavedDraft.getReceiver();
        Assert.assertEquals(receiver, "dina_abdykasheva@mail.ru","Receiver isn't valid");
        String subject = openSavedDraft.getSubject();
        Assert.assertEquals(subject,"mentoring task","Subject isn't valid");
        String body = openSavedDraft.getBody();
        Assert.assertEquals(body,"body text","Body isn't valid");
        openSavedDraft.sendMail();
    }

    @Test(description = "MailIsDeletedFromDraftsTest", priority = 3)
    public void mailIsDeletedFromDraftsTest() {
        DraftsFolderPage openDraftFolder = new AccountPage(driver).openDrafts();
        //Assert.assertFalse(openDraftFolder.isDraftMailDisplayed(), "Mail isn't deleted from drafts");
    }

    @Test(description = "VerifySentFolderTest", priority = 4)
    public void verifySentFolderTest() {
        SentFolderPage openSentFolder = new AccountPage(driver).openSentMail();
        boolean isMailSent = new SentFolderPage(driver).isMailSent();
        Assert.assertTrue(isMailSent, "Mail isn't sent");
    }

    @Test(description = "ExitGMailTest", priority = 5)
    public void exitGMailTest() {
        AccountPage exitGMail = new AccountPage(driver).exitGMail();
        boolean isUserLoggedOff = new LoginToGMailPage(driver).isUserLoggedOff();
        Assert.assertTrue(isUserLoggedOff, "User wasn't logged off");
    }

    @AfterClass(description = "closeDriver")
    public void closeDriver() {
        driver.close();
    }
}
