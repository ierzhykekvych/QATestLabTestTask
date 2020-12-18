import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LogInNegativeTest {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_LOCATION = "src\\test\\WebDrivers\\Chrome\\86.0.4240.22\\chromedriver.exe";
    private static final String APPLICATION_URL = "http://demo.hospitalrun.io/#/login";
    private static WebDriver driver;

    @DataProvider(name = "negativeTestEmptyFields")
    public Object[][] dataSignInEmptyFields() {
        return new Object[][] {{(""),("")}};
    }

    @DataProvider(name = "negativeTestWrongData")
    public Object[][] WrongDataSignIn() {
        return new Object[][] {{("1"),("1")}};
    }

    @BeforeClass
    private static void setup(){
        System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "negativeTestEmptyFields", enabled = true)
    private void signInNegativeTestEmptyFields(String userName, String password) {
        driver.get(APPLICATION_URL);
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        signInPageObject.getSingInComponent().inputUsername(userName);
        signInPageObject.getSingInComponent().inputPassword(password);
        signInPageObject.getSingInComponent().clickOnSingInButton();

        boolean actualResult = signInPageObject.isVisibleErrorEmailIsRequired();
        final boolean expectedResult = true;
        String expectedUrl = APPLICATION_URL;
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualResult,expectedResult);
        Assert.assertEquals(actualUrl,expectedUrl);
    }

    @Test(dataProvider = "negativeTestWrongData", dependsOnMethods = {"signInNegativeTestEmptyFields"})
    private void signInNegativeTestWrongData(String userName, String password) {
        driver.get(APPLICATION_URL);
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        signInPageObject.getSingInComponent().inputUsername(userName);
        signInPageObject.getSingInComponent().inputPassword(password);
        signInPageObject.getSingInComponent().clickOnSingInButton();

        boolean actual = signInPageObject.isVisibleErrorPasswordIsRequired();
        final boolean expectedResult = true;
        String expectedUrl = APPLICATION_URL;
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actual, expectedResult);
        Assert.assertEquals(actualUrl,expectedUrl);
    }

    @AfterClass
    private static void tearDown() {
        driver.quit();
    }
}
