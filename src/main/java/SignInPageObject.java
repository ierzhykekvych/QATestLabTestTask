import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPageObject extends CommonPageObject {

    private static final String ERROR_EMAIL_IS_REQUIRED = "[class=\"alert alert-danger form-signin-alert\"]";
    private static final String ERROR_PASSWORD_IS_REQUIRED = "[class=\"alert alert-danger form-signin-alert\"]";
    private static final String SING_IN_COMPONENT = "div.signin-contents";
    private static WebDriverWait wait;

    @FindBy(css = ERROR_EMAIL_IS_REQUIRED)
    private WebElement errorEmailIsRequired;

    @FindBy(css = ERROR_PASSWORD_IS_REQUIRED)
    private WebElement errorPasswordIsRequired;

    @FindBy(css = SING_IN_COMPONENT )
    WebElement singInComponent;

    public SignInPageObject(WebDriver driver){
        super(driver);
        int timeOutInSeconds = 10;
        wait = new WebDriverWait(driver, timeOutInSeconds);
    }

    public SingInComponent getSingInComponent() {
        return new SingInComponent(driver, singInComponent);
    }

    public boolean isVisibleErrorEmailIsRequired() {
        wait.until(ExpectedConditions.visibilityOf(errorEmailIsRequired));
        return errorEmailIsRequired.isDisplayed();
    }

    public boolean isVisibleErrorPasswordIsRequired() {
        wait.until(ExpectedConditions.visibilityOf(errorPasswordIsRequired));
        return errorPasswordIsRequired.isDisplayed();
    }
}
