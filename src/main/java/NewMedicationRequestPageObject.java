import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class NewMedicationRequestPageObject extends CommonPageObject {

    private static final String PATIENT_FIELD = "//input[starts-with(@id,'patientTypeAhead-ember')]";
    private static final String PATIENT_DATA_FIELD = "//div[text() = \" - P00201\"]";
    private static final String VISIT_FIELD = "[class=\"form-control \"]";
    private static final String MEDICATION_FIELD = "//span/input[starts-with(@id,'inventoryItemTypeAhead-ember')]";
    private static final String PRESCRIPTION_FIELD = "[class=\"form-control  ember-text-area ember-view\"]";
    private static final String PRESCRIPTION_DATE_FIELD = "//input[starts-with(@id,'display_prescriptionDate-ember')]";
    private static final String QUANTITY_REQUESTED = "//input[starts-with(@id,'quantity-ember')]";
    private static final String REFILLS = "//input[starts-with(@id,'refills-ember')]";
    private static final String FULFILL_REQUEST = "[id=\"shouldFulfillRequest-ember1274\"]";
    private static final String MEDICATION_REQUEST_SAVED_COMPONENT = "//div[text()= \"The medication record has been saved.\"]";
    private final WebDriverWait wait;
    private WebElement panelBody;

    @FindBy(xpath = PATIENT_FIELD)
    WebElement patient;

    @FindBy(xpath = PATIENT_DATA_FIELD)
    private WebElement patientData;

    @FindBy(css = VISIT_FIELD)
    private WebElement visit;

    @FindBy(xpath = MEDICATION_FIELD)
    private WebElement medicationField;

    @FindBy(css = PRESCRIPTION_FIELD)
    private WebElement prescription;

    @FindBy(xpath = PRESCRIPTION_DATE_FIELD)
    private WebElement prescriptionDateField;

    @FindBy(xpath = QUANTITY_REQUESTED)
    private WebElement quantityRequested;

    @FindBy(xpath = REFILLS)
    private WebElement refills;

    @FindBy(id = FULFILL_REQUEST)
    private WebElement fulfillRequest;

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div/div[2]/button[2]")
    private WebElement addButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[2]/text()")
    private WebElement medicationRequestSaved;

    public NewMedicationRequestPageObject(WebDriver driver) {
        super(driver);
        int timeOutInSeconds = 2;
        wait = new WebDriverWait(driver, timeOutInSeconds);
    }

    MedicationRequestComponent getMedicationRequestComponent() {
        return new MedicationRequestComponent(driver, medicationRequestSaved);
    }

    public void clickOnVisitButton() {
        visit.click();
    }

    public void selectVisitOfData() {
        List<WebElement> list = driver.findElements(By.cssSelector("[value]"));
        int size = list.size();
        int rand = ThreadLocalRandom.current().nextInt(0, size);
        list.get(rand).click();
    }


    protected void inputPatient(WebElement webElement, String text) {
        webElement.clear();
        for (int i = 0; i < text.length(); i++) {
            webElement.sendKeys(String.valueOf(text.charAt(i)));
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void inputMedication(String nameOfMedication) {
        medicationField.sendKeys(nameOfMedication);
    }

    public NewMedicationRequestPageObject selectMedication() {
        List<WebElement> listOfMedication = driver.findElements(By.xpath("//div[@class= \"tt-dataset tt-dataset-1\"]/*"));
        int size = listOfMedication.size();
        int rand = ThreadLocalRandom.current().nextInt(0, size);
        listOfMedication.get(rand).click();
        return this;
    }

    public void selectPatient() {
        patientData.click();
    }

    public void inputPrescription(String prescriptionText) {
        prescription.sendKeys(prescriptionText);
    }

    public void inputPrescriptionDate() throws ParseException {
        String d = "MM/dd/yyyy";
        DateFormat dataFormat = new SimpleDateFormat(d);
        Date currentDate = new Date();
        String yesterday = dataFormat.format(currentDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(yesterday));
        calendar.add(Calendar.DATE, -1);
        yesterday = simpleDateFormat.format(calendar.getTime());
        prescriptionDateField.clear();
        prescriptionDateField.sendKeys(yesterday);
        prescriptionDateField.sendKeys(Keys.TAB);
    }

    public void inputQuantityRequested() {
        quantityRequested.sendKeys(Integer.toString(new Random().nextInt(5)+1));
    }

    public void inputRefills() {
        refills.sendKeys(Integer.toString(new Random().nextInt(5)+5));
    }

    public void clickOnAddButton() {
        addButton.click();
    }

    public boolean isVisibleMedicationRequestSaved() {
        return wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(MEDICATION_REQUEST_SAVED_COMPONENT),"The medication record has been saved."));
    }
}
