import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class
MedicationPropertiesList extends CommonPageObject {

    //private static final String OPTIONS_MEDICATION = "//*[@id=\"ember765\"]/div[2]";
    private static final String REQUEST = "//a[@class=\"category-sub-item nav-link active ember-view\"]";
    private static final String NEW_REQUEST = "[href=\"#/medication/edit/new\"]";
    private static final String COMPLETED = "[href=\"#/medication/completed\"]";
    private static final String RETURN_MEDICATION = "[href=\"#/medication/return/new\"]";

    @FindBy(how = How.CSS, using = "[href=\"#/medication/edit/new\"]")
    private WebElement newRequest;

    public MedicationPropertiesList(WebDriver driver, WebElement element) {
        super(driver);
    }

    public String newRequestButton() {
        return driver.findElement(By.cssSelector(NEW_REQUEST)).getText();
    }

    public String requestButton() {
        return driver.findElement(By.xpath(REQUEST)).getText();
    }

    public String completedButton() {
        return driver.findElement(By.cssSelector(COMPLETED)).getText();
    }

    public String returnMedicationButton() {
        return driver.findElement(By.cssSelector(RETURN_MEDICATION)).getText();
    }
    /*
        public ArrayList<String> optionsList() {
            List<String> list = new ArrayList<>();
            List<WebElement> optionsList = driver.findElements(By.xpath(OPTIONS_MEDICATION));
            for (WebElement element : optionsList)
            {
               list.add(element.getText());

            }
            return (ArrayList<String>) list;
        }
    */
    public void clickNewRequest() {
        newRequest.click();
    }


}
