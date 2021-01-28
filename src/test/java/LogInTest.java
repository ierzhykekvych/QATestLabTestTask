import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

@Listeners(ListenersTestNG.class)
public class LogInTest extends TestListenerAdapter {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_LOCATION = "src\\test\\WebDrivers\\Chrome\\87.0.4280.88\\chromedriver.exe";
    private static final String MOZILLA_DRIVER_LOCATION = "src\\test\\WebDrivers\\Mozilla\\geckodriver.exe";
    private static final String MOZILLA_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String APPLICATION_URL = "http://demo.hospitalrun.io/#/login";
    private WebDriver driver;

    int timeOut = 3;

    @DataProvider(name = "positiveTest")
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
    }

    @Test(dataProvider = "positiveTest")
    private void signInTest(String userName, String password) {
        String name = "1";
        String name2 = "1";
        String name3 = new String("1");
        System.out.println(name == name3);
        driver.get(APPLICATION_URL);
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        /*{
            @Override
            public boolean isVisibleErrorEmailIsRequired() {
                System.out.print("asd");
                return false;
            }
        };
         */
        MainPageObject mainPageObject = new MainPageObject(driver);
        signInPageObject.getSingInComponent()
                .inputUsername(userName)
                .inputPassword(password)
                .clickOnSingInButton();
        mainPageObject.getCommonLeftSideBarComponent().clickOnPatientsButton();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.urlToBe("http://demo.hospitalrun.io/#/patients"));
    }

    @AfterClass
    private void tearDown() {
        driver.quit();

    }
}