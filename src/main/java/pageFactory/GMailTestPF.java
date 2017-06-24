package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageFactory.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class GMailTestPF {
    private WebDriver driver;

    @BeforeClass(description = "StartBrowser")
    private void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(description = "pageObject.GMailTestPO")
    public void loginToAccountTest() {
        AccountPagePF accountPage = new UsernameVerificationPagePF(driver).googleMailOpen().fillUsername("test.da.10062017").clickNextButton().fillPassword("testtest01").clickNextButton();
        boolean isAccountIconPresent = new AccountPagePF(driver).isElementPresent();
        Assert.assertTrue(isAccountIconPresent, "User isn't logged in");
    }

    @Test(description = "SaveToDraftTest", dependsOnMethods = "loginToAccountTest")
    public void saveToDraftTest() {
        WriteMailPagePF writeMail = new AccountPagePF(driver).clickWriteMailButton().fillToField("dina_abdykasheva@mail.ru").fillSubjectField("mentoring task").fillBodyField("text").clickCloseButton();
        DraftsFolderPagePF draftsFolder = new AccountPagePF(driver).openDrafts();
        boolean isDraftMailSaved = new DraftsFolderPagePF(driver).isDraftMailSaved();
        Assert.assertTrue(isDraftMailSaved, "Mail isn't saved in drafts");
    }

    @Test(description = "VerifySavedDraftTest", dependsOnMethods = "saveToDraftTest")
    public void verifySavedDraftTest() {
        WriteMailPagePF openSavedDraft = new DraftsFolderPagePF(driver).openDraftMail();
        String receiver = new WriteMailPagePF(driver).getReceiver();
        Assert.assertEquals("dina_abdykasheva@mail.ru", receiver, "Receiver isn't valid");
        String subject = new WriteMailPagePF(driver).getSubject();
        Assert.assertEquals("mentoring task", subject, "Subject isn't valid");
        String body = new WriteMailPagePF(driver).getBody();
        Assert.assertEquals("text", body, "Body isn't valid");
    }

    @Test(description = "SendMailTest", dependsOnMethods = "verifySavedDraftTest")
    public void sendMailTest() {
        AccountPagePF sendMail = new WriteMailPagePF(driver).sendMail();
    }

    @Test(description = "MailIsDeletedFromDraftsTest", dependsOnMethods = "sendMailTest")
    public void mailIsDeletedFromDraftsTest() {
        DraftsFolderPagePF openDraftFolder = new AccountPagePF(driver).openDrafts();
        boolean isMailDeletedFromDrafts = new DraftsFolderPagePF(driver).isDraftMailDeleted();
        Assert.assertTrue(isMailDeletedFromDrafts, "Mail isn't deleted from drafts");
    }

    @Test(description = "VerifySentFolderTest", dependsOnMethods = "sendMailTest")
    public void verifySentFolderTest() {
        SentFolderPagePF openSentFolder = new AccountPagePF(driver).openSentMail();
        boolean isMailSent = new SentFolderPagePF(driver).isMailSent();
        Assert.assertTrue(isMailSent, "Mail isn't sent");
    }

    @Test(description = "ExitGMailTest", dependsOnMethods = "verifySentFolderTest")
    public void exitGMailTest() {
        AccountPagePF exitGMail = new AccountPagePF(driver).exitGMail();
    }

    @AfterClass(description = "closeDriver")
    public void closeDriver() {
        driver.close();
    }

}
