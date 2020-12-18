import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CommonLeftSideBarComponent extends CommonPageObject  {

    private static final String PATIENT_BUTTON = "[id=\"ember727\"]";
    private static final String MEDICATION = "[href=\"#/medication\"]";
    private static final String COGWHEEL_BUTTON = "[class=\"mega-octicon octicon-gear\"]";

    WebElement cogwheelComponent;

    @FindBy(how = How.CSS, using = COGWHEEL_BUTTON)
    private WebElement cogwheelButton;

    @FindBy(how = How.CSS, using = PATIENT_BUTTON)
    private WebElement patientButton;

    @FindBy(how = How.CSS, using = MEDICATION)
    private WebElement medicationButton;

    Object fds = new Object();


    public CommonLeftSideBarComponent(WebDriver driver, WebElement element) {
        super(driver);
    }

    CogwheelComponent getCogwheelComponent() {
        return new CogwheelComponent(driver, cogwheelComponent);
    }

    public void clickOnPatientsButton() {
        patientButton.click();
    }

    public void clickOnCogwheelButton() {
        cogwheelButton.click();
    }

    public void clickOnMedicationButton() {
        medicationButton.click();
    }
}
