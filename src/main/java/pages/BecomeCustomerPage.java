package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BecomeCustomerPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    public BecomeCustomerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }
    // Locators
    private By businessType = By.id("businessType");
    private By contactName = By.id("leadGenerationForm_name");
    private By businessName = By.id("leadGenerationForm_businessName");
    private By businessAddress = By.id("leadGenerationForm_businessAdd");
    private By country = By.id("leadGenerationForm_country");
    private By city = By.id("leadGenerationForm_city");
    private By state = By.id("leadGenerationForm_state");
    private By zip = By.id("leadGenerationForm_zip");
    private By phone = By.id("leadGenerationForm_phone");
    private By email = By.id("leadGenerationForm_email");
    private By employeeCheckbox = By.id("referralEmployee");
    private By globalId = By.id("leadGenerationForm_globalId");
    private By empName = By.id("leadGenerationForm_employeeName");
    private By empPhone = By.id("leadGenerationForm_employeePhone");
    private By empEmail = By.id("leadGenerationForm_employeeEmail");
    private By pepsiBeveragesCheckbox = By.id("listOfLobs0");
    private By fritoLayCheckbox = By.id("listOfLobs1");
    private By tropicanaCheckbox = By.id("listOfLobs2");
    private By quakerCheckbox = By.id("listOfLobs3");
    private By gatoradeCheckbox = By.id("listOfLobs4");
    private By commentsBox = By.id("leadGenerationForm_req");
    private By submitBtn =By.cssSelector("button.leadGenSubBtn[type='submit']");
    // Wait for the form to fully load
    public void waitForFormToLoad() {
        try {
            // If the form is inside an iframe, uncomment below
            // driver.switchTo().frame("iframe-id");
            wait.until(ExpectedConditions.presenceOfElementLocated(businessType));
            System.out.println("[INFO] Form is ready for interaction.");
        } catch (Exception e) {
            System.out.println("[ERROR] Form did not load properly: " + e.getMessage());
        }
    }
    public void selectBusinessType(String type) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(businessType));
        dropdown.click();
        dropdown.sendKeys(type);
    }
    public void enterContactName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(contactName)).sendKeys(name);
    }
    public void enterBusinessName(String bName) {
        driver.findElement(businessName).sendKeys(bName);
    }
    public void enterBusinessAddress(String bAddress) {
        driver.findElement(businessAddress).sendKeys(bAddress);
    }
    public void enterCountry(String countryName) {
        driver.findElement(country).sendKeys(countryName);
    }
    public void enterCity(String cityName) {
        driver.findElement(city).sendKeys(cityName);
    }
    public void enterState(String stateName) {
        driver.findElement(state).sendKeys(stateName);
    }
    public void enterZip(String zipCode) {
        driver.findElement(zip).sendKeys(zipCode);
    }
    public void enterPhone(String phoneNum) {
        driver.findElement(phone).sendKeys(phoneNum);
    }
    public void enterEmail(String emailAddr) {
        driver.findElement(email).sendKeys(emailAddr);
    }
    public void checkIsPepsiEmployee() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(employeeCheckbox));
        scrollToElement(checkbox);
        clickWithJS(checkbox);
        wait.until(ExpectedConditions.presenceOfElementLocated(globalId));
    }
    public void enterReferralDetails(String id, String name, String phone, String email) {
        driver.findElement(globalId).sendKeys(id);
        driver.findElement(empName).sendKeys(name);
        driver.findElement(empPhone).sendKeys(phone);
        driver.findElement(empEmail).sendKeys(email);
    }
    public void selectPepsiBeverages() {
        selectCheckbox(pepsiBeveragesCheckbox);
    }
    public void selectFritoLay() {
        selectCheckbox(fritoLayCheckbox);
    }
    public void selectTropicana() {
        selectCheckbox(tropicanaCheckbox);
    }
    public void selectQuaker() {
        selectCheckbox(quakerCheckbox);
    }
    public void selectGatorade() {
        selectCheckbox(gatoradeCheckbox);
    }
    public void enterComments(String comment) {
        WebElement comments = wait.until(ExpectedConditions.presenceOfElementLocated(commentsBox));
        scrollToElement(comments);
        comments.clear();
        comments.sendKeys(comment);
    }
    public void clickSubmit() {
        try {
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(submitBtn));
            scrollToElement(btn);

            // ✅ [NEW] Fill CAPTCHA input if it exists
            try {
                WebElement captchaInput = driver.findElement(By.id("accessibility")); // adjust if needed
                captchaInput.clear();
                captchaInput.sendKeys("1234");  // ✅ Use test CAPTCHA value allowed by backend
                System.out.println("[INFO] CAPTCHA input filled with test value.");
            } catch (Exception e) {
                System.out.println("[WARN] CAPTCHA input not found or not fillable.");
            }

            // Log button state
            System.out.println("[INFO] Submit button displayed: " + btn.isDisplayed());
            System.out.println("[INFO] Submit button enabled: " + btn.isEnabled());
            System.out.println("[INFO] Submit button text: " + btn.getText());

            // Check if button is enabled
            if (!btn.isEnabled()) {
                System.out.println("[ERROR] Submit button is disabled.");
                return;
            }

            // Try native click first
            try {
                btn.click();
                System.out.println("[INFO] Native click on submit button succeeded.");
            } catch (Exception e) {
                System.out.println("[WARN] Native click failed: " + e.getMessage());
                System.out.println("[INFO] Attempting JavaScript click as fallback.");
                clickWithJS(btn);
            }

            // Optional: wait for confirmation message or page redirect
            wait.until(ExpectedConditions.or(
                ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "thank"),
                ExpectedConditions.urlContains("confirmation") // adjust if there's a redirect
            ));

        } catch (Exception e) {
            System.out.println("[ERROR] Failed to click submit button: " + e.getMessage());
        }
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void clickWithJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
    private void selectCheckbox(By checkboxLocator) {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));
        scrollToElement(checkbox);
        if (!checkbox.isSelected()) {
            clickWithJS(checkbox);
        }
    }
	public boolean hasValidationErrors() {
		// TODO Auto-generated method stub
		return false;
	}
}