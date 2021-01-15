import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

@Listeners(ListenersTestNG.class)
public class LogInNegativeTest {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_LOCATION = "src\\test\\WebDrivers\\Chrome\\87.0.4280.88\\chromedriver.exe";
    private static final String MOZILLA_DRIVER_LOCATION = "src\\test\\WebDrivers\\Mozilla\\geckodriver.exe";
    private static final String MOZILLA_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String APPLICATION_URL = "http://demo.hospitalrun.io/#/login";
    private WebDriver driver;

    @DataProvider(name = "negativeTestEmptyFields")
    public Object[][] dataSignInEmptyFields() {
        return new Object[][]{{(""), ("")}};
    }

    @DataProvider(name = "negativeTestWrongData")
    public Object[][] WrongDataSignIn() {
        return new Object[][]{{("1"), ("1")}};
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
    }

    @Test(dataProvider = "negativeTestEmptyFields", enabled = true)
    private void signInNegativeTestEmptyFields(String userName, String password) {
        driver.get(APPLICATION_URL);
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        signInPageObject.getSingInComponent()
                .inputUsername(userName)
                .inputPassword(password)
                .clickOnSingInButton();

        boolean actualResult = signInPageObject.isVisibleErrorEmailIsRequired();
        final boolean expectedResult = true;
        String expectedUrl = APPLICATION_URL;
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(dataProvider = "negativeTestWrongData", dependsOnMethods = {"signInNegativeTestEmptyFields"})
    private void signInNegativeTestWrongData(String userName, String password) {
        driver.get(APPLICATION_URL);
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        signInPageObject.getSingInComponent()
                .inputUsername(userName)
                .inputPassword(password)
                .clickOnSingInButton();

        boolean actual = signInPageObject.isVisibleErrorPasswordIsRequired();
        final boolean expectedResult = true;
        String expectedUrl = APPLICATION_URL;
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actual, expectedResult);
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @AfterClass
    private void tearDown() {
        driver.quit();

    }
}
