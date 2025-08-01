package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.stream.Collectors;

public class ContactUsPage {
    private WebDriver driver;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By nameInput = By.id("j_name");
    private By addressInput = By.id("j_address");
    private By phoneInput = By.id("j_telephone");
    private By emailInput = By.id("j_email"); // Update this locator to actual email field (it was wrong before)
    private By subjectDropdown = By.xpath("//select[@name='j_subject']");
    private By commentsInput = By.id("j_message");
    private By submitBtn = By.id("submitForm"); 
    private By cancelBtn = By.id("cancelSubmit");
    private By validationError = By.cssSelector(".field-validation-error");

    // Actions
    public void enterName(String name) {
        WebElement element = driver.findElement(nameInput);
        element.clear();
        element.sendKeys(name);
    }

    public void enterAddress(String address) {
        WebElement element = driver.findElement(addressInput);
        element.clear();
        element.sendKeys(address);
    }

    public void enterPhone(String phone) {
        WebElement element = driver.findElement(phoneInput);
        element.clear();
        element.sendKeys(phone);
    }

    public void enterEmail(String email) {
        WebElement element = driver.findElement(emailInput);
        element.clear();
        element.sendKeys(email);
    }

    public void selectSubject(String subject) {
        Select dropdown = new Select(driver.findElement(subjectDropdown));
        dropdown.selectByVisibleText(subject);
    }

    public void enterComments(String comment) {
        WebElement element = driver.findElement(commentsInput);
        element.clear();
        element.sendKeys(comment);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    public void clickCancel() {
        driver.findElement(cancelBtn).click();
    }

    // Get values
    public String getNameValue() {
        return driver.findElement(nameInput).getAttribute("value");
    }

    public String getAddressValue() {
        return driver.findElement(addressInput).getAttribute("value");
    }

    public String getPhoneValue() {
        return driver.findElement(phoneInput).getAttribute("value");
    }

    public String getEmailValue() {
        return driver.findElement(emailInput).getAttribute("value");
    }

    public String getSelectedSubject() {
        Select dropdown = new Select(driver.findElement(subjectDropdown));
        return dropdown.getFirstSelectedOption().getText();
    }

    public List<String> getSubjectOptions() {
        Select dropdown = new Select(driver.findElement(subjectDropdown));
        return dropdown.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getCommentsValue() {
        return driver.findElement(commentsInput).getAttribute("value");
    }

    // Button checks
    public boolean isSubmitVisible() {
        return driver.findElement(submitBtn).isDisplayed();
    }

    public boolean isSubmitEnabled() {
        return driver.findElement(submitBtn).isEnabled();
    }

    public boolean isCancelVisible() {
        return driver.findElement(cancelBtn).isDisplayed();
    }

    public boolean isValidationErrorShown() {
        return !driver.findElements(validationError).isEmpty();
    }
}
