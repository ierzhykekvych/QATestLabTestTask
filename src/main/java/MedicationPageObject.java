import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MedicationPageObject extends CommonPageObject {

    private WebElement a;

    public MedicationPageObject(WebDriver driver) {
        super(driver);
    }

    MedicationPropertiesList getMedicationPropertiesList() {
        return new MedicationPropertiesList(driver, a);
    }

}
