package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dina_Abdykasheva on 6/16/2017.
 */
public class AbstractPagePF {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10;
    protected WebDriver driver;

    protected AbstractPagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
