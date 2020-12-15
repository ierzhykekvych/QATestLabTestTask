import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SingInComponent extends CommonPageObject {

    private static final String USER_NAME = "[id=\"identification\"]";
    private static final String PASSWORD = "[id=\"password\"]";
    private static final String SIGN_IN = "[class=\"btn btn-lg btn-primary btn-block\"]";

    @FindBy(how = How.CSS, using = USER_NAME)
    private WebElement userName;

    @FindBy(how = How.CSS, using = PASSWORD)
    private WebElement password;

    @FindBy(how = How.CSS, using = SIGN_IN)
    private WebElement signIn;

    public SingInComponent(WebDriver driver, WebElement element) {
        super(driver);
    }

    public void inputUsername(String name) {
        userName.sendKeys(name);
    }

    public void inputPassword(String password) {
        this.password.sendKeys(password);
    }

    public void clickOnSingInButton() {
        signIn.click();
    }
}
