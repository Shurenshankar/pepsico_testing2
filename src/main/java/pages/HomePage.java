package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
public class HomePage {
    // ====== LOCATORS (INITIALIZED — NOT NULL) ======
    // Click/focus the search input to open the search bar
    public static final By SEARCH_ICON = By.xpath(
        "//*[@id='main']/header/div[2]/nav[2]/div/div[2]/div[3]/ul/li[2]/div/div[1]/div/div/div/div/form/div/input"
    );
    // Search input field
    public static final By SEARCH_INPUT = By.cssSelector(
        "#main > header > div.js-header-menu.navbar-fixed-top > " +
        "nav.navigation.navigation--middle.js-navigation--middle > div > " +
        "div.desktop__nav.hidden-xs.hidden-sm > " +
        "div.nav__right.col-sm-9.col-xs-6.hidden-xs.hidden-sm.col-md-9.col-lg-10 > ul > li:nth-child(2) > div > " +
        "div.nav__left.no-space.col-sm-5.col-md-5.col-lg-7 > div > div > div > div > form > div > input"
    );
    // Suggestion items (more robust than #ui-id-28)
    public static final By SUGGESTION_LIST_ITEMS = By.cssSelector(".ui-autocomplete .ac-prod-data");
    public static final By BECOME_CUSTOMER_BTN = By.xpath(
        "//*[@id='main']/header/div[2]/nav[2]/div/div[2]/div[3]/ul/li[2]/div/div[2]/div[2]/div/a"
    );
    public static final By PEPSI_LOGO = By.xpath(
        "//*[@id='main']/header/div[2]/nav[2]/div/div[2]/div[2]/div/div/div[1]/div/a/img"
    );
    public static final By LOGIN_REGISTER_LINK = By.xpath("//*[@id='loginDown']");
    // ====== DRIVER / WAITER ======
    private static WebDriver driver;        // kept static so your static checks still work
    private WebDriverWait wait;
    public HomePage(WebDriver driver) {
        HomePage.driver = driver;           // assign to static field
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    // ====== ACTIONS / QUERIES ======
    public void openSearch() {
        WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_ICON));
        icon.click();
    }
    public boolean isSearchInputVisible() {
        openSearch(); // make sure it's opened
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT)).isDisplayed();
    }
    public void enterSearchText(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT));
        input.clear();
        input.sendKeys(text);
    }
    public boolean isSuggestionDisplayed() {
        List<WebElement> suggestions =
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(SUGGESTION_LIST_ITEMS));
        return !suggestions.isEmpty();
    }
    public void clickFirstSuggestion() {
        List<WebElement> suggestions =
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SUGGESTION_LIST_ITEMS));
        if (!suggestions.isEmpty()) {
            suggestions.get(0).click();
        }
    }
    public void clearSearchInput() {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT));
        input.clear();
    }
    // ====== STATIC CLICKABLE CHECKS (KEEPING YOUR ORIGINAL SIGNATURES) ======
    public static boolean isBecomeCustomerClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(BECOME_CUSTOMER_BTN));
        return element.isDisplayed();
    }
    public static boolean isPepsiLogoClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(PEPSI_LOGO));
        return logo.isDisplayed();
    }
    public static boolean isLoginRegisterClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_REGISTER_LINK));
        return login.isDisplayed();
    }
    public void openContactUsDirectly() {
        try {
            String url = "https://www.pepsicopartners.com/pepsico/en/USD/contact";
            driver.navigate().to(url);
            System.out.println("Navigated to Contact Us page: " + url);
        } catch (Exception e) {
            System.out.println("Failed to open Contact Us page: " + e.getMessage());
        }
    }
	public boolean clickBecomeCustomerAndReturnHome() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click the Become a Customer button
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(BECOME_CUSTOMER_BTN));
        button.click();

        // Wait for redirection to Become a Customer page
        wait.until(ExpectedConditions.urlContains("become-customer")); // update if actual URL is different
        String redirectedUrl = driver.getCurrentUrl();
        if (!redirectedUrl.contains("become-customer")) {
            System.out.println("Redirected URL did not contain 'become-customer': " + redirectedUrl);
            return false;
        }

        // Go back to homepage
        driver.navigate().back();

        // Verify back to homepage
        wait.until(ExpectedConditions.urlContains("pepsicopartners.com"));
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("pepsicopartners.com");

    } catch (Exception e) {
        System.out.println("Exception during redirect and return: " + e.getMessage());
        return false;
    }
	}
}