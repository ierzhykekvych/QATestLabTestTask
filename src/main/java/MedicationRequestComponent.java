import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MedicationRequestComponent extends CommonPageObject {

    WebDriverWait wait;

    @FindBy(css = "[class=\"octicon octicon-x\"]")
    private WebElement crossButton;

    @FindBy(xpath = "//button[text() = \"Ok\"]")
    private WebElement okButton;

    public MedicationRequestComponent(WebDriver driver, WebElement element) {
        super(driver);
        int timeOutInSeconds = 10;
        wait = new WebDriverWait(driver, timeOutInSeconds);
    }

    public void clickOnOkButton() {
        okButton.click();
    }

    public boolean isVisibleIsCrossButton() {
        wait.until(ExpectedConditions.visibilityOf(crossButton));
        return crossButton.isDisplayed();
    }

    public boolean isVisibleCrossButtonButton() {
        wait.until(ExpectedConditions.visibilityOf(okButton));
        return okButton.isDisplayed();
    }

}
