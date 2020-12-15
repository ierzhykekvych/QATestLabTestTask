import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class NewMedicationRequestPageObject extends CommonPageObject {

    private WebElement panelBody;

    private static final String PATIENT_FIELD = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[1]/div[1]/div/span/input[2]";
    private static final String PATIENT_DATA_FIELD = "//div[text() = \" - P00201\"]";
    private static final String VISIT_FIELD = "[class=\"form-control \"]";
    private static final String MEDICATION_FIELD = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[2]/div/span/input[2]";
    private static final String PRESCRIPTION_FIELD = "[class=\"form-control  ember-text-area ember-view\"]";
    private static final String PRESCRIPTION_DATE_FIELD = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[4]/div/div/input";
    private static final String QUANTITY_REQUESTED = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[5]/div[1]/div/input";
    private static final String REFILLS = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[5]/div[2]/div/input";
    private static final String FULFILL_REQUEST = "[id=\"shouldFulfillRequest-ember1274\"]";

    @FindBy(xpath = PATIENT_FIELD)
    private WebElement patient;

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
        int timeOutInSeconds = 10;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
    }

    MedicationRequestComponent getMedicationRequestComponent() {
        return new MedicationRequestComponent(driver, medicationRequestSaved);
    }

    /*
    public void select4() {
        List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class=\"primary-nav\"]/*"));
        Random rand = new Random();
        int randomProduct = rand.nextInt(allProducts.size());
        allProducts.get(randomProduct).click();
        Select select = new Select(visit);
        select.selectByVisibleText("12/31/2019 - 2/14/2020 (Admission)");
    }
     */

    public void clickOnVisitButton() {
        visit.click();
    }

    public void selectVisitOfData() {
        List<WebElement> list = driver.findElements(By.cssSelector("[value]"));
        int size = list.size();
        int rand = ThreadLocalRandom.current().nextInt(0, size);
        list.get(rand).click();
    }

    public void inputPatient(String nameOfClient) {
        patient.sendKeys(nameOfClient);
    }

    public void inputMedication(String nameOfMedication) {
        medicationField.sendKeys(nameOfMedication);
    }

    public void selectMedication() {
        List<WebElement> listOfMedication = driver.findElements(By.xpath("//div[@class= \"tt-dataset tt-dataset-1\"]/*"));
        int size = listOfMedication.size();
        int rand = ThreadLocalRandom.current().nextInt(0, size);
        listOfMedication.get(rand).click();
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
        return driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/div/div/div[2]/text()")).isEmpty();
    }

}
