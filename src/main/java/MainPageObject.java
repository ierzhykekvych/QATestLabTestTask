import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPageObject extends CommonPageObject {

    private WebElement CommonLeftSideBarComponent;

    public MainPageObject(WebDriver driver) {
        super(driver);
    }

    CommonLeftSideBarComponent getCommonLeftSideBarComponent() {
        return new CommonLeftSideBarComponent(driver, CommonLeftSideBarComponent);
    }

}
