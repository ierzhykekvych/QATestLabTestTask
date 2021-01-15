import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
@Listeners(ListenersTestNG.class)
public class LogOutTest {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_LOCATION = "src\\test\\WebDrivers\\Chrome\\87.0.4280.88\\chromedriver.exe";
    private static final String MOZILLA_DRIVER_LOCATION = "src\\test\\WebDrivers\\Mozilla\\geckodriver.exe";
    private static final String MOZILLA_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String APPLICATION_URL = "http://demo.hospitalrun.io/#/login";
    private WebDriver driver;

    @DataProvider(name = "logOutTest")
    public Object[][] dataSignIn() {
        return new Object[][]{{("hr.doctor@hospitalrun.io"), ("HRt3st12")}};
    }

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) throws Exception {

        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty(MOZILLA_DRIVER_PROPERTY, MOZILLA_DRIVER_LOCATION);
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_LOCATION);
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(APPLICATION_URL);
    }

    @Step("checking if page {actualURL} matches page {expectedURL}")
    public void checkingStringEqualsStep(String actualURL, String expectedURL) {
        Assert.assertEquals(actualURL, expectedURL);
    }

    @Test(dataProvider = "logOutTest")
    private void logOutTest(String userName, String password) throws Exception {
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        MainPageObject mainPageObject = new MainPageObject(driver);
        signInPageObject.getSingInComponent()
                .inputUsername(userName)
                .inputPassword(password)
                .clickOnSingInButton();

        mainPageObject.getCommonLeftSideBarComponent()
                .clickOnCogwheelButton()
                .getCogwheelComponent().clickOnLogOutButton();
        String expectedURL = APPLICATION_URL;
        String actualURL = driver.getCurrentUrl();
        checkingStringEqualsStep(actualURL, expectedURL);
    }

    @AfterClass
    private void tearDown() {
        driver.quit();

    }
}
