import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonPageObject {

    protected WebDriver driver;

    public CommonPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
