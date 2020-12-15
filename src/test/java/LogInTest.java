import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LogInTest {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_LOCATION = "src\\test\\WebDrivers\\Chrome\\86.0.4240.22\\chromedriver.exe"; // CSS зазвичай юзати
    private static final String APPLICATION_URL = "http://demo.hospitalrun.io/#/login";
    private static WebDriver driver;

    int timeOut = 3;

    @DataProvider(name = "positiveTest")
    public Object[][] dataSignIn() {
        return new Object[][] {{("hr.doctor@hospitalrun.io"),("HRt3st12")}};
    }

    @BeforeClass
    private static void setup() {
        System.setProperty(CHROME_DRIVER_PROPERTY, CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(APPLICATION_URL);
    }

    @Test(dataProvider = "positiveTest")
    private void signInTest(String userName, String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        MainPageObject mainPageObject = new MainPageObject(driver);
        signInPageObject.getSingInComponent().inputUsername(userName);
        signInPageObject.getSingInComponent().inputPassword(password);
        signInPageObject.getSingInComponent().clickOnSingInButton();
        mainPageObject.getCommonLeftSideBarComponent().clickOnPatientsButton();
        wait.until(ExpectedConditions.urlToBe("http://demo.hospitalrun.io/#/patients"));
    }

    @AfterClass
    private static void tearDown() {
        driver.quit();
    }

}
