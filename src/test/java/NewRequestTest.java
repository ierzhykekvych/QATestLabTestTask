import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;
@Listeners(ListenersTestNG.class)
public class NewRequestTest {

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_LOCATION = "src\\test\\WebDrivers\\Chrome\\87.0.4280.88\\chromedriver.exe";
    private static final String MOZILLA_DRIVER_LOCATION = "src\\test\\WebDrivers\\Mozilla\\geckodriver.exe";
    private static final String MOZILLA_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String APPLICATION_URL = "http://demo.hospitalrun.io/#/login";
    private WebDriver driver;
    private WebDriverWait wait;
    int timeOut = 10;

    @DataProvider(name = "CreateNewRequest")
    public Object[][] dataSignIn() {
        return new Object[][]{{("hr.doctor@hospitalrun.io"), ("HRt3st12")}};
    }

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) throws Exception{

        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty(MOZILLA_DRIVER_PROPERTY, MOZILLA_DRIVER_LOCATION);
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("chrome")){
            System.setProperty(CHROME_DRIVER_PROPERTY,CHROME_DRIVER_LOCATION);
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "CreateNewRequest")
    private void logOutTest(String userName, String password) throws InterruptedException, ParseException {
        wait = new WebDriverWait(driver, timeOut);
        driver.get(APPLICATION_URL);
        SignInPageObject signInPageObject = new SignInPageObject(driver);
        MainPageObject mainPageObject = new MainPageObject(driver);
        MedicationPageObject medicationPageObject = new MedicationPageObject(driver);
        NewMedicationRequestPageObject newMedicationRequestPageObject = new NewMedicationRequestPageObject(driver);
        Prescription prescription = new Prescription();
        NameMedicationPropertiesList nameMedicationPropertiesList = new NameMedicationPropertiesList();
        signInPageObject.getSingInComponent()
                .inputUsername(userName)
                .inputPassword(password)
                .clickOnSingInButton();
        mainPageObject.getCommonLeftSideBarComponent().clickOnMedicationButton();

        Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().requestButton(), nameMedicationPropertiesList.requests());
        Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().newRequestButton(), nameMedicationPropertiesList.newRequest());
        Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().returnMedicationButton(), nameMedicationPropertiesList.returnMedication());
        Assert.assertEquals(medicationPageObject.getMedicationPropertiesList().completedButton(), nameMedicationPropertiesList.completed());

        medicationPageObject.getMedicationPropertiesList().clickNewRequest();
        newMedicationRequestPageObject.inputPatient(newMedicationRequestPageObject.patient, "Test Patient");
        newMedicationRequestPageObject.selectPatient();
        newMedicationRequestPageObject.clickOnVisitButton();
        newMedicationRequestPageObject.selectVisitOfData();
        newMedicationRequestPageObject.inputMedication("Pramoxine");

        newMedicationRequestPageObject
                .selectMedication()
                .inputPrescription(prescription.Prescription)
                .inputPrescriptionDate()
                .inputRefills()
                .inputQuantityRequested();

        newMedicationRequestPageObject.clickOnAddButton();

        Assert.assertTrue(newMedicationRequestPageObject.getMedicationRequestComponent().isVisibleCrossButtonButton());
        Assert.assertTrue(newMedicationRequestPageObject.getMedicationRequestComponent().isVisibleIsCrossButton());

        newMedicationRequestPageObject.getMedicationRequestComponent().clickOnOkButton();
        Assert.assertTrue(newMedicationRequestPageObject.isVisibleMedicationRequestSaved());
        wait.until(ExpectedConditions.urlToBe("http://demo.hospitalrun.io/#/medication/edit/new"));
    }

    @AfterClass
    private void tearDown() {
    driver.quit();

    }
}